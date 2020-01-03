package com.farmer.miaosha.service;

/**
 * @program: FocusingJava
 * @description: 缓存服务
 * @author: FarmerSun
 * @create: 2020-01-03 18:09
 */
public interface CaffeineCacheService {

    /**
     * 获取key对应value
     * @param key
     * @return
     */
    public <T> T getValue(Object key);

    /**
     * 获取key对应value
     * @param key
     * @param cacheName 缓存名称
     * @return
     */
    public <T> T getValue(String cacheName, Object key);

    /**
     * 获取key对应value
     * @param key
     * @param value 缓存值
     * @return
     */
    public void putValue(Object key, Object value);

    /**
     * 获取key对应value
     * @param key
     * @param cacheName 缓存名称
     * @param value 缓存值
     * @return
     */
    public void putValue(String cacheName, Object key, Object value);
}
