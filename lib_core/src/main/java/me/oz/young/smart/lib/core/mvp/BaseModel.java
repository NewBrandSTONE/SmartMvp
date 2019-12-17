package me.oz.young.smart.lib.core.mvp;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import me.oz.young.smart.lib.core.integration.IRepositoryManager;

/**
 *
 */
public class BaseModel implements IModel, LifecycleOwner {

    /**
     * 统一管理网络请求层，数据库缓存层
     */
    private IRepositoryManager mRepositoryManager;

    public BaseModel(IRepositoryManager repositoryManager) {
        this.mRepositoryManager = repositoryManager;
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return null;
    }

    /**
     * 框架在BasePresenter中调用onDestroy时，会默认调用{@link IModel#onDestroy()}方法
     */
    @Override
    public void onDestroy() {
        mRepositoryManager = null;
    }
}
