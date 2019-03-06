package ${fragmentPackageName};

import android.os.Bundle;
import android.support.annotation.Nullable;

public class ${pageName}Fragment extends BaseFragment {

	 @Override
    public int initLayout() {
        return R.layout.${fragmentLayoutName};
    }
	
    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
      
    }
	
    @Override
    protected void initListener() {
        
    }
	
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        只有第一次可见时调用
//        ALog.i("懒加载",getClass().getSimpleName());
    }
	
    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
//        ALog.i("fragment可见",getClass().getSimpleName());
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
//        ALog.i("fragment隐藏",getClass().getSimpleName());
    }

	   @Override
    public void initImmersionBar() {
//  新手指引:  fragment里设置沉浸标题栏之前必须先在宿主Activity里调用ImmersionBar.with(this).init();
    }

    @Override
    public boolean immersionBarEnabled() {
//      当为true的时候才可以执行initImmersionBar方法
        return false;
    }
	
	
}