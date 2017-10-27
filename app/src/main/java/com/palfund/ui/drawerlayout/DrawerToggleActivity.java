package com.palfund.ui.drawerlayout;


import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.palfund.ui.R;

public class DrawerToggleActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_drawer_toggle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        // 抽屉开关
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R
                .string.close);
        // 同步抽屉的状态
        mDrawerToggle.syncState();
        //添加监听
        drawerLayout.addDrawerListener(mDrawerToggle);
        // 动态设置抽屉的宽度:通过设置抽屉内控件的宽度
        int pixels = getResources().getDisplayMetrics().widthPixels;
        TextView textView = (TextView) findViewById(R.id.inner);
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.width = pixels / 2;
        textView.setLayoutParams(layoutParams);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //如果不是AppCompatActivity,就复写这个方法.
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
