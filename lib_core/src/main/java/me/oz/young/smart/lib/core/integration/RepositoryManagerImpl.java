package me.oz.young.smart.lib.core.integration;

import android.content.Context;

import androidx.annotation.NonNull;

/**
 * 用来统一管理网络请求，以及数据缓存层
 * 给{@link me.oz.young.smart.lib.core.mvp.IModel}提供数据来源
 *
 * @author O.z Young
 * @version 2019-12-18
 */
public class RepositoryManagerImpl implements IRepositoryManager {
    @Override
    public <T> T obtainRetrofitService(@NonNull Class<T> serviceClass) {
        return null;
    }

    @Override
    public <T> T obtainCacheService(@NonNull Class<T> cache) {
        return null;
    }

    @Override
    public void clearAllCache() {

    }

    @Override
    public Context getContext() {
        return null;
    }
}
