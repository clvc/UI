package com.palfund.ui.actionbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ActionProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.palfund.ui.R;

public class ActionBar2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar2);
        // 启用ActionBar图标导航功能
        ActionBar actionBar = getSupportActionBar();
        //设置导航功能图标
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_launcher_background);
        setTitle("Two");
        // 去除ActionBar阴影
        actionBar.setElevation(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_provider, menu);
        menu.add(Menu.NONE, 666, 6, "popup");
        //如果是原生的activity
        //android.view.ActionProvider provider = item.getActionProvider();
        //BlankActionProvider actionProvider = (BlankActionProvider) MenuItemCompat
        // .getActionProvider(item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // 处理导航图标点击事件
            case android.R.id.home:
                Toast.makeText(this, "导航", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_jump:
                startActivity(new Intent(this, ActionBar3Activity.class));
                break;
            case 666:
                popUpOverflow();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class BlankActionProvider extends ActionProvider {
        public BlankActionProvider(Context context) {
            super(context);
            Log.i("BlankActionProvider", "---BlankActionProvider()--->" + context);
        }

        @Override
        public View onCreateActionView() {
            return null;
        }

        @Override
        public void onPrepareSubMenu(SubMenu subMenu) {
            Log.i("BlankActionProvider", "---onPrepareSubMenu()--->");
            subMenu.clear();
            subMenu.add(0, 0, 0, "sub item 1").setIcon(R.mipmap.newer).setOnMenuItemClickListener
                    (new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Log.i("BlankActionProvider", "---onMenuItemClick()--->sub item 1");
                    return true;
                }
            });
            subMenu.add(1, 1, 1, "sub item 2").setIcon(R.mipmap.palace)
                    .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Log.i("BlankActionProvider", "---onMenuItemClick()--->sub item 2");
                    return false;
                }
            });

        }

        @Override
        public boolean hasSubMenu() {
            return true;
        }

    }

    public int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
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
        View parentView = getLayoutInflater().inflate(R.layout.activity_action_bar2, null);
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

}
