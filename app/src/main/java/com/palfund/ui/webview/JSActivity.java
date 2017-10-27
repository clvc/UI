package com.palfund.ui.webview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.palfund.ui.R;

public class JSActivity extends AppCompatActivity {
    private WebView mWebView;
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js);
        mWebView = (WebView) findViewById(R.id.webView);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        //设置编码格式
        settings.setDefaultTextEncodingName("utf-8");
        //加载本地assets目录下的文件
        //mWebView.loadUrl("file:///android_asset/in" + "dex.html");
         mWebView.loadUrl("http://192.168.0.117/index.html");
        //允许js来操作Android程序.
        //object:代表了执行这个js的对象.
        //name:代表了Android程序.
        mWebView.addJavascriptInterface(this, "mAndroid");
    }

    public void click(View view) {
        //android调用js的方法
        mWebView.loadUrl("JavaScript:changeContent()");
    }


    //注解:必须添加这个注解.
    @JavascriptInterface
    public void showToast() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(JSActivity.this, "我是不带参数的Toast!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @JavascriptInterface
    public void showToastWithParams(final String params) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(JSActivity.this, "我是带参数的Toast!--->" + params, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }


}
