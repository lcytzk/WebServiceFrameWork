package com.yztz.stocktrade.facade.receiver;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.yztz.FCServer.model.TradeRecordItem;
import com.yztz.admin.common.service.intergration.stock.StockDataService;
import com.yztz.common.message.CustomerListener;
import com.yztz.model.InvestDealing;
import com.yztz.model.Scheme;
import com.yztz.model.User;
import com.yztz.model.enums.InvestDealingTypeEnum;
import com.yztz.model.enums.SchemeTypeEnum;
import org.yolk.common.constants.SystemConstants;
import org.yolk.common.service.CacheService;
import com.yztz.stocktrade.biz.service.SchemeBizService;
import com.yztz.stocktrade.biz.service.UserService;
import com.yztz.stocktrade.dao.InvestDealingDAO;
import com.yztz.stocktrade.facade.domain.TradeActionEnum;

/**
 * @author Liang Chenye
 * @version $Id: DealingDataReceiver, v 0.1 2015/6/23 15:12
 */
@Component
public class InvestDealingDataReceiver implements CustomerListener<Object>, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(InvestDealingDataReceiver.class);

    /**
     * 滚动数据名称map
     */
    public static Map<Integer, String> schemeRollMapping = new HashMap<Integer, String>();

    /**
     * 所有滚动数据 参数表名称
     */
    public static List<String> rollDataCacheNameList = new ArrayList<String>();

    /**
     * 缓存30条交易数据
     */
    static int CACHE_SIZE = 30;

    /**
     * 缓存失效时间10天
     */
    static int EXPIRE_TIME = 60 * 60 * 24 * 10;

    static {
        schemeRollMapping.put(SchemeTypeEnum.EVERWIN.getValue(), SystemConstants.EVERWIN_ROLL_DATA_CACHE);
        schemeRollMapping.put(SchemeTypeEnum.EXPERIENCE.getValue(), SystemConstants.EXPERIENCE_ROLL_DATA_CACHE);
        schemeRollMapping.put(SchemeTypeEnum.FAVOUR.getValue(), SystemConstants.FAVOUR_ROLL_DATA_CACHE);
        schemeRollMapping.put(SchemeTypeEnum.MATCH.getValue(), SystemConstants.MATCH_ROLL_DATA_CACHE);
        schemeRollMapping.put(SchemeTypeEnum.MONTHWIN.getValue(), SystemConstants.MONTHWIN_ROLL_DATA_CACHE);
        schemeRollMapping.put(SchemeTypeEnum.WEEKWIN.getValue(), SystemConstants.WEEKWIN_ROLL_DATA_CACHE);
        schemeRollMapping.put(SchemeTypeEnum.YING.getValue(), SystemConstants.YINIG_ROLL_DATA_CACHE);
        rollDataCacheNameList.addAll(schemeRollMapping.values());
        rollDataCacheNameList.add(SystemConstants.TRADE_BROADCAST_DATA_CACHE);
        rollDataCacheNameList.add(SystemConstants.FASHION_ROLL_DATA_CACHE);
    }

    @Autowired
    private InvestDealingDAO investDealingDAO;

    @Autowired
    private SchemeBizService schemeBizService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private UserService userService;

    @Autowired
    private StockDataService stockDataService;

    private static final String SCHEME_ID_2_USER_ID_KEY_PREFIX = "INVEST_DEALING_SCHEME_";

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * Executor service.
     */
    private ExecutorService myExecutorService;

    /**
     * 单线程处理交易数据
     */
    public InvestDealingDataReceiver() {
        myExecutorService = Executors.newSingleThreadExecutor();
    }

    public void receiveMessage(final Object obj) {
        myExecutorService.submit(new Runnable() {

            public void run() {
                doProcess(obj);
            }
        });
        if (obj instanceof TradeRecordItem) {
            InvestDealing investDealing = convertFromTradeRecordItem((TradeRecordItem) obj);
            if (investDealing != null) {
                investDealingDAO.insertIfAbsent(investDealing);
            }
        } else {
            if (logger.isWarnEnabled()) {
                logger.warn("接收交易数据类型错误，当前类型为：" + obj.getClass());
            }
        }
    }

    private void doProcess(Object obj) {
        try {
            String msg = null;
            if (obj instanceof InvestDealing) {
                InvestDealing investDealing = (InvestDealing) obj;
                msg = buildTradeStr(investDealing);
            } else if (obj instanceof String) {
                msg = (String) obj;
            }
            if (msg != null) {
                String[] params = msg.split(",");
                processCacheRollData(msg, SystemConstants.TRADE_BROADCAST_DATA_CACHE);
                processCacheRollData(msg, schemeRollMapping.get(Integer.valueOf(params[0])));
                if (!SchemeTypeEnum.EXPERIENCE.getValue().equals(Integer.valueOf(params[0])) && !SchemeTypeEnum.YING
                        .getValue().equals(Integer.valueOf(params[0])) && TradeActionEnum.PROFIT.getValue()
                        .equals(params[1]) && Integer.valueOf(params[4]) > 100) {
                    processCacheRollData(msg, SystemConstants.FASHION_ROLL_DATA_CACHE);
                }
            }
        } catch (Exception e) {
            logger.error("存储交易数据错误", e);
            logger.error("传入数据为" + obj.toString());
        }
    }

    /**
     * 先进先出保存至缓存
     *
     * @param str
     * @param cacheName
     */
    private void processCacheRollData(String str, String cacheName) {
        LinkedList<String> dataList = cacheService.get(cacheName);
        if (dataList != null && dataList.size() + 1 <= CACHE_SIZE) {
            dataList.addFirst(str);
        } else if (dataList != null && dataList.size() + 1 > CACHE_SIZE) {
            dataList.removeLast();
            dataList.addFirst(str);
        } else if (dataList == null) {
            dataList = new LinkedList<String>();
            dataList.addFirst(str);
        }
        cacheService.set(cacheName, dataList, EXPIRE_TIME);
    }

    /**
     * @param investDealing
     * @return
     */
    private String buildTradeStr(InvestDealing investDealing) {
        //[ProductCode, action.BUY, 用户名，股票代码|股票名称, 单位, 价格, 时间 ]
        Integer schemeId = investDealing.getSchemeId();
        if (schemeId != null) {
            Scheme scheme = schemeBizService.getById(schemeId);
            if (scheme != null) {
                String type = InvestDealingTypeEnum.getByValue(investDealing.getType()).getMessage();
                String actionType = TradeActionEnum.getByMessage(type).getValue();
                User user = userService.getById(scheme.getUserId());
                String stockName = stockDataService.getStockNameByCode(investDealing.getCode());
                Assert.isTrue(StringUtils.isNotBlank(stockName), "股票名称不能为空");
                Integer number = investDealing.getNumber();
                BigDecimal price = investDealing.getPrice();
                Date time = investDealing.getTime();
                return scheme.getType() + "," + actionType + "," + user.getUsername() + "," + investDealing.getCode()
                        + "|" + stockName + "," + number + "," + price + "," + time.getTime();

            }
        }

        return null;
    }

    private InvestDealing convertFromTradeRecordItem(TradeRecordItem tradeRecordItem) {
        InvestDealing investDealing = null;
        try {
            investDealing = new InvestDealing();
            investDealing.setSerialNo(tradeRecordItem.getSerialId());
            investDealing.setSchemeId(Integer.valueOf(tradeRecordItem.getScheamId()));
            investDealing.setUserId(getUserId(Integer.parseInt(tradeRecordItem.getScheamId())));
            investDealing.setType(convertEntrustBs(tradeRecordItem.getEntrustBs()));
            investDealing.setCode(tradeRecordItem.getStockCode());
            investDealing.setName(tradeRecordItem.getStockName());
            investDealing.setPrice(new BigDecimal(tradeRecordItem.getBusinessPrice()));
            investDealing.setNumber(Integer.valueOf(tradeRecordItem.getBusinessAmount()));
            investDealing.setAmount(new BigDecimal(tradeRecordItem.getBusinessBalance()));
            investDealing.setTime(format.parse(getDateStr(tradeRecordItem)));

            investDealing.setTotalFee(BigDecimal.ZERO);
            investDealing.setCommission(BigDecimal.ZERO);
            investDealing.setTransferFee(BigDecimal.ZERO);
            investDealing.setStampTax(BigDecimal.ZERO);
            investDealing.setTradingFee(BigDecimal.ZERO);
            investDealing.setIncome(BigDecimal.ZERO);
            investDealing.setIncomeRatio(0.0);
            investDealing.setHoldTime(0);
            investDealing.setCreateTime(new Date());
            investDealing.setRemark("");
        } catch (Exception e) {
            logger.error("转换出错", e);
            logger.error("数据为", tradeRecordItem);
        }
        return investDealing;
    }

    private Integer getUserId(int schemeId) {
        String key = SCHEME_ID_2_USER_ID_KEY_PREFIX + schemeId;
        Integer userId = cacheService.get(key);
        if (userId == null || userId == 0) {
            userId = schemeBizService.getUserIdBySchemeId(schemeId);
            cacheService.set(key, userId, 60 * 60 * 24);
        }
        return userId;
    }

    private Integer convertEntrustBs(String entrust) throws Exception {
        Integer value = Integer.valueOf(entrust);
        if (value == 1201) {
            return InvestDealingTypeEnum.BUY.getValue();
        } else if (value == 1202) {
            return InvestDealingTypeEnum.SELL.getValue();
        } else {
            throw new Exception("转化交易类型出错");
        }
    }

    private String getDateStr(TradeRecordItem tradeRecordItem) throws Exception {
        if (tradeRecordItem.getBusinessTime().length() == 5) {
            return tradeRecordItem.getDate() + "0" + tradeRecordItem.getBusinessTime();
        } else if (tradeRecordItem.getBusinessTime().length() == 6) {
            return tradeRecordItem.getDate() + tradeRecordItem.getBusinessTime();
        } else {
            throw new Exception("日期无法解析");
        }
    }

    public void destroy() throws Exception {
        if (myExecutorService != null) {
            myExecutorService.shutdown();
        }
    }
}
