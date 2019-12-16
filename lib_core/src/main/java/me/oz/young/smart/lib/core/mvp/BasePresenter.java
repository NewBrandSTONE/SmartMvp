package me.oz.young.smart.lib.core.mvp;

import androidx.core.util.Preconditions;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author 01380154
 * @version 2019/12/16
 */
public class BasePresenter<M extends IModel, V extends IView> implements IPresenter, LifecycleObserver {

    protected CompositeDisposable mCompositeDisposable;
    private final String TAG = this.getClass().getSimpleName();
    private static final String NULL_HINT = " can not be null";

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

    private void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
