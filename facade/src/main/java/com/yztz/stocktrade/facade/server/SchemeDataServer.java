/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package com.yztz.stocktrade.facade.server;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import org.yolk.common.server.BaseServer;
import org.yolk.common.service.BaseService;
import org.yolk.common.constants.SystemConstants;
import com.yztz.stocktrade.biz.system.SystemConfig;
import com.yztz.stocktrade.facade.scheduledtask.StoreSchemeUnitDailyScheduleTask;
import com.yztz.stocktrade.facade.servicefacade.SchemeDataServiceFacadeType;

/**
 * @author Liang Chenye
 * @version $Id: SchemeDataServer, v 0.1 2015/6/18 13:59
 */
@Component
public class SchemeDataServer extends BaseServer {

    private static final Logger log = LoggerFactory.getLogger(SchemeDataServer.class);

    @Autowired
    private StoreSchemeUnitDailyScheduleTask storeSchemeUnitDailyScheduleTask;

    @Override
    public List<BaseService> getService(ApplicationContext context) {
        List<BaseService> services = Lists.newArrayList();
        for (SchemeDataServiceFacadeType type : SchemeDataServiceFacadeType.values()) {
            BaseService service = (BaseService) context.getBean(type.getClazz());
            services.add(service);
        }
        return services;
    }

    @Override
    public void beforeStart() {
        super.beforeStart();
        setBaseUrl(SystemConfig.get(SystemConstants.STOCK_TRADE_DATA_SERVER_BASE_URL));
        setPort(SystemConfig.getInt(SystemConstants.STOCK_TRADE_DATA_SERVER_PORT));
    }

    @Override
    public void afterStart() {
        super.afterStart();
        storeSchemeUnitDailyScheduleTask.start();
    }

    @Override
    public void beforeStop() {
        super.beforeStop();
        storeSchemeUnitDailyScheduleTask.stop();
    }
}
