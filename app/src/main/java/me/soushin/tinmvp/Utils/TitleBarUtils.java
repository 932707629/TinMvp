package me.soushin.tinmvp.Utils;

import android.app.Activity;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;

import me.soushin.tinmvp.R;
import me.soushin.tinmvp.widget.ToolHolder;

/**
 * 标题栏工具类
 * 标题栏样式较多,可以把所有样式放到这里,方便调用
 *
 * @auther SouShin
 * @time 2019/1/24 09:16
 */
public class TitleBarUtils {
    private ToolHolder toolHolder;
    /**
     * 适用于有标题栏有状态栏的情况
     * BarParams里面有ImmersionBar各项设置详细说明
     * ImmersionBar是设置标题栏和状态栏背景的工具类(https://github.com/gyf-dev/ImmersionBar)
     * 支持设置标题栏和状态栏渐变/纯色/白底黑字
     *
     * @param activity 上下文
     * @param isBack 需/不需要返回按钮
     * @param title 标题
     */

    public void setToolBar(final Activity activity, boolean isBack, String title) {
        View toolBar = activity.findViewById(R.id.toolbar);
        if (toolBar==null){
            throw new NullPointerException("新手指引:请在activity布局文件里添加<include layout=\"@layout/layout_tool_bar\" />");
        }
        ImmersionBar.with(activity)
                .statusBarColor(R.color.colorPrimary)
                .titleBar(toolBar)
                .init();
        toolHolder = new ToolHolder(toolBar);
        toolHolder.tvTitle.setText(title);
        toolHolder.imgBack.setVisibility(isBack ? View.VISIBLE : View.GONE);
        toolHolder.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });
    }

    /**
     * 适用于无标题栏只有状态栏的情况
     * 包括设置状态栏透明
     * statusBarDarkFont(true,1) 状态栏黑字
     * fitsSystemWindows(true)解决布局与状态栏重叠问题
     * @param activity 上下文
     */
    public void setToolBar(Activity activity){
        ImmersionBar.with(activity)
                .statusBarColor(R.color.colorAccent)
                .fitsSystemWindows(true)
                .init();
    }

    /**
     * 解除绑定
     */
    public void Unbind(){
        if (toolHolder!=null){
            toolHolder.unbinder.unbind();
            toolHolder.unbinder=null;
        }
    }
}
