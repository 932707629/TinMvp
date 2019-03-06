package me.soushin.tinmvp.widget;

import android.app.Dialog;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhouyou.http.subsciber.IProgressDialog;

import butterknife.BindView;
import me.soushin.tinmvp.R;
import me.soushin.tinmvp.base.BaseDialog;

/**
 * 加载等待框
 *
 * @author SouShin
 * @time 2018/10/31 9:49
 */
public class ProgressDialog extends BaseDialog implements IProgressDialog {

  @BindView(R.id.pb_progress)
  ProgressBar pbProgress;
  @BindView(R.id.tv_progress)
  TextView tvProgress;

  public ProgressDialog(Context context, String msg) {
    super(context, R.layout.dialog_progress);
    tvProgress.setText(msg);
    setCancelable(false);
    setCanceledOnTouchOutside(false);
  }

  @Override
  public Dialog getDialog() {
    return this;
  }
}
