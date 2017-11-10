package statusbar.com.statusbardemo;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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

public class Btn05Activity extends AppCompatActivity {
    Toolbar toolbar;
    private int mColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn05);
        mColor = getResources().getColor(R.color.shape2);
        toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


}
