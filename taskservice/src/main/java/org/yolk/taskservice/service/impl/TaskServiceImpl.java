/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */
package org.yolk.taskservice.service.impl;

import org.springframework.stereotype.Component;
import org.yolk.common.task.Task;
import org.yolk.common.threadpool.ThreadPool;
import org.yolk.taskservice.service.TaskService;

/**
 * @author Liang Chenye
 * @version $Id: TaskServiceImpl, v 0.1 2015/8/11 13:52
 */
@Component
public class TaskServiceImpl implements TaskService {

    private ThreadPool threadPool;

    public TaskServiceImpl() {
        threadPool = new ThreadPool();
    }

    public void submit(Task task) {
        threadPool.submit(task);
    }
}
