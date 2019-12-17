package me.oz.young.smart.lib.core.integration;

import android.content.Context;

import androidx.annotation.NonNull;

import retrofit2.Retrofit;

/**
 * 用来统一管理网络请求，以及数据缓存层
 * 给{@link me.oz.young.smart.lib.core.mvp.IModel}提供数据来源
 *
 * @author O.z Young
 * @version 2019-12-16
 */
public interface IRepositoryManager {

    /**
     * 根据传入的Class获取对应的Retrofit服务
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    <T> T obtainRetrofitService(@NonNull Class<T> serviceClass);

    /**
     * 根据传入的Cache获取RxCache的值
     *
     * @param cache
     * @param <T>
     * @return
     */
    <T> T obtainCacheService(@NonNull Class<T> cache);

    void clearAllCache();

    Context getContext();

    interface ObtainServiceDelegate {
        <T> T createRetrofitService(Retrofit retrofit, Class<T> clazz);
    }
}
