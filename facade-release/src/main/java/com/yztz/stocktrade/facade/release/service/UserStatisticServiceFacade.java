/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 *
 */

package com.yztz.stocktrade.facade.release.service;

import javax.jws.WebService;

import com.yztz.stocktrade.facade.release.dto.Result;

/**
 * @author Liang Chenye
 * @version $Id: UserStatisticServiceFacade, v 0.1 2015/6/29 15:18
 */
@WebService(serviceName = "userStatisticService", portName = "userStatisticServicePort", targetNamespace = "http://stocktrade.yztz.com/")
public interface UserStatisticServiceFacade {

    /**
     * Correct user invest data.
     *
     * @param userId the user id.
     * @return Result of this operation.
     */
    Result<Integer> correctInvestStat(Integer userId);
}
