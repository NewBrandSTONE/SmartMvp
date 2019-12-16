package me.oz.young.smart.lib.core.mvp;

import android.app.Activity;

/**
 * 每个Presenter要实现，以满足规范
 *
 * @author 01380154
 * @version 2019/12/16
 */
public interface IPresenter {

    /**
     * 做一些初始化操作
     */
    void onStart();

    /**
     * 在框架中 {@link Activity#onDestroy()} 时会默认调用 {@link IPresenter#onDestroy()} 方法
     */
    void onDestroy();

}
