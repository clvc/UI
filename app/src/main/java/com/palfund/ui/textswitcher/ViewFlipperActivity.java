package com.palfund.ui.textswitcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.palfund.ui.R;

import java.util.ArrayList;
import java.util.List;

public class ViewFlipperActivity extends AppCompatActivity {
    private UPMarqueeView mUPMarqueeView;
    List<String> data = new ArrayList<>();
    List<View> views = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flipper);
        initParam();
        initdata();
        initView();
        mUPMarqueeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("SecondActivity", "---onClick()--->" + mUPMarqueeView.getChildCount());
                Log.i("SecondActivity", "---onClick()--->" + mUPMarqueeView.getCurrentView()
                        .getTag());
                Toast.makeText(ViewFlipperActivity.this, "text" + mUPMarqueeView.getCurrentView()
                        .getTag(), Toast.LENGTH_SHORT).show();
            }
        });
        // 开始翻滚
        //mUPMarqueeView.startFlipping();
        // 停止翻滚
        //mUPMarqueeView.stopFlipping();
        // 是否在翻滚
        //boolean flippinged = mUPMarqueeView.isFlipping();
        // 自动开始
        mUPMarqueeView.setAutoStart(true);
        // 是否自动开始
        //boolean autoStarted = mUPMarqueeView.isAutoStart();
    }

    /**
     * 实例化控件
     */
    private void initParam() {
        mUPMarqueeView = (UPMarqueeView) findViewById(R.id.upView1);
    }

    /**
     * 初始化界面程序
     */
    private void initView() {
        setView();
        mUPMarqueeView.setViews(views);
    }

    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     */
    private void setView() {
        for (int i = 0; i < data.size(); i = i + 2) {
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout
                    .item_view_switcher, null);
            //初始化布局的控件
            TextView tv1 = (TextView) moreView.findViewById(R.id.tv1);
            TextView tv2 = (TextView) moreView.findViewById(R.id.tv2);
            //进行对控件赋值
            tv1.setText(data.get(i).toString());
            if (data.size() > i + 1) {
                //因为淘宝那儿是两条数据，但是当数据是奇数时就不需要赋值第二个，所以加了一个判断，还应该把第二个布局给隐藏掉
                tv2.setText(data.get(i + 1).toString());
            } else {
                moreView.findViewById(R.id.tv2).setVisibility(View.GONE);
            }
            moreView.setTag(i);
            //添加到循环滚动数组里面去
            views.add(moreView);
        }
    }

    /**
     * 初始化数据
     */
    private void initdata() {
        data = new ArrayList<>();
        data.add("家人给2岁孩子喝这个，孩子智力倒退10岁!!!");
        data.add("iPhone8最感人变化成真，必须买买买买!!!!");
        data.add("简直是白菜价！日本玩家33万甩卖15万张游戏王卡");
        data.add("iPhone7价格曝光了！看完感觉我的腰子有点疼...");
        data.add("主人内疚逃命时没带够，回废墟狂挖30小时！");
        //        data.add("竟不是小米乐视！看水抢了骁龙821首发了！！！");

    }
}