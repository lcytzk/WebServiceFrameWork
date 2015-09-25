/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package com.yztz.stocktrade.facade.scheduledtask;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.yztz.common.page.PageList;
import com.yztz.common.util.DateUtil;
import com.yztz.model.Scheme;
import com.yztz.model.enums.SchemeStatusEnum;
import com.yztz.model.trading.TradingUnitAsset;
import org.yolk.common.shedule.BaseScheduleTask;
import com.yztz.stocktrade.biz.proxy.TradeCenterProxy;
import com.yztz.stocktrade.biz.statistic.UserInvestStatTask;
import com.yztz.stocktrade.biz.statistic.UserSchemeStatTask;
import com.yztz.stocktrade.biz.util.HolidayUtil;
import com.yztz.stocktrade.dao.SchemeDAO;
import com.yztz.stocktrade.dao.TradingUnitAssetSerializationDAO;
import com.yztz.stocktrade.dao.model.TradingUnitAssetSerialization;
import com.yztz.stocktrade.dao.query.SchemeQuery;

/**
 * @author Liang Chenye
 * @version $Id: StoreSchemeUnitDaily, v 0.1 2015/6/23 15:10
 */
@Component
public class StoreSchemeUnitDailyScheduleTask extends BaseScheduleTask {

    private static final Logger logger = LoggerFactory.getLogger(StoreSchemeUnitDailyScheduleTask.class);

    private Date closeTime;

    @Autowired
    private SchemeDAO schemeDAO;

    @Autowired
    private TradingUnitAssetSerializationDAO tradingUnitAssetSerializationDAO;

    @Autowired
    private UserInvestStatTask userInvestStatTask;

    @Autowired
    private UserSchemeStatTask userSchemeStatTask;

    @Autowired
    private TradeCenterProxy tradeCenterProxy;

    public void run() {
        if (!isStop()) {
            if (logger.isInfoEnabled()) {
                logger.info("Begin StoreSchemeUnitDailyScheduleTask.");
            }
            storeTradingUnitAssetData();
            doStatisticTask();
        }
    }

    private void storeTradingUnitAssetData() {
        if (logger.isInfoEnabled()) {
            logger.info("Do store schemeUnitData!");
        }
        closeTime = HolidayUtil.getMarketCloseTime(new Date());
        SchemeQuery schemeQuery = new SchemeQuery();
        schemeQuery.setStatus(Lists.newArrayList(SchemeStatusEnum.LOAN.getValue()));
        PageList<Scheme> schemes;
        int pageIndex = 1;
        do {
            schemes = schemeDAO.getByPage(schemeQuery, pageIndex, 1000);
            for (Scheme scheme : schemes) {
                TradingUnitAsset asset = tradeCenterProxy.getTradingUnitAssetSnapshot(scheme.getId(), true);
                if ((asset != null) && isTimeValid(asset.getVersion())) {
                    tradingUnitAssetSerializationDAO
                            .insert(convertTradingUnitAssetToSerialization(asset, scheme.getId()));
                }
            }
            pageIndex++;
        } while (schemes.getPaginator().getPage() != schemes.getPaginator().getPages());
    }

    private void doStatisticTask() {
        if (logger.isInfoEnabled()) {
            logger.info("Do statistic task!");
        }
        userSchemeStatTask.doTask();
        userInvestStatTask.doTask();
    }

    private TradingUnitAssetSerialization convertTradingUnitAssetToSerialization(TradingUnitAsset asset, int schemeId) {
        TradingUnitAssetSerialization tradingUnitAssetSerialization = new TradingUnitAssetSerialization();
        tradingUnitAssetSerialization.setSchemeId(schemeId);
        tradingUnitAssetSerialization.setDate(new Date());
        Gson gson = new GsonBuilder().serializeNulls().create();
        tradingUnitAssetSerialization.setContent(gson.toJson(asset));
        return tradingUnitAssetSerialization;
    }

    private boolean isTimeValid(long version) {
        Date date = new Date(version);
        return DateUtil.compare(date, closeTime) >= 0;
    }
}
