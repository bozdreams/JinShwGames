package statusbar.com.statusbardemo;

import android.app.Activity;
import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;

/**
 * 业务名:
 * 功能说明:
 * 编写日期: 2017/9/5 下午5:11
 * 作者:	 zhangbo
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */

public class Btn02Activity extends Activity {

    private int mColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn02);
        mColor = getResources().getColor(R.color.colorPrimary);
//        mColor = R.drawable.shape;
        StatusBarUtil.setColor(Btn02Activity.this, mColor, 0);
//        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);
    }
}
