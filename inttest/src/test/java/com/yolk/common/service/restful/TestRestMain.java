package com.yolk.common.service.restful;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

/**
 * @author Liang Chenye
 * @version $Id: TestRestMain, v 0.1 2015/9/29 17:51
 */

public class TestRestMain {

    public static void main(String[] args) {
        TestRestful service = new TestRestful();

        // Service instance
        JAXRSServerFactoryBean restServer = new JAXRSServerFactoryBean();
        restServer.setServiceBean(service);
        restServer.setAddress("http://localhost:9999/");
        restServer.create();

        System.out.println("started");
    }

}
