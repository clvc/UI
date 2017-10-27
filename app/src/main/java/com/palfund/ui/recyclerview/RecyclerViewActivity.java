package com.palfund.ui.recyclerview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.SpeedRecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.palfund.ui.R;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerViewActivity extends AppCompatActivity {
    private SpeedRecyclerView mRecyclerView;
    private ActivityRecyclerViewAdapter mAdapter;
    private int preLoadIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        // 获取recyclerView
        mRecyclerView = (SpeedRecyclerView) findViewById(R.id.recyclerView);
        // 设置具有固定大小
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setSpeedRatio(0.2);
        //布局管理器:自带了3种.
        //LinearLayoutManager:
        //GridLayoutManager--->GridView
        //StaggeredGridLayoutManager--->瀑布流效果.
        //final GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager
        // .VERTICAL,
        //                false);
        //        mRecyclerView.setLayoutManager(manager);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // 设置方向
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //linearLayoutManager.setReverseLayout(true);
        // 必须设置布局管理器
        mRecyclerView.setLayoutManager(linearLayoutManager);
        // 创建适配器
        mAdapter = new ActivityRecyclerViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        // 设置动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        /*
        添加装饰线
        new SpacesItemDecoration(5)适用于linearLayoutManager、gridLayoutManager
        new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST)适用于linearLayoutManager
        可通过style中的<item name="android:listDivider">@drawable/listdivider</item>修改
         */
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration
                .VERTICAL));
        //mRecyclerView.addItemDecoration(new SpacesItemDecoration(30,30));
        // 创建数据源
        final int[] images = {R.mipmap.star, R.mipmap.palace};
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("index", "index   " + i);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), images[i % 2]);
            hashMap.put("image", bitmap);
            list.add(hashMap);
        }
        mAdapter.insertItemRange(0, list);
        // 滑动到需要展示的position
        //mRecyclerView.scrollToPosition(list.size()-1);
        mAdapter.setOnItemClickListener(new ActivityRecyclerViewAdapter
                .OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.textView_item1:
                        Toast.makeText(RecyclerViewActivity.this, "textView" + position, Toast
                                .LENGTH_SHORT).show();
                        break;
                    case R.id.imageView_item1:
                        Toast.makeText(RecyclerViewActivity.this, "imageView" + position, Toast
                                .LENGTH_SHORT).show();
                        break;
                }
            }
        });
        // 滑动监听
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            // 最后一个可见item的position
            private int mLastVisibleItemPosition;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                // 避免数据未获取回来时重复加载
                if (mLastVisibleItemPosition + 2 == linearLayoutManager.getItemCount() &&
                        mLastVisibleItemPosition != preLoadIndex) {
                    Log.i("RecyclerViewActivity", "---onScrolled()--->" + "加载数据");
                    preLoadIndex = mLastVisibleItemPosition;
                    // 预加载
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ArrayList<HashMap<String, Object>> list = new ArrayList<>();
                            for (int i = 0; i < 20; i++) {
                                HashMap<String, Object> hashMap = new HashMap<>();
                                hashMap.put("index", "add" + i);
                                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                                        images[i % 2]);
                                hashMap.put("image", bitmap);
                                list.add(hashMap);
                            }
                            mAdapter.insertItemRange(mAdapter.getItemCount(), list);
                        }
                    }, 3000);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, 1, "scroll");
        menu.add(Menu.NONE, 1, 1, "MultiType");
        menu.add(Menu.NONE, 2, 1, "Change");
        menu.add(Menu.NONE, 3, 1, "ChangeTwo");
        menu.add(Menu.NONE, 4, 1, "Staggered");
        menu.add(Menu.NONE, 5, 1, "RV_RV_Coor");
        menu.add(Menu.NONE, 6, 1, "Grid");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("index", "change");
        map.put("image", BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
        switch (item.getItemId()) {
            case 0:
                mRecyclerView.scrollToPosition(0);
                break;
            case 1:
                startActivity(new Intent(this, MultiTypeActivity.class));
                break;
            case 2:
                // 处理更新时闪屏问题
                mAdapter.updateItem(5, map);
                // change的动画执行时间为0
                mRecyclerView.getItemAnimator().setChangeDuration(0);
                break;
            case 3:
                mAdapter.updateItem(6, map);
                // 不支持改变动画
                ((SimpleItemAnimator) mRecyclerView.getItemAnimator())
                        .setSupportsChangeAnimations(false);
                break;
            case 4:
                startActivity(new Intent(this, StaggeredActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, RV_RV_CoordinatorLayoutActivity.class));
                break;
            case 6:

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
