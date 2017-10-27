package com.palfund.ui.coordinatorlayout;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.palfund.ui.R;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // 获取可折叠的toolbar
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        // 设置标题
        collapsingToolbarLayout.setTitle("collapsingToolbarLayout");
        // 设置折叠时的文字颜色
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.RED);
        // 展开式的文字颜色
        collapsingToolbarLayout.setExpandedTitleColor(Color.YELLOW);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //展示SnackBar
                Snackbar snackbar = Snackbar.make(view, "Replace with your own action", Snackbar
                        .LENGTH_LONG).setAction("点我啊", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ScrollingActivity.this, "点击了snackbar..", Toast
                                .LENGTH_SHORT).show();
                    }
                }).setActionTextColor(Color.RED);

                //获取SnackBar的背景视图
                View v = snackbar.getView();
                v.setBackgroundColor(Color.BLUE);
                // 修改字体颜色
                ((TextView) v.findViewById(R.id.snackbar_text)).setTextColor(Color.RED);
                snackbar.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        // snackbar消失的时候调用
                        super.onDismissed(transientBottomBar, event);
                    }

                    @Override
                    public void onShown(Snackbar transientBottomBar) {
                        // snackbar展示的时候调用
                        super.onShown(transientBottomBar);
                    }
                });
                //不要忘记show出来
                snackbar.show();
                //snackbar.dismiss();
            }
        });
    }
}
