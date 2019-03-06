package me.soushin.tinmvp.module.main;

import me.soushin.tinmvp.R;
import me.soushin.tinmvp.Utils.ActivityUtils;
import me.soushin.tinmvp.Utils.AppUtils;
import me.soushin.tinmvp.base.BaseActivity;

/**
 * View
 */
public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void bindView() {
        getPresenter().subscribe(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void onBackPressedSupport() {
//        onBackPressed()已停用  请使用onBackPressedSupport代替
        if (AppUtils.doubleClickExit()) {
            ActivityUtils.AppExit(getActivity());
        }
    }
}
