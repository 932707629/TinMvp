package me.soushin.tinmvp.Utils;

import android.app.Dialog;

import me.soushin.tinmvp.base.BaseDialog;
import me.soushin.tinmvp.widget.ProgressDialog;

/**
 * @auther SouShin
 * @time 2019/2/22 10:37
 */
public class DialogUtils {
    private static ProgressDialog progressDialog;

    /**
     *
     * @param msg
     * @return
     */
    public static ProgressDialog getLoading(String msg){
        return progressDialog=new ProgressDialog(ActivityUtils.currentActivity(),msg);
    }

    /**
     * 关闭弹出框
     * 最好在onDestroy里调用一下这个方法
     */
    public static void disDialog() {
        disDialog(progressDialog);
    }

    public static void disDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


}
