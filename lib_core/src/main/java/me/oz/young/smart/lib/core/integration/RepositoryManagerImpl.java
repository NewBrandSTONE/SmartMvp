package me.oz.young.smart.lib.core.integration;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.inject.Inject;

import dagger.Lazy;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.internal.RxCache;
import me.oz.young.smart.lib.core.integration.cache.Cache;
import me.oz.young.smart.lib.core.integration.cache.CacheType;
import retrofit2.Retrofit;

/**
 * 用来统一管理网络请求，以及数据缓存层
 * 给{@link me.oz.young.smart.lib.core.mvp.IModel}提供数据来源
 *
 * @author O.z Young
 * @version 2019-12-18
 */
public class RepositoryManagerImpl implements IRepositoryManager {

    @Inject
    Lazy<RxCache> mRxCache;

    @Inject
    Lazy<Retrofit> mRetrofit;

    @Inject
    Application mApplication;

    @Inject
    Cache.Factory mCacheFactory;

    private Cache<String, Object> mRetrofitServiceCache;
    private Cache<String, Object> mCacheServiceCache;

    @Inject
    public RepositoryManagerImpl() {
    }

    @Override
    public <T> T obtainRetrofitService(@NonNull Class<T> serviceClass) {
        return createWrapperService(serviceClass);
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

    private <T> T createWrapperService(Class<T> serviceClass) {
        Preconditions.checkNotNull(serviceClass, "serviceClass == null");
        // 通过二次代理，对 Retrofit 代理方法的调用包进新的 Observable 里在 io 线程执行。
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader()
                , new Class<?>[]{serviceClass}, (proxy, method, args) -> {
                    // 如果当前方法返回的是Observable对象,则再包一层
                    if (method.getReturnType() == Observable.class) {
                        return Observable.defer(() -> {
                            T service = getRetrofitService(serviceClass);
                            return ((Observable) getRetrofitMethod(service, method)
                                    .invoke(service, args))
                                    .subscribeOn(Schedulers.io());
                        }).subscribeOn(Schedulers.single());
                    }
                    return null;
                });
    }

    /**
     * 根据传入的 Class 获取对应的 Retrofit service
     *
     * @param serviceClass ApiService class
     * @param <T>          ApiService class
     * @return ApiService
     */
    private <T> T getRetrofitService(Class<T> serviceClass) {
        if (mRetrofitServiceCache == null) {
            mRetrofitServiceCache = mCacheFactory.build(CacheType.RETROFIT_SERVICE_CACHE);
        }
        Preconditions.checkNotNull(mRetrofitServiceCache,
                "Cannot return null from a Cache.Factory#build(int) method");
        T retrofitService = (T) mRetrofitServiceCache.get(serviceClass.getCanonicalName());
        if (retrofitService == null) {
            retrofitService = mRetrofit.get().create(serviceClass);
            mRetrofitServiceCache.put(serviceClass.getCanonicalName(), retrofitService);
        }
        return retrofitService;
    }

    private <T> Method getRetrofitMethod(T service, Method method) throws NoSuchMethodException {
        return service.getClass().getMethod(method.getName(), method.getParameterTypes());
    }
}
