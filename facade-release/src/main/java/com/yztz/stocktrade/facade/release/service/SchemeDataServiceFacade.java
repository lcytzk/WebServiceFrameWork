/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package com.yztz.stocktrade.facade.release.service;

import java.math.BigDecimal;
import javax.jws.WebService;

import com.yztz.stocktrade.facade.release.dto.PageList;
import com.yztz.stocktrade.facade.release.dto.Result;
import com.yztz.stocktrade.facade.release.dto.SchemeInfo;

/**
 * @author Liang Chenye
 * @version $Id: SchemeDataService, v 0.1 2015/6/18 13:17
 */
@WebService(serviceName = "schemeDataService", portName = "schemeDataServicePort", targetNamespace = "http://stocktrade.yztz.com/")
public interface SchemeDataServiceFacade {

    /**
     * Get scheme by page.
     *
     * @param pageSize The size of one page less than 10,000.
     * @param pageIndex The index of page.
     * @return A list of schemes.
     */
    Result<PageList<SchemeInfo>> getSchemeInfoByPage(int pageSize, int pageIndex);

    /**
     * Get scheme.
     *
     * @param schemeId The id of scheme.
     * @return Scheme, null if not exist.
     */
    Result<SchemeInfo> getSchemeInfo(int schemeId);

    /**
     * Get charge money.
     *
     * @param schemeId
     * @return Sum of all charge for the schemeId.
     */
    Result<BigDecimal> getChargeMoney(int schemeId);
}
