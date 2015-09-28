package org.yolk.common.task.processor;

import org.yolk.common.task.Task;

/**
 * @author Liang Chenye
 * @version $Id: Processor, v 0.1 2015/8/13 17:23
 */

public interface Processor {

    /**
     * Do something before execute task.
     *
     * @param task The task.
     */
    void beforeTask(Task task);

    /**
     * Do something after execute task.
     *
     * @param task The task.
     */
    void afterTask(Task task);
}
