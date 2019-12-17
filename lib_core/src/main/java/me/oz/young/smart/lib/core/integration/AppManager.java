package me.oz.young.smart.lib.core.integration;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import java.util.List;

/**
 * 用于管理所有的{@link android.app.Activity}以及栈顶的{@link android.app.Activity}
 *
 * @author O.z Young
 * @version 2019-12-16
 */
public class AppManager {

    /**
     * 不保证栈顶顺序一致
     */
    private List<Activity> mActivityList;

    private Activity mCurrentActivity;

    private static volatile AppManager sAppManager;

    private Application mApplication;

    private AppManager() {
        throw new IllegalStateException("AppManager can not be instance !");
    }

    public static AppManager getInstance() {
        if (sAppManager == null) {
            synchronized (AppManager.class) {
                if (sAppManager == null) {
                    sAppManager = new AppManager();
                }
            }
        }
        return sAppManager;
    }

    public AppManager init(Application application) {
        this.mApplication = application;
        return sAppManager;
    }

    public void startActivity(Intent intent) {
        if (getTopActivity() == null) {
            // 在新的栈中打开
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mApplication.startActivity(intent);
            return;
        }
        getTopActivity().startActivity(intent);
    }

    private Activity getTopActivity() {
        if (mActivityList == null) {
            return null;
        }
        return mActivityList.isEmpty() ? null : mActivityList.get(mActivityList.size() - 1);
    }
}
