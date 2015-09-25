/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package com.yztz.stocktrade.facade.servicefacade;

import com.yztz.stocktrade.facade.release.service.SchemeDataServiceFacade;
import com.yztz.stocktrade.facade.release.service.UserStatisticServiceFacade;

/**
 * @author Liang Chenye
 * @version $Id: ServiceType, v 0.1 2015/6/18 15:30
 */

public enum SchemeDataServiceFacadeType {

    SCHEME_DATA_SERVICE("schemeDataService", SchemeDataServiceFacade.class),
    USER_STATISTIC_SERVICE("userStatisticService", UserStatisticServiceFacade.class);

    private String name;

    private Class<?> clazz;

    SchemeDataServiceFacadeType(String name, Class<?> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
