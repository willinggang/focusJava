package com.farmer.miaosha.service.impl;

import com.farmer.miaosha.service.CaffeineCacheService;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @program: FocusingJava
 * @description: 缓存服务
 * @author: FarmerSun
 * @create: 2020-01-03 18:07
 */
@Service
public class CaffeineCacheServiceImpl implements CaffeineCacheService {

    CaffeineCacheManager cacheManager = new CaffeineCacheManager();

    private final static String DEFAULT_CACHE = "mobileCode";

    @PostConstruct
    public void initCacheManager() {
        cacheManager.setCaffeine(
                Caffeine.newBuilder().
                        expireAfterWrite(1, TimeUnit.MINUTES).
                        maximumSize(1000)
                        .initialCapacity(10));
    }

    @Override
    public <T> T getValue(Object key) {
        if (key == null) {
            return null;
        }

        Cache cache = cacheManager.getCache(DEFAULT_CACHE);
        if (cache != null) {
            Cache.ValueWrapper wrapper = cache.get(key);
            if (wrapper != null)
                return (T) wrapper.get();
        }

        return null;
    }

    @Override
    public <T> T getValue(String cacheName, Object key) {
        if (cacheName == null || key == null) {
            return null;
        }

        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            Cache.ValueWrapper wrapper = cache.get(key);
            if (wrapper != null)
                return (T) wrapper.get();
        }

        return null;
    }

    @Override
    public void putValue(Object key, Object value) {
        if (key == null || value == null) {
            return;
        }

        Cache cache = cacheManager.getCache(DEFAULT_CACHE);
        if (cache != null) {
            cache.put(key, value);
        }
    }

    @Override
    public void putValue(String cacheName, Object key, Object value) {
        if (cacheName == null || key == null || value == null) {
            return;
        }

        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.put(key, value);
        }
    }

    @Override
    public void evict(String cacheName, Object key) {
        if (key == null) {
            return;
        }
        Cache cache = cacheManager.getCache(StringUtils.isEmpty(cacheName) ? DEFAULT_CACHE : cacheName);
        if (cache != null) {
            cache.evictIfPresent(key);
        }
    }
}
