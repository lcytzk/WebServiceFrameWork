package com.yolk.common.server;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.springframework.util.Assert;
import org.yolk.common.server.BaseSoapServer;
import org.yolk.common.server.impl.BaseSoapServerImpl;

import com.yolk.common.service.soap.TestBaseSoapService;
import com.yolk.common.service.soap.TestBaseSoapServiceImpl;

/**
 * @author Liang Chenye
 * @version $Id: TestBaseSoapServer, v 0.1 2015/9/29 15:38
 */

public class TestBaseSoapServer {

    public static void main(String[] args) {
        BaseSoapServer baseSoapServer = new BaseSoapServerImpl();
        baseSoapServer.addService(new TestBaseSoapServiceImpl());
        baseSoapServer.startServer();
        System.out.print("started!");
    }

    @Test
    public void createClient() {
        JaxWsProxyFactoryBean src = new JaxWsProxyFactoryBean();
        src.setServiceClass(TestBaseSoapService.class);
        src.setAddress("http://localhost:8182/testBaseSoapService");
        TestBaseSoapService client = (TestBaseSoapService) src.create();
        Assert.isTrue(client.HelloWorld().equals("Hello World!"));
    }

}
