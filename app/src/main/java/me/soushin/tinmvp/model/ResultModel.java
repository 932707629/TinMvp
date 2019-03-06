package me.soushin.tinmvp.model;

import com.zhouyou.http.model.ApiResult;

/**
 * 说好的返回Api
 * @author SouShin
 * @time 2018/11/21 13:50
 */
public class ResultModel<T> extends ApiResult<T> {
    T weatherinfo;

    @Override
    public T getData() {
        return weatherinfo;
    }

    @Override
    public void setData(T data) {
        this.weatherinfo=data;
    }

    @Override
    public boolean isOk() {
        return weatherinfo!=null;
    }
}
