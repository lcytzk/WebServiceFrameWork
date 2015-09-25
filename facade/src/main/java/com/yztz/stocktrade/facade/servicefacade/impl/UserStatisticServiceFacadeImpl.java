/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package com.yztz.stocktrade.facade.servicefacade.impl;

import java.util.Date;
import java.util.List;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.yolk.common.service.BaseService;
import com.yztz.stocktrade.biz.service.InvestSchemeStatBizService;
import com.yztz.stocktrade.biz.service.InvestUserStatBizService;
import com.yztz.stocktrade.facade.release.dto.Result;
import com.yztz.stocktrade.facade.release.service.UserStatisticServiceFacade;

/**
 * @author Liang Chenye
 * @version $Id: UserStatisticServiceFacadeImppl, v 0.1 2015/6/29 15:21
 */
@Component
@WebService(serviceName = "userStatisticService", portName = "userStatisticServicePort", targetNamespace = "http://stocktrade.yztz.com/")
public class UserStatisticServiceFacadeImpl implements UserStatisticServiceFacade, BaseService {

    private static final String NAME = "userStatisticService";

    private Logger log = LoggerFactory.getLogger(UserStatisticServiceFacadeImpl.class);

    @Autowired
    private InvestSchemeStatBizService investSchemeStatBizService;

    @Autowired
    private InvestUserStatBizService investUserStatBizService;

    public Result<Integer> correctInvestStat(Integer userId) {
        Result<Integer> result = new Result<Integer>();
        try {
            List<Date> correctDate = investSchemeStatBizService.correctSchemeInvestStat(userId);
            for (Date date : correctDate) {
                investUserStatBizService.correctUserStat(userId, date);
            }
            result.setResult(0);
        } catch (Exception e) {
            log.error("Error when correctUserStat", e);
            result.setResult(-1);
        }
        return result;
    }

    public String getName() {
        return NAME;
    }
}
