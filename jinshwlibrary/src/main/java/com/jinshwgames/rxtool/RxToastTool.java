package com.jinshwgames.rxtool;

import android.content.Context;
import android.widget.Toast;


/**
 * 业务名:自定义Toast在屏幕中间显示-显示成功-显示失败
 * 功能说明:
 * 编写日期:2017/10/23 下午2:38;
 * 作者:zhangbo;
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class RxToastTool {

    private static Toast toast;
    protected static long lastClickTime;
    /**
     * 当前工具类的总开关
     */
    private static boolean isAllowShow = true;

    private RxToastTool() {

    }

    /**
     * @Author: zhangbo;
     * @MethodDescription: 上面方法的增强版，当只需要显示部分Toast提示时，信息为string类型
     * @CreateDate: 2017/10/19 下午6:01;
     * @Version: 1.0;
     */
    public static void show(Context context, String msg) {
        if (!isAllowShow) {
            return;
        }
        // 开关开启后才往下执行
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * @Author: zhangbo;
     * @MethodDescription: 上面方法的增强版，当只需要显示部分Toast提示时，信息为int类型
     * @CreateDate: 2017/10/19 下午6:01;
     * @Version: 1.0;
     */
    public static void show(Context context, int msg) {
        if (!isAllowShow) {
            return;
        }
        // 开关开启后才往下执行
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * @Author: zhangbo;
     * @MethodDescription: 上面方法的增强版，当只需要显示部分Toast提示时，信息为string类型
     * 总开关关闭, 传入true, 显示;总开关开启,传入false,关闭
     * @CreateDate: 2017/10/19 下午6:01;
     * @Version: 1.0;
     */
    public static void show(Context context, String msg, boolean flag) {
        if (!flag) {
            return;
        }
        if (!isAllowShow || !flag) {
            return;
        }
        // 开关开启后才往下执行
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * @Author: zhangbo;
     * @MethodDescription: 增强版，当只需要显示部分Toast提示时，信息为int类型
     * 总开关关闭, 传入true, 显示;总开关开启,传入false,关闭
     * @CreateDate: 2017/10/19 下午6:01;
     * @Version: 1.0;
     */
    public static void show(Context context, int msg, boolean flag) {
        if (!flag) {
            return;
        }
        if (!isAllowShow || !flag) {
            return;
        }
        // 开关开启后才往下执行
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }



    protected static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
