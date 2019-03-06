package me.soushin.tinmvp.base;

/**
 * 逻辑处理基类
 */
public abstract class BasePresenter<V extends BaseView> {
    private V View;

    /**
     * 绑定View
     * 设置状态栏
     * 调用initView()
     * @param View
     */
     public void subscribe(V View){
        this.View= View;
        getView().initView();
    }

    /**
     * 解绑
     */
    public void unsubscribe(){
        View = null;
    }

    /**
     *  获取View
     * @return
     */
    protected V getView(){
        return View;
    }
}
