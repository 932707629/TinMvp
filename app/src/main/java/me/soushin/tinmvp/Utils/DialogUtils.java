package me.soushin.tinmvp.Utils;

import android.app.Dialog;

import com.zhouyou.http.EasyHttp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import me.soushin.tinmvp.base.BaseDialog;
import me.soushin.tinmvp.model.SubcribeModel;
import me.soushin.tinmvp.widget.ProgressDialog;

/**
 * @auther SouShin
 * @time 2019/2/22 10:37
 */
public class DialogUtils {
    private static List<BaseDialog> dialogList = new ArrayList<>();

    /**
     * @param msg
     * @return
     */
    public static ProgressDialog getLoading(String msg) {
        return new ProgressDialog(ActivityUtils.currentActivity(), msg);
    }

    /**
     * 关闭弹出框
     * 最好在onDestroy里调用一下这个方法
     */
    public static void disDialog() {
        Iterator<BaseDialog> it = dialogList.iterator();
        while (it.hasNext()) {
            BaseDialog dialog = it.next();
            disDialog(dialog);
            it.remove();
        }
    }

    public static void addDialog(BaseDialog dialog) {
        dialogList.add(dialog);
    }

    public static void disDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


}
