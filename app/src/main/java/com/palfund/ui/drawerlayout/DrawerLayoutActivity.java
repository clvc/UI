package com.palfund.ui.drawerlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.palfund.ui.R;

public class DrawerLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_drawer_layout);
        // 设置屏幕全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN);
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            // 抽屉滑动过程中调用的方法
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                Log.i("DrawerLayoutActivity", "---onDrawerSlide()--->");
            }

            // 抽屉已经打开后调用的方法
            @Override
            public void onDrawerOpened(View drawerView) {
                Log.i("DrawerLayoutActivity", "---onDrawerOpened()--->");
            }

            // 抽屉关闭后调用的方法
            @Override
            public void onDrawerClosed(View drawerView) {
                Log.i("DrawerLayoutActivity", "---onDrawerClosed()--->");
            }

            // 抽屉状态改变的方法
            @Override
            public void onDrawerStateChanged(int newState) {
                Log.i("DrawerLayoutActivity", "---onDrawerStateChanged()--->");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 666, 1, "DrawerToggle");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 666:
                startActivity(new Intent(this, DrawerToggleActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
