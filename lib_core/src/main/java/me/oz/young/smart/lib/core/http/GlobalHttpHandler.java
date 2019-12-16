package me.oz.young.smart.lib.core.http;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 处理 Http 请求和响应
 *
 * @author O.z Young
 * @version 2019-12-16
 */
public interface GlobalHttpHandler {

    /**
     * 这里可以先一步拿到服务器返回的结果，可以先解析json，判断token是否过期，如果检测到
     * token过期可以重新发起请求
     *
     * @param httpResult
     * @param chain
     * @param response
     * @return
     */
    @NonNull
    Response onHttpResultResponse(@Nullable String httpResult, @NonNull Interceptor.Chain chain,
                                  @NonNull Response response);

    /**
     * 在向服务器发起请求之前，可以增加Token或者Header
     *
     * @return
     */
    @NonNull
    Request onHttpRequestBefore(@NonNull Interceptor.Chain chain, @NonNull Request request);


}
