package me.oz.young.smart.lib.core.mvp;

/**
 * 框架要求每个Model都必须实现这个类
 *
 * @author 01380154
 * @version 2019/12/16
 */
public interface IModel {

    /**
     * 在框架中{@link BasePresenter#onDestroy()}时会默认调用{@link IModel#onDestroy()}方法
     */
    void onDestroy();

}
