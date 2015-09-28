package org.yolk.common.server;

import java.util.List;

import org.yolk.common.service.BaseSoapService;

/**
 * @author Liang Chenye
 * @version $Id: BaseSoapService, v 0.1 2015/9/28 17:16
 */

public interface BaseSoapServer extends BaseServer {

    void addService(BaseSoapService service);

    void addServices(List<BaseSoapService> services);

}
