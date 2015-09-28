/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package org.yolk.taskservice.service;

import org.yolk.common.service.BaseService;
import org.yolk.common.task.Task;

/**
 * @author Liang Chenye
 * @version $Id: TaskService, v 0.1 2015/8/11 13:52
 */

public interface TaskService extends BaseService {

    /**
     * Submit task for executing.
     *
     * @param task The task.
     */
    void submit(Task task);

}
