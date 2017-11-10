package com.jinshwgames.rxjava;

import android.content.Context;
import android.util.Log;

import com.jinshwgames.rxtool.RxNetworkTool;
import com.jinshwgames.rxtool.RxToastTool;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.adapter.rxjava2.HttpException;


/**
 * 业务名:用于在Http请求开始时，自动显示一个ProgressDialog      在Http请求结束是，关闭ProgressDialog
 * 功能说明:
 * 编写日期:2017/10/23 下午1:16;
 * 作者:zhangbo;
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class ProgressSubscriber<T> implements Observer<T> {

    private SubscriberOnNextListener mSubscriberOnNextListener;
    private Context context;
    private NetWorkFail netWorkFail;
    private Disposable disposable;

    /**
     * @Author: zhangbo;
     * @MethodDescription:通过boolean控制请求本身的加载动画
     * @CreateDate: 2017/10/20 上午10:28;
     * @Version: 1.0;
     */
    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        this.context = context;
    }


    /**
     * @Author: zhangbo;
     * @MethodDescription:订阅开始时调用，显示ProgressDialog
     * @CreateDate: 2017/10/20 上午10:27;
     * @Version: 1.0;
     */
    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }


    /**
     * @param t 创建Subscriber时的泛型类型
     * @Author: zhangbo;
     * @MethodDescription:将onNext方法中的返回结果交给Activity或Fragment自己处理 如果状态码等于200解除订阅-不再接收上游事件
     * @CreateDate: 2017/10/20 上午10:26;
     * @Version: 1.0;
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null && t != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }

    /**
     * @Author: zhangbo;
     * @MethodDescription:完成，隐藏ProgressDialog
     * @CreateDate: 2017/10/20 上午10:27;
     * @Version: 1.0;
     */
    @Override
    public void onComplete() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }


    /**
     * @Author: zhangbo;
     * @MethodDescription:* 对错误进行统一处理 ，隐藏ProgressDialog
     * @CreateDate: 2017/10/20 上午10:27;
     * @Version: 1.0;
     */
    @Override
    public void onError(Throwable e) {
        //请求失败时回调接口
        if (null != netWorkFail) {
            netWorkFail.onError(e);
        }
        if (!RxNetworkTool.isNetworkAvailable(context)) {
            RxToastTool.show(context, "请检查网络连接");
        } else if (e instanceof ConnectException) {
            RxToastTool.show(context, "服务异常，请重新登录");
        } else if (e instanceof SocketTimeoutException) {
            RxToastTool.show(context, "网络连接超时");
        } else if (e instanceof NullPointerException) {
            RxToastTool.show(context, "服务器异常，请重试");
        } else if (e instanceof HttpException) {
            RxToastTool.show(context, "网络错误");
        } else {
            RxToastTool.show(context, "异常错误");
        }
    }


    /**
     * @Author: zhangbo;
     * @MethodDescription:网络请求失败时的回调
     * @CreateDate: 2017/10/20 上午10:26;
     * @Version: 1.0;
     */
    public interface NetWorkFail {
        void onError(Throwable e);
    }
}