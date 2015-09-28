/**
 * Yztz.cn Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package org.yolk.common.schedule.impl;

import org.quartz.SchedulerException;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author Liang Chenye
 * @version $Id: SchedulerFactoryBean, v 0.1 2015/9/28 9:32
 */
@Component
public class TaskSchedulerImpl extends SchedulerFactoryBean {

    public TaskSchedulerImpl() {}



    @Override
    public void destroy() throws SchedulerException {
        super.destroy();
    }
}
