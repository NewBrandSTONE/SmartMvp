package me.oz.young.smart.lib.core.integration.cache;


import androidx.annotation.NonNull;

/**
 * 用于缓存框架中所必须的组件
 *
 * @author 01380154
 * @version 2019/12/19
 */
public interface Cache<K, V> {

    interface Factory {

        /**
         * 创建缓存
         *
         * @param cacheType 缓存类型
         * @return 缓存
         */
        @NonNull
        Cache build(CacheType cacheType);

    }

    /**
     * 返回当前缓存已经占用的总size
     *
     * @return 总size
     */
    int size();

    /**
     * 返回当前缓存能允许的最大size
     *
     * @return 最大size
     */
    int getMaxSize();

    /**
     * 返回这个 key 对应的 value 值
     * 如果返回 null 说明这个 key 值没有 value 值
     *
     * @param key key值
     * @return value值
     */
    V get(K key);

    void put(K key, V value);
}
