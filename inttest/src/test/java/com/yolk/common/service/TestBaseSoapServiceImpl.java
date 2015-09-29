package com.yolk.common.service;

import javax.jws.WebService;

/**
 * @author Liang Chenye
 * @version $Id: TestBaseSoapServiceImpl, v 0.1 2015/9/29 16:29
 */
@WebService(serviceName = "testBaseSoapService", portName = "testBaseSoapServicePort", targetNamespace = "http://yolk.yolk.org/")
public class TestBaseSoapServiceImpl implements TestBaseSoapService {

    public String HelloWorld() {
        return "Hello World!";
    }

    public String getName() {
        return "testBaseSoapService";
    }
}
