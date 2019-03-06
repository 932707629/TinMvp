package me.soushin.tinmvp.base;

import me.soushin.tinmvp.widget.ProgressDialog;

/**
 * View处理基类
 * @author SouShin
 * @time 2018/11/15 14:19
 */
public interface BaseView {
    void initView();
    void showToasty(String msg);
    ProgressDialog getLoading(String msg);
}
