/**
 * Yztz.cn Inc.
 * Copyright (c) 2013-2014 All Rights Reserved.
 */
package org.yolk.common.service;

/**
 * 缓存服务接口
 *
 * @author elliott
 * @version $Id: CacheService.java, v 0.1 2014-3-19 下午3:52:04 elliott Exp $
 */
public interface CacheService {

    /**
     * 获取对象
     *
     * @param key
     * @return
     */
    public <T> T get(final String key);

    /**
     * 将对象放入缓存
     *
     * @param key
     * @param obj
     * @param expire 过期时间,单位S
     */
    public void set(final String key, final Object obj, final int expire);

    /**
     * 通过key删除
     *
     * @param key
     */
    public void delete(String key);

    /**
     * 服务是否可用
     *
     * @return
     */
    public boolean isAvailable();

}
