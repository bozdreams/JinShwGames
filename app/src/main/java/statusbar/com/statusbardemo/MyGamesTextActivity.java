package statusbar.com.statusbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.golive.aidl.UserHelper;
import com.golive.aidl.UserInfoInterface;

/**
 * 业务名:
 * 功能说明:
 * 编写日期: 2017/11/9 下午3:37
 * 作者:zhangbo
 * <p/>
 * 历史记录
 * 1、修改日期：
 * 修改人：
 * 修改内容：
 */
public class MyGamesTextActivity extends Activity implements UserInfoInterface {

    TextView text1;
    Button button1;
    private String mUserInfo;
    private UserHelper m;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mygames);
        text1 = (TextView) findViewById(R.id.text1);
        button1 = (Button) findViewById(R.id.button1);
        m = UserHelper.initialize(MyGamesTextActivity.this, this);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m.getUserInfo(MyGamesTextActivity.this);
            }
        });
        text1.setText(mUserInfo);
    }

    @Override
    public void userInfo(String s) {
        mUserInfo = s;
        Toast.makeText(this,s+"--",Toast.LENGTH_SHORT).show();
        Log.e("aaa", s+"---");
    }
}
