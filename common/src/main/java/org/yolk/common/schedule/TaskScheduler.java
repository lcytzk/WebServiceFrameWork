/**
 * Yztz.cn Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package org.yolk.common.schedule;

/**
 * @author Liang Chenye
 * @version $Id: TaskScheduler, v 0.1 2015/9/28 9:39
 */

public interface TaskScheduler {

    void scheduleTask(ScheduleTask task);

    void start();

    void stop();
}
