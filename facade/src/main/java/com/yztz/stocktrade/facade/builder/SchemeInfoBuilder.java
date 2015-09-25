/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package com.yztz.stocktrade.facade.builder;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yztz.common.util.DateUtil;
import com.yztz.stocktrade.dao.UserDAO;
import com.yztz.stocktrade.dao.model.FacadeScheme;
import com.yztz.stocktrade.facade.release.dto.SchemeInfo;

/**
 * @author Liang Chenye
 * @version $Id: SchemeInfoBuilder, v 0.1 2015/6/23 10:21
 */
@Component
public class SchemeInfoBuilder {

    private static final Logger log = LoggerFactory.getLogger(SchemeInfoBuilder.class);

    private static final Integer DAY_TYPE = 100;

    private BigDecimal ration = new BigDecimal(0.3);

    @Autowired
    private UserDAO userDAO;

    public SchemeInfo buildWithScheme(FacadeScheme scheme) {
        if (scheme == null) {
            return null;
        }
        SchemeInfo schemeInfo = new SchemeInfo();
        schemeInfo.setId(scheme.getId());
        schemeInfo.setUserId(scheme.getUserId());
        schemeInfo.setUserName(scheme.getUserName());
        schemeInfo.setMatchId(scheme.getMatchId());
        schemeInfo.setConnectedUserId(scheme.getConnectedUserId());
        schemeInfo.setType(scheme.getType());
        schemeInfo.setStatus(scheme.getStatus());
        schemeInfo.setPrincipal(scheme.getPrincipal());
        schemeInfo.setLever(scheme.getLever());
        schemeInfo.setMoney(scheme.getMoney());
        schemeInfo.setOpenLine(scheme.getOpenLine());
        schemeInfo.setWarningLine(scheme.getWarningLine());
        schemeInfo.setPrincipalAdditional(scheme.getPrincipalAdditional());
        schemeInfo.setPrincipalWithdraw(scheme.getPrincipalWithdraw());
        schemeInfo.setProfitWithdraw(scheme.getProfitWithdraw());
        schemeInfo.setInitiateTime(scheme.getInitiateTime());
        schemeInfo.setAccountTime(scheme.getAccountTime());
        schemeInfo.setInterestTime(scheme.getInterestTime());
        schemeInfo.setFinishApplyTime(scheme.getFinishApplyTime());
        schemeInfo.setFinishTime(scheme.getFinishTime());
        schemeInfo.setSuspendedValue(scheme.getSuspendedValue());
        schemeInfo.setInvestMargin(scheme.getInvestMargin());
        boolean enoughInvestMargin = true;
        if (!scheme.getSuspendedValue().equals(BigDecimal.ZERO)) {
            enoughInvestMargin = scheme.getInvestMargin().compareTo(scheme.getSuspendedValue().multiply(ration)) >= 0;
        }
        schemeInfo.setEnoughInvestMargin(enoughInvestMargin);
        // TODO time? finish time?
        if (scheme.getType().equals(DAY_TYPE) && !(scheme.getFinishApplyTime().equals(scheme.getInitiateTime()))) {
            Date date = DateUtil.addDays(new Date(), 1);
            schemeInfo.setInterestEndTime(date);
        } else {
            schemeInfo.setInterestEndTime(scheme.getInterestEndTime());
        }
        return schemeInfo;
    }
}
