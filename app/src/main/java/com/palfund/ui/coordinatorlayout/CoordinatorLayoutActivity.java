package com.palfund.ui.coordinatorlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.palfund.ui.R;

/**
 * BottomSheetBehavior
 * app:layout_behavior="@string/bottom_sheet_behavior"底部弹出
 * app:behavior_peekHeight="0dp"默认显示的高度
 * app:behavior_hideable="true"当peekHeight不为0时能否通过拖拽将其完全隐藏
 */
public class CoordinatorLayoutActivity extends AppCompatActivity {

    private TextView mNestedScrollView;
    private BottomSheetBehavior mBottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout);
        mNestedScrollView = (TextView) findViewById(R.id.textView);
        mBottomSheetBehavior = BottomSheetBehavior.from(mNestedScrollView);
        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            // 这里是bottomSheet状态的改变
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            // 这里是拖拽中的回调,根据slideOffset可以做一些动画
            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i("Coordinator", "---onSlide()--->" + slideOffset);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 000, 1, "bottomSheetBehavior");
        menu.add(Menu.NONE, 111, 2, "AppBarLayout");
        menu.add(Menu.NONE, 222, 3, "ScrollingActivity");
        menu.add(Menu.NONE, 333, 4, "AlipayActivity");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 000:
                Log.i("Coordinator", "---onOptionsItemSelected()--->" + mBottomSheetBehavior
                        .getState());
                mBottomSheetBehavior.setState(mBottomSheetBehavior.getState() ==
                        BottomSheetBehavior.STATE_COLLAPSED ? BottomSheetBehavior.STATE_EXPANDED
                        : BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case 111:
                startActivity(new Intent(this, AppBarActivity.class));
                break;
            case 222:
                startActivity(new Intent(this, ScrollingActivity.class));
                break;
            case 333:
                startActivity(new Intent(this,AlipayActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
