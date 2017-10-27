package com.palfund.ui.toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.palfund.ui.R;

public class ToolbarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 取消标题栏
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        //getSupportActionBar();//一定要在setSupportActionBar(Toolbar toolbar)之后调用
        //修改PopupWindow的样式
        //mToolbar.setPopupTheme(R.style.AppTheme);
        //设置导航图标(home菜单)
        toolbar.setNavigationIcon(R.drawable.bg);
        // 设置导航菜单(Home菜单)的点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ToolbarActivity.this, "toast", Toast.LENGTH_SHORT).show();
            }
        });
        // 填充菜单
        //        toolbar.inflateMenu(R.menu.actionbar);
        //        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
        //            @Override
        //            public boolean onMenuItemClick(MenuItem item) {
        //                switch (item.getItemId()) {
        //                    case R.id.action_search0:
        //                        Toast.makeText(ToolbarActivity.this, "search", Toast
        // .LENGTH_SHORT).show();
        //                        break;
        //                    case R.id.action_share:
        //                        Toast.makeText(ToolbarActivity.this, "share", Toast
        // .LENGTH_SHORT).show();
        //                        break;
        //                    case R.id.action_updata:
        //                        Toast.makeText(ToolbarActivity.this, "updata", Toast
        // .LENGTH_SHORT).show();
        //                        break;
        //                }
        //                return false;
        //            }
        //        });

    }

    /**
     * 每次菜单被关闭时调用.
     * 菜单被关闭有三种情形:
     * 1.展开menu的按钮被再次点击
     * 2.back按钮被点击
     * 3.用户选择了某一个菜单项
     */
    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    /**
     * 在onCreateOptionsMenu执行后，菜单被显示前调用；如果菜单已经被创建，则在菜单显示前被调用。 同样的，
     * 返回true则显示该menu,false 则不显示; （可以通过此方法动态的改变菜单的状态，比如加载不同的菜单等）
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 000, 1, "CusomToolbar");
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 000:
                startActivity(new Intent(this, CustomToolbarActivity.class));
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
