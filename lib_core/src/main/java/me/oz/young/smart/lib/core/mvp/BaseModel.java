package me.oz.young.smart.lib.core.mvp;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import me.oz.young.smart.lib.core.integration.IRepositoryManager;

/**
 * 基类Model
 * Model必须实现Contract中的Model接口，并且继承BaseModel，通过IRepositoryManager拿到
 * 需要的Service和Cache，为Presenter提供需要的数据
 */
public class BaseModel implements IModel, LifecycleObserver {

    /**
     * 统一管理网络请求层，数据库缓存层
     */
    private IRepositoryManager mRepositoryManager;

    public BaseModel(IRepositoryManager repositoryManager) {
        this.mRepositoryManager = repositoryManager;
    }

    /**
     * 框架在BasePresenter中调用onDestroy时，会默认调用{@link IModel#onDestroy()}方法
     */
    @Override
    public void onDestroy() {
        mRepositoryManager = null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
    }
}
