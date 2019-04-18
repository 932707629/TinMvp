package me.soushin.tinmvp;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.blankj.ALog;
import com.hjq.toast.ToastUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.SerializableDiskConverter;
import com.zhouyou.http.cookie.CookieManger;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import me.jessyan.autosize.AutoSizeConfig;
import me.soushin.tinmvp.network.TokenInterceptor;
import me.soushin.tinmvp.widget.ToastStyle;

/**
 * @auther SouShin
 * @time 2019/4/18 17:13
 */
public class App extends Application {
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    /**
     * 获取app示例
     *
     * @return
     */
    public static App getApp() {
        return app;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }



}
