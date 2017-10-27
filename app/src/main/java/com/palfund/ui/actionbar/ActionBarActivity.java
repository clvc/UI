package com.palfund.ui.actionbar;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.palfund.ui.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ActionBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);
        ActionBar actionBar = getSupportActionBar();
        // 隐藏actionBar
        actionBar.hide();
        // 显示actionBar
        actionBar.show();
        //设置导航功能图标
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_background);
        // 设置显示logo
        actionBar.setLogo(R.drawable.favorite);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        // 设置显示标题
        setTitle("One");
        // 去掉标题内容
        actionBar.setDisplayShowTitleEnabled(false);
        Log.i("ActionBarActivity", "---onCreate()--->");
        // 通过反射使OverflowIcon永远显示,避免有物理Menu键的手机不显示
        setOverflowShowingAlways();
        // 去除ActionBar阴影
        actionBar.setElevation(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 通过反射使OptionsMenu的Icon图标可见:
        setMenuIconsVisible(menu, true);
        menu.add(Menu.NONE, 323, 1, "ActionBarConstom");
        menu.add(Menu.NONE, 989, 1, "ActionMode");
        menu.add(Menu.NONE, 987, 1, "NavigationListener");
        getMenuInflater().inflate(R.menu.actionbar, menu);
        // 得到menu里的某个菜单项
        MenuItem item = menu.findItem(R.id.action_search0);
        //如果是原生的Activity.
        // SearchView searchView = (SearchView) item.getActionView();
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        // SearchView的文本监听方法
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当查询文本被提交的时候调用方法.
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i("TAG", "query=" + query);
                return false;
            }

            // 查询文本改变的时候调用
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i("TAG", "newText=" + newText);
                return false;
            }
        });
        // SearchView的展开关闭监听
        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(ActionBarActivity.this, "open", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(ActionBarActivity.this, "close", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_jump:
                startActivity(new Intent(this, ActionBar2Activity.class));
                break;
            case R.id.action_add:
                // popupWindow实现overflow
                popUpOverflow();
                break;
            case 323:
                startActivity(new Intent(this, ActionBarCustomActivity.class));
                break;
            case 989:
                startActivity(new Intent(this, ActionModeActivity.class));
                break;
            case 987:
                startActivity(new Intent(this, NavigationListActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // 通过反射使OptionsMenu的Icon图标可见:
    private void setMenuIconsVisible(Menu menu, boolean flag) {
        try {
            //得到字节码
            Class<?> clazz = Class.forName("android.support.v7.view.menu.MenuBuilder");
            //根据字节码得到类中指定的方法                      方法名                     参数类型
            Method mMethod = clazz.getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            //设置可访问性--->暴力反射.
            mMethod.setAccessible(true);
            //执行方法.
            mMethod.invoke(menu, flag);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // 通过反射使OverflowIcon永远显示,避免有物理Menu键的手机不显示
    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void popUpOverflow() {
        /**
         * 定位PopupWindow，让它恰好显示在Action Bar的下方。 通过设置Gravity，确定PopupWindow的大致位置。
         * 首先获得状态栏的高度，再获取Action bar的高度，这两者相加设置y方向的offset样PopupWindow就显示在action
         * bar的下方了。 通过dp计算出px，就可以在不同密度屏幕统一X方向的offset.但是要注意不要让背景阴影大于所设置的offset，
         * 否则阴影的宽度为offset.
         */
        // 获取状态栏高度
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        //        状态栏高度：frame.top
        int xOffset = frame.top + getSupportActionBar().getHeight() - 0;//减去阴影宽度，适配UI.
        int yOffset = Dp2Px(this, 5f); //设置x方向offset为5dp
        View parentView = getLayoutInflater().inflate(R.layout.activity_action_bar, null);
        View popView = getLayoutInflater().inflate(R.layout.popupwindow_imp_overflow, null);
        PopupWindow popWind = new PopupWindow(popView, LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);
        //popView即popupWindow的布局，ture设置focusAble.
        //必须设置BackgroundDrawable后setOutsideTouchable(true)才会有效。这里在XML中定义背景，所以这里设置为null;
        popWind.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        popWind.setOutsideTouchable(true); //点击外部关闭。
        //设置一个动画。
        popWind.setAnimationStyle(android.R.style.Animation_Dialog);
        //设置Gravity，让它显示在右上角。
        popWind.showAtLocation(parentView, Gravity.RIGHT | Gravity.TOP, yOffset, xOffset);
    }

    public int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
