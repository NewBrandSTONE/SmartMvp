package me.oz.young.smart.lib.core.http;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;

import java.io.InputStream;

/**
 * 使用OkHttp加载网络媒体资源
 *
 * @author 01380154
 * @version 2019/12/16
 */
public class OkHttpUrlLoader implements ModelLoader<GlideUrl, InputStream> {


    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(@NonNull GlideUrl glideUrl, int width, int height, @NonNull Options options) {
        return null;
    }

    @Override
    public boolean handles(@NonNull GlideUrl glideUrl) {
        return false;
    }
}
