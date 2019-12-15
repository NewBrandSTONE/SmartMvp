package me.oz.young.smart.lib.core.http;

import androidx.annotation.NonNull;

import okhttp3.HttpUrl;

/**
 * 针对BaseUrl在启动时不确定，需要请求服务端接口动态获取的场景
 *
 * @author O.z Young
 * @version 2019-12-15
 */
public interface BaseUrl {

    /**
     * 在调用Retrofit API接口之前，使用Okhttp或者其他方式获取到正确的BaseUrl并通过此方式返回
     *
     * @return HttpUrl
     */
    @NonNull
    HttpUrl url();

}
