package org.yolk.common.server.impl;

import java.util.HashMap;
import java.util.List;
import javax.xml.ws.Endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yolk.common.server.BaseSoapServer;
import org.yolk.common.service.BaseSoapService;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author Liang Chenye
 * @version $Id: BaseSoapServerImpl, v 0.1 2015/9/28 17:23
 */
public class BaseSoapServerImpl implements BaseSoapServer {

    private static final Logger logger = LoggerFactory.getLogger(BaseSoapServer.class);

    private String baseUrl = "http://0.0.0.0";

    private List<BaseSoapService> soapServices = Lists.newArrayList();

    private int port = 8182;

    private HashMap<Class, Endpoint> endpoints = Maps.newHashMap();

    public void beforeStart() {
        logger.info("Add stop hook!");
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

    public void startServer() {
        beforeStart();
        doStart();
        afterStart();
    }

    public void doStart() {
        List<BaseSoapService> services = getService();
        for (BaseSoapService service : services) {
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

    public List<BaseSoapService> getService() {
        return soapServices;
    }

    public void publish(BaseSoapService service) {
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

    public void addService(BaseSoapService service) {
        soapServices.add(service);
    }

    public void addServices(List<BaseSoapService> services) {
        soapServices.addAll(services);
    }
}
