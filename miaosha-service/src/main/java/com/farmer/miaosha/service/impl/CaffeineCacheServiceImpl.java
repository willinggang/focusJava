package com.farmer.miaosha.service.impl;

import com.farmer.miaosha.service.CaffeineCacheService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: FocusingJava
 * @description: 缓存服务
 * @author: FarmerSun
 * @create: 2020-01-03 18:07
 */
@Service
public class CaffeineCacheServiceImpl implements CaffeineCacheService {
    @Resource
    CacheManager caffeineCacheManager;

    private final static String DEFAULT_CACHE = "mobileCode";

    @Override
    public <T> T getValue(Object key) {
        if (key == null) {
            return null;
        }

        Cache cache = caffeineCacheManager.getCache(DEFAULT_CACHE);
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

        Cache cache = caffeineCacheManager.getCache(cacheName);
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

        Cache cache = caffeineCacheManager.getCache(DEFAULT_CACHE);
        if (cache != null) {
            cache.put(key, value);
        }
    }

    @Override
    public void putValue(String cacheName, Object key, Object value) {
        if (cacheName == null || key == null || value == null) {
            return;
        }

        Cache cache = caffeineCacheManager.getCache(cacheName);
        if (cache != null) {
            cache.put(key, value);
        }
    }

    @Override
    public void evict(String cacheName, Object key) {
        if (key == null){
            return;
        }
        Cache cache = caffeineCacheManager.getCache(StringUtils.isEmpty(cacheName)?DEFAULT_CACHE:cacheName);
        if (cache != null) {
            cache.evictIfPresent(key);
        }
    }
}
