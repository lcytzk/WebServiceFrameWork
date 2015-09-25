/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package org.yolk.common.server;

import java.util.HashMap;
import java.util.List;
import javax.xml.ws.Endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.google.common.collect.Maps;

import org.yolk.common.service.BaseService;

/**
 * @author Liang Chenye
 * @version $Id: BaseServer, v 0.1 2015/6/18 14:00
 */
public abstract class BaseServer {

    private String baseUrl;

    private int port;

    private Logger logger = LoggerFactory.getLogger(BaseServer.class);

    private HashMap<Class, Endpoint> endpoints = Maps.newHashMap();

    public void beforeStart() {
        logger.info("Add shutdown hook!");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                stopServer();
            }
        });
    }

    public void afterStart() {
    }

    public void beforeStop() {
    }

    public void afterStop() {
    }

    public void startServer(ApplicationContext context) {
        beforeStart();
        doStart(context);
        afterStart();
    }

    public void doStart(ApplicationContext context) {
        List<BaseService> services = getService(context);
        for (BaseService service : services) {
            publish(service);
        }
    }

    public void stopServer() {
        beforeStop();
        doStop();
        afterStop();
    }

    public void doStop() {
        logger.info("Stop server begin!");
        for (Class serviceName : endpoints.keySet()) {
            try {
                logger.info("Stop service" + serviceName);
                endpoints.get(serviceName).stop();
            } catch (Exception e) {
                logger.error("停止服务出错，服务为：", e);
            }
        }
        logger.info("Stop server end!");
    }

    abstract public List<BaseService> getService(ApplicationContext context);

    public void publish(BaseService service) {
        try {
            String url = baseUrl + ":" + port + "/" + service.getName();
            logger.info("publish:" + url);
            Endpoint endpoint = Endpoint.publish(url, service);
            if (endpoint == null) {
                throw new Exception("创建服务为null:" + service.getClass());
            }
            endpoints.put(service.getClass(), endpoint);
        } catch (Exception e) {
            logger.error("创建服务失败", e);
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
