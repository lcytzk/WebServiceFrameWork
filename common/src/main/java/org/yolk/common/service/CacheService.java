package org.yolk.common.service;

public interface CacheService {

    <T> T get(final String key);

    void set(final String key, final Object obj, final int expire);

    void delete(String key);

    boolean isAvailable();

}
