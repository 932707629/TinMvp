package me.soushin.tinmvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.blankj.ALog;
import com.hjq.toast.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.soushin.tinmvp.Utils.DialogUtils;
import me.soushin.tinmvp.widget.ProgressDialog;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Activity基类
 *
 * @author SouShin
 * @time 2018/11/16 14:05
 */
public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends SupportActivity {
    private P presenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        presenter = createPresenter();
        bindView();
    }

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    //这个方法是为了让用户不要忘了绑定BaseView
    protected abstract void bindView();

    protected P getPresenter() {
        return presenter;
    }

    public AppCompatActivity getActivity() {
        return this;
    }

    public void showToasty(String msg) {
        ToastUtils.show(msg);
    }

    public ProgressDialog getLoading(String msg){
        return DialogUtils.getLoading(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (getPresenter() != null) {
            getPresenter().unsubscribe();
        }
    }
}
