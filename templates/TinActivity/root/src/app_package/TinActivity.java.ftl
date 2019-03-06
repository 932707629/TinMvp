package ${ativityPackageName};


public class ${pageName}Activity extends BaseActivity<${pageName}View, ${pageName}Presenter> implements ${pageName}View {
	@Override
    protected ${pageName}Presenter createPresenter() {
        return new ${pageName}Presenter();
    }
	   @Override
    protected int getLayoutId() {
        return R.layout.${activityLayoutName};
    }

    @Override
    protected void bindView() {
        getPresenter().subscribe(this);
    }

    @Override
    public void initView() {

    }
}
