package com.jinshwgames.rxjava;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 业务名:RxJava管理类
 * 功能说明:
 * 编写日期: 2017/10/23 下午12:01
 * 作者:zhangbo
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class RxManager {
    private static RxManager rxManager;
    private Map<String, CompositeDisposable> map;

    private RxManager() {
        map = new HashMap<>();
    }

    /**
     * @Author: zhangbo;
     * @MethodDescription:初始化
     * @CreateDate: 2017/10/23 下午1:34;
     * @Version: 1.0;
     */
    public static RxManager getInstance() {
        if (rxManager == null) {
            rxManager = new RxManager();
        }
        return rxManager;
    }


    /**
     * @Author: zhangbo;
     * @MethodDescription:添加标记
     * @CreateDate: 2017/10/23 下午1:33;
     * @Version: 1.0;
     */
    public void add(String key, Disposable disposable) {
        Set<String> keySet = map.keySet();
        if (keySet.contains(key)) {
            CompositeDisposable compositeDisposable = map.get(key);
            compositeDisposable.add(disposable);
        } else {
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(disposable);
            map.put(key, compositeDisposable);
        }
    }

    /**
     * @Author: zhangbo;
     * @MethodDescription:取消订阅
     * @CreateDate: 2017/10/23 下午1:33;
     * @Version: 1.0;
     */
    public void clear(String key) {
        Set<String> keySet = map.keySet();
        if (keySet.contains(key)) {
            CompositeDisposable compositeDisposable = map.get(key);
            compositeDisposable.clear();
            map.remove(key);
        }
    }
}
