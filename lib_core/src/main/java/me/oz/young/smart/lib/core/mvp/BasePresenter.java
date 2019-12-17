package me.oz.young.smart.lib.core.mvp;

import androidx.core.util.Preconditions;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Presenter主要实现业务逻辑代码，从Model层获取数据，调用View层的方法展示数据。
 * 必须要实现Contract中定义的接口，指定Model和View的泛型。
 *
 * @author 01380154
 * @version 2019/12/16
 */
public class BasePresenter<M extends IModel, V extends IView>
        implements IPresenter, LifecycleObserver {

    private final String TAG = this.getClass().getSimpleName();
    private static final String NULL_HINT = " can not be null";

    protected CompositeDisposable mCompositeDisposable;

    protected M mModel;
    protected V mRootView;

    public BasePresenter(M model, V view) {
        Preconditions.checkNotNull(model, model.getClass().getName() + NULL_HINT);
        Preconditions.checkNotNull(view, view.getClass().getName() + NULL_HINT);
        this.mModel = model;
        this.mRootView = view;
        onStart();
    }

    public BasePresenter(V rootView) {
        Preconditions.checkNotNull(rootView, rootView.getClass().getName() + NULL_HINT);
        this.mRootView = rootView;
    }

    public BasePresenter() {
        onStart();
    }

    @Override
    public void onStart() {
        if (mRootView instanceof LifecycleOwner) {
            ((LifecycleOwner) mRootView).getLifecycle().addObserver(this);
            if (mModel instanceof LifecycleObserver) {
                ((LifecycleOwner) mRootView).getLifecycle().addObserver((LifecycleObserver) mModel);
            }
        }
    }

    @Override
    public void onDestroy() {
        unDispose();
        if (mModel != null) {
            mModel.onDestroy();
        }
        this.mModel = null;
        this.mRootView = null;
        this.mCompositeDisposable = null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner owner) {
        owner.getLifecycle().removeObserver(this);
    }

    /**
     * 后续使用RxLifeCycle可以替代
     *
     * @param disposable
     */
    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        // 将所有的Disposable添加到集合中，统一管理
        mCompositeDisposable.add(disposable);
    }

    /**
     * 停止集合中正在执行的RxJava任务
     */
    private void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
