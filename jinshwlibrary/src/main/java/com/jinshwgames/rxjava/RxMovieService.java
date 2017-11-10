package com.jinshwgames.rxjava;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 业务名:使用Retrofit+RxJava实现网络请求
 * 功能说明:
 * 编写日期: 2017/10/19 下午5:16
 * 作者:zhangbo
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public interface RxMovieService {

    /**
     * @Author: zhangbo;
     * @MethodDescription:兑换
     * @CreateDate: 2017/11/8 下午12:49;
     * @Version: 1.0;
     */
    @POST(RxHttpAddress.GoldsChange)
    Observable<GoldsChangeEntity> getGoldsChange(@Body RequestBody body);

}
