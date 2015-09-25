/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package com.yztz.stocktrade.facade.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yztz.stocktrade.facade.server.SchemeDataServer;

/**
 * @author Liang Chenye
 * @version $Id: BootStrap, v 0.1 2015/6/24 14:27
 */

public class BootStrap {

    private static final Logger log = LoggerFactory.getLogger(BootStrap.class);

    public static void main(String[] args) {
        log.info("Begin server!");
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SchemeDataServer server = context.getBean(SchemeDataServer.class);
        server.startServer(context);
        context.registerShutdownHook();
        log.info("Server started!");
    }
}
