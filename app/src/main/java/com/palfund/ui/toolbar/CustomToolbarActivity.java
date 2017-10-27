package com.palfund.ui.toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.palfund.ui.R;

public class CustomToolbarActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 取消标题栏
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_custom_toolbar);
        ImageView iv_click = (ImageView) findViewById(R.id.click);
        ImageView iv_star = (ImageView) findViewById(R.id.star);
        TextView tv_right = (TextView) findViewById(R.id.right);
        iv_click.setOnClickListener(this);
        iv_star.setOnClickListener(this);
        tv_right.setOnClickListener(this);
        BaseToolbar toolbar = (BaseToolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("title");
        toolbar.setTitleTextColor(Color.RED);
        toolbar.setSubtitle("subtitle");
        toolbar.setSubtitleTextColor(Color.GREEN);
        // 设置toolbar背景色
        //toolbar.setBackgroundResource(android.R.color.black);
        toolbar.setOverflowIcon(getDrawable(R.mipmap.more));
        // 设置logo
        toolbar.setLogo(R.mipmap.star);
        // 让当前toolbar支持ActionBar(兼容ActionBar的功能),调用此方法后,无法填充menu
        setSupportActionBar(toolbar);
        // getSupportActionBar();//一定要在setSupportActionBar(Toolbar toolbar)之后调用
        //修改PopupWindow的样式
        //mToolbar.setPopupTheme(R.style.AppTheme);
        //设置导航图标(home菜单)
        toolbar.setNavigationIcon(R.drawable.bg);
        // 设置导航菜单(Home菜单)的点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomToolbarActivity.this, "toast", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.click:
                Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.star:
                Toast.makeText(this, "star", Toast.LENGTH_SHORT).show();
                break;
            case R.id.right:
                Toast.makeText(this, "right", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,676,2,"test");
        return super.onCreateOptionsMenu(menu);
    }
}
