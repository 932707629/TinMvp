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
 * Created by SouShin on 2018/10/91508.
 */
public class App extends Application {
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        initApp();
    }

    /**
     * 这些初始化操作在主线程会拖延App启动速度
     * 建议在子线程进行
     */
    private void initApp() {
        app = this;
        initALog();
        initEasyHttp();
        ToastUtils.init(this, new ToastStyle());
        //注册生命周期回调
        registerActivityLifecycleCallbacks(new ActivityLifeCycleCallBackIml());
//        AutoSizeConfig.getInstance().setLog(false);
        /*Observable.just(getApp())
                .observeOn(Schedulers.computation())
                .subscribe(new Observer<App>() {
                    Disposable mDisposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(App app) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mDisposable.dispose();
                    }

                    @Override
                    public void onComplete() {
                        mDisposable.dispose();
                    }
                });*/
    }

    /**
     * 获取app示例
     *
     * @return
     */
    public static App getApp() {
        return app;
    }

    // init it in ur application
    public void initALog() {
        ALog.init(this)
                .setLogSwitch(BuildConfig.DEBUG)// 设置log总开关，包括输出到控制台和文件，默认开
                .setConsoleSwitch(BuildConfig.DEBUG)// 设置是否输出到控制台开关，默认开
                .setGlobalTag(null)// 设置log全局标签，默认为空
                // 当全局标签不为空时，我们输出的log全部为该tag，
                // 为空时，如果传入的tag为空那就显示类名，否则显示tag
                .setLogHeadSwitch(true)// 设置log头信息开关，默认为开
                .setLog2FileSwitch(false)// 打印log时是否存到文件的开关，默认关
                .setDir("")// 当自定义路径为空时，写入应用的/cache/log/目录中
                .setFilePrefix("")// 当文件前缀为空时，默认为"alog"，即写入文件为"alog-MM-dd.txt"
                .setBorderSwitch(false)// 输出日志是否带边框开关，默认开
                .setConsoleFilter(ALog.V)// log的控制台过滤器，和logcat过滤器同理，默认Verbose
                .setFileFilter(ALog.V)// log文件过滤器，和logcat过滤器同理，默认Verbose
                .setStackDeep(1);// log栈深度，默认为1

    }

    private void initEasyHttp() {
        EasyHttp.init(this);
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
                .setCookieStore(new CookieManger(this))
                .setCertificates();//信任所有证书
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }



}
