package com.farmer.miaosha.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @program: FocusingJava
 * @description: Guava Cache缓存配置
 * @author: FarmerSun
 * @create: 2020-01-03 17:46
 */

@Configuration
@EnableCaching
public class GuavaCacheConfig {
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(
                Caffeine.newBuilder().
                        expireAfterWrite(1, TimeUnit.MINUTES).
                        maximumSize(1000)
                        .initialCapacity(10));
        return cacheManager;
    }
}
