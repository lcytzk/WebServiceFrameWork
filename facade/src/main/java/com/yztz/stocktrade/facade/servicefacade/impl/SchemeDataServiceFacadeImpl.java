/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package com.yztz.stocktrade.facade.servicefacade.impl;

import java.math.BigDecimal;
import java.util.List;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import com.yztz.FCServer.model.UserAccount;
import com.yztz.admin.common.service.intergration.trading.TradingServiceFacadeClient;
import com.yztz.model.enums.SchemeStatusEnum;
import org.yolk.common.service.BaseService;
import com.yztz.stocktrade.biz.service.FacadeSchemeBizService;
import com.yztz.stocktrade.dao.FundRecordDAO;
import com.yztz.stocktrade.dao.model.FacadeScheme;
import com.yztz.stocktrade.dao.query.SchemeQuery;
import com.yztz.stocktrade.facade.builder.SchemeInfoBuilder;
import com.yztz.stocktrade.facade.release.dto.PageList;
import com.yztz.stocktrade.facade.release.dto.Paginator;
import com.yztz.stocktrade.facade.release.dto.Result;
import com.yztz.stocktrade.facade.release.dto.SchemeInfo;
import com.yztz.stocktrade.facade.release.service.SchemeDataServiceFacade;
import com.yztz.stocktrade.facade.servicefacade.errorcode.ErrorCode;

/**
 * @author Liang Chenye
 * @version $Id: SchemeDataServiceImpl, v 0.1 2015/6/18 13:17
 */
@Component
@WebService(serviceName = "schemeDataService", portName = "schemeDataServicePort", targetNamespace = "http://stocktrade.yztz.com/")
public class SchemeDataServiceFacadeImpl implements SchemeDataServiceFacade, BaseService {

    private Logger log = LoggerFactory.getLogger(SchemeDataServiceFacadeImpl.class);

    private static final String NAME = "schemeDataService";

    @Autowired
    private FacadeSchemeBizService facadeSchemeBizService;

    @Autowired
    private SchemeInfoBuilder schemeInfoBuilder;

    @Autowired
    private FundRecordDAO fundRecordDAO;

    @Autowired
    private TradingServiceFacadeClient tradingServiceFacadeClient;

    public Result<PageList<SchemeInfo>> getSchemeInfoByPage(int pageSize, int pageIndex) {
        try {
            SchemeQuery query = getQuery();
            if (query.getAccountList().size() < 1) {
                throw new Exception("主账户为空");
            }
            com.yztz.common.page.PageList<FacadeScheme> schemes =
                    facadeSchemeBizService.getByPage(query, pageIndex, pageSize);
            PageList<SchemeInfo> schemeInfos = new PageList<SchemeInfo>();
            com.yztz.common.page.Paginator oldPaginator = schemes.getPaginator();
            Paginator paginator =
                    new Paginator(oldPaginator.getPage(), oldPaginator.getPageSize(), oldPaginator.getItems());
            schemeInfos.setPaginator(paginator);
            for (FacadeScheme scheme : schemes) {
                schemeInfos.add(schemeInfoBuilder.buildWithScheme(scheme));
            }
            Result<PageList<SchemeInfo>> result = new Result<PageList<SchemeInfo>>();
            result.setResult(schemeInfos);
            return result;
        } catch (Exception e) {
            log.error("查询出错", e);
            Result<PageList<SchemeInfo>> result = new Result<PageList<SchemeInfo>>();
            result.setErrorCode(ErrorCode.SYSTEM_ERROR.getCode());
            result.setErrorMsg(ErrorCode.SYSTEM_ERROR.getMsg() + e.getMessage());
            return result;
        }
    }

    public Result<SchemeInfo> getSchemeInfo(int schemeId) {
        try {
            Result<SchemeInfo> result = new Result<SchemeInfo>();
            SchemeInfo schemeInfo = schemeInfoBuilder.buildWithScheme(facadeSchemeBizService.getById(schemeId));
            if (schemeInfo == null) {
                result.setErrorCode(ErrorCode.NO_SUCH_ITEM.getCode());
                result.setErrorMsg(ErrorCode.NO_SUCH_ITEM.getMsg());
                return result;
            } else {
                result.setResult(schemeInfo);
                return result;
            }
        } catch (Exception e) {
            log.error("查询出错", e);
            Result<SchemeInfo> result = new Result<SchemeInfo>();
            result.setErrorCode(ErrorCode.SYSTEM_ERROR.getCode());
            result.setErrorMsg(ErrorCode.SYSTEM_ERROR.getMsg());
            return result;
        }
    }

    public Result<BigDecimal> getChargeMoney(int schemeId) {
        try {
            BigDecimal money = fundRecordDAO.getReadyMoney(schemeId);
            if (money == null) {
                money = new BigDecimal(0);
            }
            return new Result<BigDecimal>(money);
        } catch (Exception e) {
            log.error("查询出错", e);
            Result<BigDecimal> result = new Result<BigDecimal>();
            result.setErrorCode(ErrorCode.SYSTEM_ERROR.getCode());
            result.setErrorMsg(ErrorCode.SYSTEM_ERROR.getMsg());
            return result;
        }
    }

    public String getName() {
        return NAME;
    }

    private SchemeQuery getQuery() {
        SchemeQuery query = new SchemeQuery();
        query.setStatus(Lists.newArrayList(SchemeStatusEnum.LOAN.getValue()));
        List<String> accounts = Lists.newArrayList();
        for (UserAccount userAccount : tradingServiceFacadeClient.getMainFundAccountList().getList()) {
            accounts.add(userAccount.getMainFundAccount());
        }
        query.setAccountList(accounts);
        return query;
    }
}
