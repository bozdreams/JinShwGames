package com.jinshwgames;

import android.content.Context;

import com.golive.aidl.UserHelper;
import com.golive.aidl.UserInfoInterface;
import com.jinshwgames.rxjava.GoldsChangeFailListener;
import com.jinshwgames.rxjava.GoldsChangeSuccessListener;
import com.jinshwgames.rxjava.ProgressSubscriber;
import com.jinshwgames.rxjava.RxAppClient;
import com.jinshwgames.rxjava.RxHttpStatus;
import com.jinshwgames.rxjava.RxManager;
import com.jinshwgames.rxjava.RxMovieService;
import com.jinshwgames.rxjava.SubscriberOnNextListener;
import com.jinshwgames.rxjava.SwitchSchedulers;
import com.jinshwgames.rxjava.GoldsChangeEntity;
import com.jinshwgames.rxtool.RxJsonTool;
import com.jinshwgames.rxtool.RxToastTool;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 业务名:
 * 功能说明:
 * 编写日期: 2017/11/7 下午4:36
 * 作者:zhangbo
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class RxGames {

    private Context mContext;
    private static RxGames mRxUtils;
    private GoldsChangeSuccessListener mGoldsChangeSuccessListener;
    private GoldsChangeFailListener mGoldsChangeFailListener;
    private Map<String, Object> params = new HashMap<>();

    public RxGames(Context mContext) {
        this.mContext = mContext;
    }


    public static RxGames getContext(Context context) {
        if (mRxUtils == null) {
            synchronized (RxGames.class) {
                if (mRxUtils == null) {
                    mRxUtils = new RxGames(context);
                }
            }
        }
        return mRxUtils;
    }


    /**
     * @Author: zhangbo;
     * @MethodDescription:兑换金币接口
     * @CreateDate: 2017/11/8 上午11:42;
     * @Version: 1.0;
     */
    public void getGoldsChange(String userNo, String golds, String remark, String deviceNo, String outAppid, String outAppname, GoldsChangeSuccessListener goldsChangeSuccessListener, GoldsChangeFailListener goldsChangeFailListener) {
        mGoldsChangeSuccessListener = goldsChangeSuccessListener;
        mGoldsChangeFailListener = goldsChangeFailListener;
        params.clear();
        params.put("userNo", userNo);
        params.put("golds", golds);
        params.put("deviceNo", deviceNo);
        params.put("outAppid", outAppid);
        params.put("outAppname", outAppname);
        params.put("remark", remark);

        Observable<GoldsChangeEntity> observable = RxAppClient.retrofit().create(RxMovieService.class).getGoldsChange(returnParamsBody());
        observable
                .compose(SwitchSchedulers.io_main())
                .subscribe(new ProgressSubscriber<>(new SubscriberOnNextListener<GoldsChangeEntity>() {
                    @Override
                    public void onNext(GoldsChangeEntity o) {
                        if (o.getCode().equals(RxHttpStatus.Success)) {
                            if (mGoldsChangeSuccessListener != null) {
                                mGoldsChangeSuccessListener.goldsChangeSuccessData(o.getData());
                            }
                        } else {
                            if (mGoldsChangeFailListener != null) {
                                mGoldsChangeFailListener.goldsChangeFailData(o);
                            }
                        }
                    }
                }, mContext));
    }


    /**
     * @Author: zhangbo;
     * @MethodDescription:拼接参数
     * @CreateDate: 2017/11/8 下午12:48;
     * @Version: 1.0;
     */
    private RequestBody returnParamsBody() {
        String str = RxJsonTool.map2Json(params);
        RequestBody orderIdBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
                str);
        return orderIdBody;
    }

}
