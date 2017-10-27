package com.palfund.ui.topfloat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.palfund.ui.R;

public class TopFloatActivity extends AppCompatActivity implements TopFloatScrollView
        .OnScrollListener {
    private EditText search_edit;
    private TopFloatScrollView tfScrollView;
    private int searchLayoutTop;
    LinearLayout top1, top2;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_float);
        //初始化控件
        init();
    }

    private void init() {
        search_edit = (EditText) findViewById(R.id.search_edit);
        tfScrollView = (TopFloatScrollView) findViewById(R.id.topFloatScrollView);
        top1 = (LinearLayout) findViewById(R.id.top1);
        top2 = (LinearLayout) findViewById(R.id.top2);
        //rlayout = (RelativeLayout)findViewById(R.id.rlayout);
        mTv = (TextView) findViewById(R.id.tv);
        tfScrollView.setOnScrollListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            searchLayoutTop = mTv.getBottom();//获取searchLayout的顶部位置
            Log.i("TopFloatActivity", "---onWindowFocusChanged()--->" + searchLayoutTop);
        }
    }

    //监听滚动Y值变化，通过addView和removeView来实现悬停效果
    @Override
    public void onScroll(int scrollY) {
        Log.i("TopFloatActivity", "---onScroll()--->" + mTv.getBottom());
        if (scrollY >= searchLayoutTop) {
            if (search_edit.getParent() != top1) {
                top2.removeView(search_edit);
                top1.addView(search_edit);
            }
        } else {
            if (search_edit.getParent() != top2) {
                top1.removeView(search_edit);
                top2.addView(search_edit);
            }
        }
    }
}