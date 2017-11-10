package statusbar.com.statusbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.golive.aidl.UserHelper;
import com.golive.aidl.UserInfoInterface;
import com.jinshwgames.RxGames;
import com.jinshwgames.rxjava.GoldsChangeEntity;
import com.jinshwgames.rxjava.GoldsChangeFailListener;
import com.jinshwgames.rxjava.GoldsChangeSuccessListener;
import com.jinshwgames.rxtool.RxToastTool;

public class MyGamesActivity extends Activity implements GoldsChangeSuccessListener, GoldsChangeFailListener, UserInfoInterface {

    TextView text1;
    TextView text2;
    TextView text3;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    String str;
    private UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_mygames);
        String serialNum = android.os.Build.SERIAL;
        RxToastTool.show(this, serialNum);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        userHelper = UserHelper.initialize(this, this);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userHelper.getUserInfo(MyGamesActivity.this);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userHelper.login(MyGamesActivity.this);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userHelper.payGold(MyGamesActivity.this);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userHelper.payGoldCode(MyGamesActivity.this, 10.0);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxGames.getContext(MyGamesActivity.this).getGoldsChange("76D75539D1544F0FB1B33298AA86B22F", "58", "40", "jinshw", "0123456789ABCDE", "151012500091623234234", "jin", MyGamesActivity.this, MyGamesActivity.this);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxGames.getContext(MyGamesActivity.this).getGoldsChange("76D75539D1544F0FB1B33298AA86B22F0", "6", "40", "jinshw", "0123456789ABCDE", "151012500091623234234", "jin", MyGamesActivity.this, MyGamesActivity.this);
            }
        });
    }

    @Override
    public void goldsChangeSuccessData(GoldsChangeEntity.DataBean date) {
        text2.setText(date.toString());
    }

    @Override
    public void goldsChangeFailData(GoldsChangeEntity date) {
        text3.setText(date.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userHelper.destroy(this);
    }

    @Override
    public void userInfo(String s) {
        text1.setText(s);
    }
}
