package org.yolk.common.schedule;

/**
 * @author Liang Chenye
 * @version $Id: BaseScheduledTask, v 0.1 2015/6/23 15:19
 */

public abstract class BaseScheduleTask implements ScheduleTask {

    private boolean stop = true;

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void start() {
        setStop(false);
    }

    public void stop() {
        setStop(true);
    }
}
