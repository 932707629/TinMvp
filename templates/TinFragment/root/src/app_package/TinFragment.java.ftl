package ${fragmentPackageName};


public class ${pageName}Fragment extends BaseFragment<${pageName}View, ${pageName}Presenter> implements ${pageName}View {

	@Override
    protected ${pageName}Presenter createPresenter() {
        return new ${pageName}Presenter();
    }
	   @Override
    protected int getLayoutId() {
        return R.layout.${fragmentLayoutName};
    }

    @Override
    protected void bindView() {
        getPresenter().subscribe(this);
    }

    @Override
    public void initView() {

    }
}
