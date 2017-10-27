package com.palfund.ui.actionbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.palfund.ui.R;

public class ActionBar3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar3);
        // 启用ActionBar导航功能
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 设置显示logo
        actionBar.setLogo(R.drawable.favorite);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        setTitle("Three");
        // 添加导航tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.Tab tab = actionBar.newTab().setText("first").setTabListener(new
                TabListener<FirstFragment>(this, "artist", FirstFragment.class));
        actionBar.addTab(tab);
        tab = actionBar.newTab().setText("second").setTabListener(new TabListener<SecondFragment>
                (this, "album", SecondFragment.class));
        actionBar.addTab(tab);
        // 与ViewPager配合使用时
        //actionBar.setSelectedNavigationItem(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // NavUtils.getParentActivityIntent()方法可以获取到跳转至父Activity的Intent
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                // 如果父Activity和当前Activity不是在同一个Task中的，则需要借助TaskStackBuilder来创建一个新的Task
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent)
                            .startActivities();
                } else {
                    // 如果父Activity和当前Activity是在同一个Task中的,则直接调用navigateUpTo()方法进行跳转
                    upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
