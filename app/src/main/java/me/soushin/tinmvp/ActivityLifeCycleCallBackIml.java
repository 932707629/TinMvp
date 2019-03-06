package me.soushin.tinmvp;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.blankj.ALog;
import com.gyf.barlibrary.ImmersionBar;
import me.soushin.tinmvp.Utils.ActivityUtils;
import me.soushin.tinmvp.Utils.AppUtils;
import me.soushin.tinmvp.Utils.DialogUtils;
import me.soushin.tinmvp.Utils.TitleBarUtils;
import me.soushin.tinmvp.module.main.MainActivity;
import me.soushin.tinmvp.network.HttpClient;

/**
 * activity生命周期管理类
 * 你想象力有多丰富,这里就有多强大,
 * 以前放到BaseActivity的操作都可以放到这里
 * 使用:registerActivityLifecycleCallbacks(new ActivityLifeCycleCallBackIml());
 *
 * @author SouShin
 * @time 2018/12/10 15:38
 */
public class ActivityLifeCycleCallBackIml implements Application.ActivityLifecycleCallbacks {
    private TitleBarUtils titleBar;
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        ALog.i("activity生命周期管理类",activity.getLocalClassName());
        ActivityUtils.addActivity(activity);
    }

    @Override
    public void onActivityStarted(final Activity activity) {
//        ALog.i("activity生命周期管理类", "onActivityStarted");
        //这里根据不同的activity显示不同的topBar
        titleBar=new TitleBarUtils();
        if (activity instanceof MainActivity) {
            titleBar.setToolBar(activity,false,AppUtils.getString(R.string.app_name));
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
//        ALog.i("activity生命周期管理类", "onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
//        ALog.i("activity生命周期管理类", "onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
//        ALog.i("activity生命周期管理类", "onActivityStopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//        ALog.i("activity生命周期管理类", "onActivitySaveInstanceState");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ALog.i("activity生命周期管理类", "onActivityDestroyed");
        ImmersionBar.with(activity).destroy(); //必须调用该方法，防止内存泄漏
        ActivityUtils.removeActivity(activity);
        HttpClient.disposeRequest(activity.getLocalClassName());
        DialogUtils.disDialog();
        titleBar.Unbind();
        titleBar=null;
    }

}
