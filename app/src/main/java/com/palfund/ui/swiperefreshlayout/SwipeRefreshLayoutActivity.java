package com.palfund.ui.swiperefreshlayout;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.palfund.ui.R;
import com.palfund.ui.recyclerview.ActivityRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SwipeRefreshLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        final ActivityRecyclerViewAdapter adapter = new ActivityRecyclerViewAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        // 取消动画
        recyclerView.setItemAnimator(null);
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("index", "item" + i);
            hashMap.put("image", BitmapFactory.decodeResource(getResources(), R.mipmap.star));
            list.add(hashMap);
        }
        adapter.insertItemRange(0, list);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id
                .swipeRefreshLayout);
        // 设置进度条的变化颜色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R
                .color.holo_green_light, android.R.color.holo_red_light, android.R.color
                .holo_orange_light);
        // 设置进度圆圈的大小:默认值DEFAULT和大的值LARGE.
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        // 设置进度圈的背景色
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.BLACK);
        // 设置监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // todo 刷新
                new Thread() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        final ArrayList<HashMap<String, Object>> list = new ArrayList<>();
                        for (int i = 60; i < 80; i++) {
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("index", "item" + i);
                            hashMap.put("image", BitmapFactory.decodeResource(getResources(), R
                                    .mipmap.star));
                            list.add(hashMap);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 设置是否加载
                                swipeRefreshLayout.setRefreshing(false);
                                adapter.insertItemRange(0, list);
                                recyclerView.scrollToPosition(0);
                            }
                        });

                    }
                }.start();
            }
        });


    }
}
