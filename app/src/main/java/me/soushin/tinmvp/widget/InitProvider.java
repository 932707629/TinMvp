package me.soushin.tinmvp.widget;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blankj.ALog;
import com.hjq.toast.ToastUtils;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.SerializableDiskConverter;
import com.zhouyou.http.cookie.CookieManger;

import me.soushin.tinmvp.ActivityLifeCycleCallBackIml;
import me.soushin.tinmvp.BuildConfig;
import me.soushin.tinmvp.Configure;
import me.soushin.tinmvp.network.TokenInterceptor;

/**
 * 通过声明 {@link ContentProvider} 自动完成应用初始化
 * @auther SouShin
 * @time 2019/4/18 17:04
 */
public class InitProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        initApp((Application) getContext().getApplicationContext());
        return true;
    }

    /**
     * 这些初始化操作在主线程会拖延App启动速度
     * 建议在子线程进行
     */
    private void initApp(Application app) {
        initALog(app);
        initEasyHttp(app);
        ToastUtils.init(app, new ToastStyle());
        //注册生命周期回调
        app.registerActivityLifecycleCallbacks(new ActivityLifeCycleCallBackIml());
    }

    // init it in ur application
    public void initALog(Application app) {
        ALog.init(app)
                .setLogSwitch(BuildConfig.DEBUG)// 设置log总开关，包括输出到控制台和文件，默认开
                .setConsoleSwitch(BuildConfig.DEBUG)// 设置是否输出到控制台开关，默认开
                .setBorderSwitch(false);// 输出日志是否带边框开关，默认开
    }

    private void initEasyHttp(Application app) {
        EasyHttp.init(app);
        EasyHttp.getInstance()
                .debug("NetWork--INFO", BuildConfig.DEBUG)
                .setReadTimeOut(20 * 1000)
                .setWriteTimeOut(20 * 1000)
                .setConnectTimeout(20 * 1000)
                .setRetryCount(1)//默认网络不好自动重试3次
                .setBaseUrl(Configure.BASE_URL)
                .setCacheDiskConverter(new SerializableDiskConverter())//默认缓存使用序列化转化
                .setCacheMaxSize(50 * 1024 * 1024)//设置缓存大小为50M
                .setCacheVersion(1)//缓存版本为1
                .addInterceptor(new TokenInterceptor())
                .setCookieStore(new CookieManger(app))
                .setCertificates();//信任所有证书
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
