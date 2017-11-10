package statusbar.com.statusbardemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

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

public class Btn06Activity extends Activity {
    private WebView mWebView;
    private Button btnShowInfo;
    private JSKit js;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn06);
        //初始化控件
        mWebView = (WebView) findViewById(R.id.web);
        btnShowInfo = (Button) findViewById(R.id.btn);
        //实例化js对象
//        js = new JSKit(this);
        //设置参数
        mWebView.getSettings().setBuiltInZoomControls(true);
        //内容的渲染需要webviewChromClient去实现，设置webviewChromClient基类，解决js中alert不弹出的问题和其他内容渲染问题
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        //把js绑定到全局的myjs上，myjs的作用域是全局的，初始化后可随处使用
//        mWebView.addJavascriptInterface(new JsToJava(), "getApptRecId");
        mWebView.loadUrl("http://123.56.220.116/index.html?formId=7&token=eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0MDAyMyIsImV4cCI6MTU0NjE4NTYwMH0.uayId0F9xFz7yc3TaBq4SGRFjBDLkjOyyR1ioDO8sqJNKEaHSz4LWm5Wcf_fLbDn9rMP42SPzFquoeZ8IqaOzg");
//        mWebView.loadUrl("file:///android_asset/hello.html");
        mWebView.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


        btnShowInfo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        //调用 HTML 中的javaScript 函数
//                        mWebView.loadUrl("javascript:getApptRecId()");
//                    }
//                });
                mWebView.evaluateJavascript("javascript:getApptRecId()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        //此处为 js 返回的结果
                        Toast.makeText(Btn06Activity.this,value,Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        mWebView.evaluateJavascript("javascript:getApptRecId()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                //此处为 js 返回的结果
                Toast.makeText(Btn06Activity.this,value,Toast.LENGTH_LONG).show();
            }
        });

       
    }
    
    private class JsToJava{
        //这里需要加@JavascriptInterface，4.2之后提供给javascript调用的函数必须带有@JavascriptInterface
        @JavascriptInterface
        public void jsMethod(String paramFromJS){
            Toast.makeText(Btn06Activity.this,paramFromJS,Toast.LENGTH_LONG).show();
        }
    }
}
