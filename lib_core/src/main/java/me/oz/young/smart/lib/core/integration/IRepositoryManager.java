package me.oz.young.smart.lib.core.integration;

import androidx.annotation.NonNull;

/**
 * 用来统一管理网络请求，以及数据缓存层
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
}
