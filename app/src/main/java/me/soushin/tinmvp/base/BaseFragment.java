package me.soushin.tinmvp.base;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.barlibrary.SimpleImmersionOwner;
import com.gyf.barlibrary.SimpleImmersionProxy;
import com.hjq.toast.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.soushin.tinmvp.Utils.DialogUtils;
import me.soushin.tinmvp.widget.ProgressDialog;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * fragment基类
 *
 * @auther SouShin
 * @time 2018/12/26 17:27
 */
public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends SupportFragment implements SimpleImmersionOwner {
    private P presenter;
    private Unbinder unbinder;
    //ImmersionBar代理类
    private SimpleImmersionProxy mSimpleImmersionProxy = new SimpleImmersionProxy(this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        presenter = createPresenter();
        bindView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mSimpleImmersionProxy.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSimpleImmersionProxy.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mSimpleImmersionProxy.onHiddenChanged(hidden);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mSimpleImmersionProxy.onConfigurationChanged(newConfig);
    }

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    //这个方法是为了让用户不要忘了绑定BaseView
    protected abstract void bindView();

    @Override
    public void initImmersionBar() {

    }

    @Override
    public boolean immersionBarEnabled() {
//        当为true的时候才可以执行initImmersionBar方法
        return false;
    }

    protected P getPresenter() {
        return presenter;
    }

    public FragmentActivity getFragActivity() {
        return _mActivity;
    }

    public void showToasty(String msg) {
        ToastUtils.show(msg);
    }

    public ProgressDialog getLoading(String msg){
        return DialogUtils.getLoading(msg);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        if (presenter != null) {
            presenter.unsubscribe();
        }
        super.onDestroyView();
    }

}
