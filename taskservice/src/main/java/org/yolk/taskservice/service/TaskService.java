package org.yolk.taskservice.service;

import org.yolk.common.service.BaseSoapService;
import org.yolk.common.task.Task;

/**
 * @author Liang Chenye
 * @version $Id: TaskService, v 0.1 2015/8/11 13:52
 */

public interface TaskService extends BaseSoapService {

    /**
     * Submit task for executing.
     *
     * @param task The task.
     */
    void submit(Task task);

}
