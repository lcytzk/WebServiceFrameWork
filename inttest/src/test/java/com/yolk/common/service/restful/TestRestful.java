package com.yolk.common.service.restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author Liang Chenye
 * @version $Id: TestRestful, v 0.1 2015/9/29 17:35
 */
public class TestRestful {

    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String helloWorld() {
        return "Hello";
    }
}
