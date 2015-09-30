package com.yolk.common.service.soap;

import javax.jws.WebService;

import org.yolk.common.service.BaseSoapService;

/**
 * @author Liang Chenye
 * @version $Id: TestBaseSoapService, v 0.1 2015/9/29 15:39
 */
@WebService(serviceName = "testBaseSoapService", portName = "testBaseSoapServicePort", targetNamespace = "http://yolk.yolk.org/")
public interface TestBaseSoapService extends BaseSoapService {
    String HelloWorld();
}
