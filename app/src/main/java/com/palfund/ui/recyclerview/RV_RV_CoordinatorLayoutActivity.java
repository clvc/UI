package com.palfund.ui.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.palfund.ui.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * android:nestedScrollingEnabled="false"解决RecyclerView嵌套RecyclerView结合CoordinatorLayout
 * 滑动内部RecyclerView,CoordinatorLayout不响应的bug
 * 新的嵌套滑动机制增加支持子View和父View共同处理滑动事件的能力，子View处理事件的时候，能通知父View同时处理。
 * CoordinatorLayout实现了NestedScrollingParent，纵向RecyclerView是CoordinatorLayout的子View，RecyclerView
 * 的滑动能通知到CoordinatorLayout，继而由CoordinatorLayout协调让CollapsingToolbarLayout发生折叠。
 * 横向RecyclerView的父View是纵向RecyclerView，而RecyclerView只实现了NestedScrollingChild，无法像CoordinatorLayout
 * 一样响应。所以要关闭横向RecyclerView的嵌套滑动功能，让横向RecyclerView如同其他嵌入纵向RecyclerView的view一样，触发折叠。
 */
public class RV_RV_CoordinatorLayoutActivity extends AppCompatActivity implements
        RecyclerViewAdapter.OnItemViewClickListener<Object> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv__rv__coordinator_layout);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        OuterAdapter adapter = new OuterAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter.setOnItemViewClickListener(this);
        recyclerView.setAdapter(adapter);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", R.mipmap.ic_launcher);
            List<Integer> list = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                list.add(R.drawable.click);
                list.add(R.drawable.fire);
                list.add(R.drawable.flash);
                list.add(R.drawable.icon_book);
                list.add(R.drawable.message);
                list.add(R.drawable.more);
                list.add(R.drawable.notice);
                list.add(R.drawable.rain);
                list.add(R.drawable.running);
                list.add(R.drawable.star);
                list.add(R.drawable.water3);
            }
            map.put("list", list);
            mapList.add(map);
        }
        adapter.addAll(mapList, false);
    }

    @Override
    public void onItemViewClick(int position, int clickId, Object itemData) {
        switch (clickId) {
            case R.id.iv_item:
                Integer id = (Integer) ((Map<String, Object>) itemData).get("image");
                Toast.makeText(this, "outer" + id, Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_itemInner:
                Toast.makeText(this, "inner" + (Integer) itemData, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
