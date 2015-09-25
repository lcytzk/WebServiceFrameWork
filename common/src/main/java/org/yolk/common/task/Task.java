/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package org.yolk.common.task;

import com.yztz.stockmarket.common.task.processor.Processor;

/**
 * @author Liang Chenye
 * @version $Id: Task, v 0.1 2015/8/11 13:32
 */

public abstract class Task implements Runnable {

    private Processor processor;

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    // TODO
    public void run() {
        processor.beforeTask(this);
        doRun();
        processor.afterTask(this);
    }

    public abstract void doRun();

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Task task = (Task) o;

        return !(processor != null ? !processor.equals(task.processor) : task.processor != null);

    }

    @Override
    public int hashCode() {
        return processor != null ? processor.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Task, {");
        sb.append("processor=");
        sb.append(processor);
        sb.append('}');
        return sb.toString();
    }
}
