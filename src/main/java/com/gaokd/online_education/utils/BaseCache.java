package com.gaokd.online_education.utils;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 本地缓存工具类的配置
 * Guava缓存,比如轮播图和视频详情都可以加入缓存（热点数据都可以加入缓存）
 */
@Component
public class BaseCache {
    private Cache<String,Object> tenMinuteCache = CacheBuilder.newBuilder()
            //设置缓存初始大小
            .initialCapacity(10)
            //最大值
            .maximumSize(100)
            //并发数设置
            .concurrencyLevel(5)
            //写入后600s（10min）
            .expireAfterWrite( 600, TimeUnit.SECONDS)
            //统计缓存命中率
            .recordStats()

            .build();

    private Cache<String,Object> oneHourCache = CacheBuilder.newBuilder()
            //设置缓存初始大小
            .initialCapacity(10)
            //最大值
            .maximumSize(100)
            //并发数设置
            .concurrencyLevel(5)
            //写入后一小时后过期
            .expireAfterWrite( 3600, TimeUnit.SECONDS)
            //统计缓存命中率
            .recordStats()

            .build();

    public Cache<String, Object> getTenMinuteCache() {
        return tenMinuteCache;
    }

    public void setTenMinuteCache(Cache<String, Object> tenMinuteCache) {
        this.tenMinuteCache = tenMinuteCache;
    }

    public Cache<String, Object> getOneHourCache() {
        return oneHourCache;
    }

    public void setOneHourCache(Cache<String, Object> oneHourCache) {
        this.oneHourCache = oneHourCache;
    }
}
