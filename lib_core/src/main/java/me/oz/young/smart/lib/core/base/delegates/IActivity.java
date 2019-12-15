package me.oz.young.smart.lib.core.base.delegates;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * 规范所有的{@link android.app.Activity}类都需要实现这个接口
 *
 * @author O.z Young
 * @version 2019-12-14
 */
public interface IActivity {

    /**
     * 初始化view，如果initView返回0，框架不会调用setContentView
     *
     * @param savedInstance
     * @return
     */
    int initView(@Nullable Bundle savedInstance);

    /**
     * @param savedInstance
     */
    void initData(@Nullable Bundle savedInstance);

    /**
     * 判断这个Activity是否使用Fragment
     *
     * @return
     */
    boolean useFragment();

}
