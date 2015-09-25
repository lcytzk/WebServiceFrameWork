/**
 * Yztz.cn Inc.
 * Copyright (c) 2013-2014 All Rights Reserved.
 */
package org.yolk.common.service.impl;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.yolk.common.service.CacheService;

/**
 * Memcached缓存服务
 *
 * @author elliott
 * @version $Id: MemcachedService.java, v 0.1 2014-3-19 下午3:59:23 elliott Exp $
 */
public class MemcachedService implements CacheService {

    private static final Logger logger = LoggerFactory.getLogger(MemcachedService.class);

    private final AtomicLong crashCounter = new AtomicLong(0);

    private MemcachedClient memcachedClient;

    private volatile boolean available = true;

    public <T> T get(String key) {
        if (isAvailable()) {
            T t = null;
            try {
                t = this.memcachedClient.get(key);
            } catch (TimeoutException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Memcached Time out：key：{}", key, e);
                }
            } catch (InterruptedException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Memcached InterruptedException：key：{}", key, e);
                }
            } catch (MemcachedException e) {
                recordError();
                if (logger.isWarnEnabled()) {
                    logger.warn("Memcached Unabailable", e);
                }
            }
            return t;
        }
        return null;
    }

    public void set(String key, Object obj, int expire) {
        if (isAvailable()) {
            try {
                memcachedClient.set(key, expire, obj);
            } catch (TimeoutException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Memcached Time out：key：{},obj:{}", key, obj, e);
                }
                logger.error("TimeoutException", e);
            } catch (InterruptedException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Memcached InterruptedException：key：{},obj:{}", key, obj, e);
                }
            } catch (MemcachedException e) {
                recordError();
                if (logger.isWarnEnabled()) {
                    logger.warn("Memcached Unabailable", e);
                }
            }
        }
    }

    public void delete(String key) {
        if (isAvailable()) {
            try {
                memcachedClient.delete(key);
            } catch (TimeoutException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Memcached Time out：key：{}", key, e);
                }
            } catch (InterruptedException e) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Memcached InterruptedException：key：{}", key, e);
                }
            } catch (MemcachedException e) {
                recordError();
                if (logger.isWarnEnabled()) {
                    logger.warn("Memcached Unabailable", e);
                }
            }
        }
    }

    protected void recordError() {
        crashCounter.addAndGet(1);
    }

    public MemcachedClient getMemcachedClient() {
        return memcachedClient;
    }

    public void setMemcachedClient(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    public boolean isAvailable() {
        return available && this.memcachedClient != null && !this.memcachedClient.isShutdown();
    }

}
