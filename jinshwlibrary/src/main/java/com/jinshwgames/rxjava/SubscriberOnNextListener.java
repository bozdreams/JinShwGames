package com.jinshwgames.rxjava;

/**
 * 业务名:请求数据返回接口
 * 功能说明:
 * 编写日期:2017/10/19 下午6:27;
 * 作者:zhangbo;
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
