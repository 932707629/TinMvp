package me.soushin.tinmvp.Utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import java.util.List;
import java.util.Stack;

import me.soushin.tinmvp.base.BaseFragment;

/**
 * 封装Activity相关工具类
 *
 * @author SouShin
 * @time 2018/10/29 14:45
 */
public class ActivityUtils {

    private static Stack<Activity> activityStack;

    /**
     * 添加Activity 到栈
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前的Activity（堆栈中最后一个压入的)
     */
    public static Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public static void finishActivity() {
        Activity activity = activityStack.lastElement();
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 移除指定的Activity
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public static void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public static void finishActivity(Class<?> cls) {
        for (int i = 0; i < activityStack.size(); i++) {
            if (activityStack.get(i).getClass().equals(cls)) {
                finishActivity(activityStack.get(i));
            }
        }
    }

    /**
     * 结束所有的Activity
     */
    public static void finishAllActivity() {
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出app
     *
     * @param context
     */
    public static void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    public static Stack<Activity> getActivityStack() {
        return activityStack;
    }

    /**
     * 判断是否存在指定Activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @param className   activity全路径类名
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isExistActivity(Context context, String packageName, String className) {
        Intent intent = new Intent();
        intent.setClassName(packageName, className);
        return !(context.getPackageManager().resolveActivity(intent, 0) == null ||
                intent.resolveActivity(context.getPackageManager()) == null ||
                context.getPackageManager().queryIntentActivities(intent, 0).size() == 0);
    }

    /**
     * 要求最低API为11
     * Activity 跳转
     * 跳转后Finish之前所有的Activity
     *
     * @param activity
     * @param goal
     */
    public static void goAndFinishAll(Activity activity, Class<?> goal, Bundle bundle) {
        Intent intent = new Intent(activity, goal);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     * 要求最低API为11
     * Activity 跳转
     * 跳转后Finish之前所有的Activity
     *
     * @param activity
     * @param goal
     */
    public static void goAndFinishAll(Activity activity, Class<?> goal) {
        Intent intent = new Intent(activity, goal);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     * Activity 跳转
     *
     * @param activity
     * @param goal
     */
    public static void goAndFinish(Activity activity, Class<?> goal, Bundle bundle) {
        Intent intent = new Intent(activity, goal);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     * Activity 跳转
     *
     * @param activity
     * @param goal
     */
    public static void goAndFinish(Activity activity, Class<?> goal) {
        activity.startActivity(new Intent(activity, goal));
        activity.finish();
    }

    /**
     * Activity 跳转
     *
     * @param activity
     * @param goal
     */
    public static void goTo(Activity activity, Class<?> goal) {
        Intent intent = new Intent(activity, goal);
        activity.startActivity(intent);
    }

    /**
     * Activity 跳转
     *
     * @param activity
     * @param goal
     */
    public static void goTo(Activity activity, Class<?> goal,Intent intent) {
        intent.setClass(activity,goal);
        activity.startActivity(intent);
    }

    public static void goForResult(Activity activity, Class<?> goal, int requestCode) {
        Intent intent = new Intent(activity, goal);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void goForResult(BaseFragment fragment, Class<?> goal, int requestCode) {
        Intent intent = new Intent(fragment.getFragActivity(), goal);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void goForResult(Activity activity, Class<?> goal, Intent intent, int requestCode) {
        intent.setClass(activity,goal);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void goForResult(BaseFragment fragment, Class<?> goal, Intent intent, int requestCode) {
        intent.setClass(fragment.getFragActivity(),goal);
        fragment.startActivityForResult(intent, requestCode);
    }
    /**
     * 打开App
     *
     * @param activity    activity
     * @param packageName 包名
     */
    public static void launchApp(Activity activity, String packageName) {
        if (AppUtils.isEmpty(packageName)){
            return;
        }
        activity.startActivity(activity.getPackageManager().getLaunchIntentForPackage(packageName));
    }

    /**
     * 使用系统发送分享数据
     *
     * @param context 上下文
     * @param text    要分享的文本
     */
    public static void share(Context context, String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(sendIntent, "分享到"));
    }

    /**
     * 获取launcher activity
     *
     * @param context     上下文
     * @param packageName 包名
     * @return launcher activity
     */
    public static String getLauncherActivity(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        for (ResolveInfo info : infos) {
            if (info.activityInfo.packageName.equals(packageName)) {
                return info.activityInfo.name;
            }
        }
        return "no " + packageName;
    }
}
