package me.oz.young.smart.lib.core.http;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * 展示 {@link GlobalHttpHandler} 的用法
 *
 * @author 01380154
 * @version 2019/12/19
 */
public class GlobalHttpHandlerImpl implements GlobalHttpHandler {

    private Context mContext;

    public GlobalHttpHandlerImpl(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public Response onHttpResultResponse(@Nullable String httpResult, @NonNull Interceptor.Chain chain
            , @NonNull Response response) {
        Timber.d("response >> %s", response);
        return response;
    }

    @NonNull
    @Override
    public Request onHttpRequestBefore(@NonNull Interceptor.Chain chain, @NonNull Request request) {
        Timber.d("request >> %s", request);
        return request;
    }
}
