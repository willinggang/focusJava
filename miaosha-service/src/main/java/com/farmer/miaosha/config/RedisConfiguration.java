package com.farmer.miaosha.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: FocusingJava
 * @description: Redis序列化存储配置
 * @author: FarmerSun
 * @create: 2020-01-07 10:09
 */
@EnableCaching
@Configuration
public class RedisConfiguration {

    /**
     * 在没有指定缓存key时，key生成策略
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append("#");
            sb.append(method.getName());
            for (Object object : objects) {
                sb.append(object);
            }
            return sb.toString();
        };
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getKeySerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()))
                .disableCachingNullValues()
                .entryTtl(Duration.ofSeconds(60));

        return RedisCacheManager.builder(redisTemplate.getConnectionFactory())
                .cacheDefaults(redisCacheConfiguration)
                .withInitialCacheConfigurations(getRedisCacheConfigurationMap())
                .build();
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        // 配置redisTemplate
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
        RedisSerializer stringSerializer = new StringRedisSerializer();
        /*key序列化*/
        redisTemplate.setKeySerializer(stringSerializer);
        /*value序列化*/
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        /*Hash key序列化*/
        redisTemplate.setHashKeySerializer(stringSerializer);
        /*Hash value序列化*/
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        /**/
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 使用 加上注解即可 @Cacheable("userCache")
     * 注：名称为配置类里面设置的名称userCache，可设置多个缓存名称及时间
     * 注意：使用@CacheEvict注解的方法必须是controller层直接调用，service里间接调用不生效@CacheEvict的方法
     * 和@Cache的方法放到一个java文件中写，他俩在两个java文件的话，会导致@CacheEvict失效。
     */
    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        //SsoCache和BasicDataCache进行过期时间配置
        redisCacheConfigurationMap.put("messagCache", this.getRedisCacheConfigurationWithTtl(30 * 60));
        //自定义设置缓存时间
        redisCacheConfigurationMap.put("userCache", this.getRedisCacheConfigurationWithTtl(60));
        return redisCacheConfigurationMap;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        FastJsonRedisSerializer<Object> jackson2JsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(jackson2JsonRedisSerializer)
        ).entryTtl(Duration.ofSeconds(seconds));

        return redisCacheConfiguration;
    }


    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}