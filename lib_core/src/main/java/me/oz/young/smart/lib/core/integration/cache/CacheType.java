package me.oz.young.smart.lib.core.integration.cache;

import android.content.Context;

/**
 * 构建缓存 {@link Cache} 时需要指定创建类型，使用 {@link CacheType} 中声明的类型，来区分不同的模块。
 * 从而为不同模块设置不同的缓存策略
 *
 * @author 01380154
 * @version 2019/12/19
 */
public interface CacheType {

    int RETROFIT_SERVICE_CACHE_TYPE_ID = 0;
    int CACHE_SERVICE_CACHE_TYPE_ID = 1;
    int EXTRAS_TYPE_ID = 2;
    int ACTIVITY_CACHE_TYPE_ID = 3;
    int FRAGMENT_CACHE_TYPE_ID = 4;

    CacheType RETROFIT_SERVICE_CACHE = new CacheType() {

        @Override
        public int getCacheTypeId() {
            return RETROFIT_SERVICE_CACHE_TYPE_ID;
        }

        @Override
        public int calculateCacheSize(Context context) {
            return 0;
        }
    };

    int getCacheTypeId();

    int calculateCacheSize(Context context);

}
