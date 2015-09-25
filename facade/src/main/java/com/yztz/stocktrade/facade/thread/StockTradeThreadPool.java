/**
 * Yztz.com Inc.
 * Copyright (c) 2013-2015 All Rights Reserved.
 */

package com.yztz.stocktrade.facade.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Liang Chenye
 * @version $Id: ThreadPool, v 0.1 2015/6/26 14:50
 */
public class StockTradeThreadPool {

    private final int defaultCorePoolSize = 1;

    private final int defaultMaximumPoolSize = 30;

    private final long aliveTime = 60L;

    private final TimeUnit timeUnit = TimeUnit.SECONDS;

    private final int defaultQueueSize = 30;

    private ThreadPoolExecutor threadPoolExecutor;

    private BlockingQueue queue = new ArrayBlockingQueue<Runnable>(defaultQueueSize);

    public StockTradeThreadPool() {
        threadPoolExecutor = new ThreadPoolExecutor(defaultCorePoolSize,
                defaultMaximumPoolSize, aliveTime, timeUnit, queue);
    }

    public void submit(Runnable task) {
        threadPoolExecutor.execute(task);
    }

    public void shutdown() {
        threadPoolExecutor.shutdown();
    }
}
