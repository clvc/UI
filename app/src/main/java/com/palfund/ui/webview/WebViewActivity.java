package com.palfund.ui.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化方法一
        //setContentView(R.layout.activity_web_view);
        //WebView webView = (WebView) findViewById(R.id.webView);
        // 初始化方法二
        mWebView = new WebView(this);
        setContentView(mWebView);
        // 让WebView充当打开网址的客户端，避免打开浏览器访问网址
        mWebView.setWebViewClient(new WebViewClient());
        // 让WebView支持类似alert这样的特殊javascript语句
        mWebView.setWebChromeClient(new WebChromeClient());
        // 让WebView控件支持普通的Javascript语句
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        //设置支持缩放
        settings.setSupportZoom(true);
        //设置显示缩放控制器
        settings.setDisplayZoomControls(true);
        //设置编码格式
        settings.setDefaultTextEncodingName("utf-8");

        String htmlString = "<!DOCTYPE html><html><head><meta " +
                "charset=\"UTF-8\"><title>我的第一个HTml5页面</title></head><body><script>alert" + "" +
                "('用户名不可以为空！');document.write(\"用户登录：\");</script><form action=\"http://www" + ""
                + ".baidu" + ".com\"><div>用户名：<input type=\"text\" " +
                "name=\"username\"/></div><div>密码：<input " + "type=\"password\" name=\"\" " +
                "/></div><div><input type=\"submit\" " + "value=\"登录\"></div></form></body></html>";
        //通过string.replace()方法可更改网络获取到的数据再加载到webView中
        //webView.loadData(htmlString , "text/html;charset=utf-8" , "utf-8");
        //webView.loadDataWithBaseURL(null,htmlString , "text/html" , "utf-8",null);
        mWebView.loadUrl("http://www.baidu.com");
        //webView.loadUrl("file:///android_asset/meet_yuzhi/index.html");
    }

    //处理返回键,支持返回上一页面,避免一次性退出App.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK://返回键
                //canGoBack:可以返回到前一页
                if (mWebView != null && mWebView.canGoBack()) {
                    //后退
                    mWebView.goBack();
                } else {
                    finish();
                }
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 111, 1, "JS");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 111:
                startActivity(new Intent(this, JSActivity.class));
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
