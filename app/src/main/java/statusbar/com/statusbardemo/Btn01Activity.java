package statusbar.com.statusbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jaeger.library.StatusBarUtil;
import com.just.library.AgentWeb;

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

public class Btn01Activity extends Activity {

    protected AgentWeb mAgentWeb;
    private LinearLayout mLinearLayout;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setTranslucent(this, 0);
        setContentView(R.layout.activity_btn01);
        mLinearLayout = (LinearLayout) this.findViewById(R.id.container);
        button = (Button) this.findViewById(R.id.button);
        mAgentWeb = AgentWeb.with(this)//传入Activity or Fragment
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams ,第一个参数和第二个参数应该对应。
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .createAgentWeb()//
                .ready()
//                .go("http://123.56.220.116/index7.html?formId=7&v=Woj0w8K9kYRI9G97eu1wTWctWNNj6Z0V");
                .go("file:///android_asset/hello.html");
        mAgentWeb.getJsInterfaceHolder().addJavaObject("android1",new AndroidInterface(mAgentWeb,this));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAgentWeb.getJsEntraceAccess().quickCallJs("callByAndroid");
            }
        });
    }
}
