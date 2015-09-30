/**
 * Yztz.cn Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package org.yolk.common.schedule.impl;

import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.yolk.common.schedule.TaskScheduler;
import org.yolk.common.schedule.util.TaskSchedulerUtil;

/**
 * @author Liang Chenye
 * @version $Id: SchedulerFactoryBean, v 0.1 2015/9/28 9:32
 */
public class TaskSchedulerImpl extends SchedulerFactoryBean implements TaskScheduler {

    // Set properties and triggers.
    public TaskSchedulerImpl() {
        setQuartzProperties(TaskSchedulerUtil.getDefaultProperties());
        setTriggers(new Trigger[0]);
    }

    @Override
    public void destroy() throws SchedulerException {
        super.destroy();
    }
}
