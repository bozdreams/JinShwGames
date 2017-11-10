package statusbar.com.statusbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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

public class Btn04Activity extends Activity {
    private View mViewNeedOffset;
    private TextView tv_status_alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn04);
        mViewNeedOffset = findViewById(R.id.view_need_offset1);
        tv_status_alpha = (TextView) findViewById(R.id.tv_status_alpha);
        
        StatusBarUtil.setTranslucentForImageView(this,0, mViewNeedOffset);
        tv_status_alpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
