package me.oz.young.smart.lib.core.mvp;

import android.content.Intent;

import androidx.annotation.NonNull;

import static androidx.core.util.Preconditions.checkNotNull;

/**
 * 框架中的每个 View 都需要实现此类，以满足规范
 *
 * @author 01380154
 * @version 2019/12/16
 */
public interface IView {

    default void showLoading() {

    }

    default void hideLoading() {

    }

    void showMessage(@NonNull String message);

    default void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        // 开启Activity
    }

    default void killMySelf() {

    }

}
