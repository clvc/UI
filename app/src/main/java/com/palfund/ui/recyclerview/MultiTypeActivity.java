package com.palfund.ui.recyclerview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.palfund.ui.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MultiTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_type);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        Snackbar.make(recyclerView, "text", Snackbar.LENGTH_SHORT).setAction("点击", new View
                .OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToDo
            }
        }).show();
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager
                .VERTICAL, false);
        // 合并,实现嵌套效果
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 10 == 0 ? 2 : 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        final MultiTypeAdapter adapter = new MultiTypeAdapter();
        recyclerView.setAdapter(adapter);
        final ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            if (i % 10 == 0) {
                hashMap.put("index", "Title" + (i / 10));
                list.add(hashMap);
                continue;
            }
            hashMap.put("index", "index" + i);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.star);
            hashMap.put("image", bitmap);
            list.add(hashMap);
        }
        adapter.insertItemRange(0, list);
        // ItemTouchHelper是一个处理RecyclerView的滑动删除和拖拽的辅助类
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

            //设置 允许拖拽和滑动删除的方向
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder
                    viewHolder) {
                // 指定可 拖拽方向 和 滑动消失的方向
                int dragFlags = 0, swipeFlags = 0;
                if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager ||
                        recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    //网格式布局有4个方向
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT
                            | ItemTouchHelper.RIGHT;
                    swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    //线性式布局有2个方向
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;

                    swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END; //设置侧滑方向为从两个方向都可以
                }
                // 如果某个值传0,表示不支持该功能
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            //长摁item拖拽时会回调这个方法
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                // 相同 viewType 之间才能拖动交换
                if (viewHolder.getItemViewType() == target.getItemViewType()) {
                    int from = viewHolder.getAdapterPosition();
                    int to = target.getAdapterPosition();
                    // todo拖拽操作
                    adapter.itemMoved(from, to);//更新适配器中item的位置
                    // todo拖拽操作
                    return true;
                }
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Log.i("MultiTypeActivity", "---onSwiped()--->" + direction);
                //这里处理滑动删除
                int deletePosition = viewHolder.getAdapterPosition();
                adapter.removeItem(deletePosition);
            }

            //是否支持长按开始拖拽,默认开启.可以不开启,然后在长按item的时候,手动调用mItemTouchHelper.startDrag(myHolder)开启,更加灵活
            @Override
            public boolean isLongPressDragEnabled() {
                //返回true则为所有item都设置可以拖拽
                //返回false通过itemTouchHelper.startDrag(recyclerview.getChildViewHolder(view));
                // 长按时设置拖拽item
                return true;
            }

            /**
             * 是否支持滑动删除,默认开启.可以不开启,然后在长按item的时候,手动调用mItemTouchHelper.startSwipe(myHolder)开启,更加灵活
             */
            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }

            //如果你想为item设置拖拽和滑动时的响应动画效果，可以利用ItemTouchHelper的下面三个方法。
            //当item拖拽开始时调用
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if (actionState == ItemTouchHelper.ACTION_STATE_DRAG) {
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);//拖拽时设置背景色为灰色
                }
            }

            //当item拖拽完成时调用
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundResource(R.color.colorAccent);//拖拽停止时设置背景色为白色
            }

            //当item视图变化时调用
            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder
                    viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState,
                        isCurrentlyActive);
                //根据item滑动偏移的值修改item透明度。screenwidth是我提前获得的屏幕宽度
                viewHolder.itemView.setAlpha(1 - Math.abs(dX) / getResources().getDisplayMetrics
                        ().widthPixels);
            }
        });
        // itemTouchHelper需要与recyclerView绑定才有效果
        itemTouchHelper.attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new MultiTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.textView_item1:
                        Toast.makeText(MultiTypeActivity.this, "textView   " + position, Toast
                                .LENGTH_SHORT).show();
                        break;
                    case R.id.imageView_item1:
                        Toast.makeText(MultiTypeActivity.this, "imageView   " + position, Toast
                                .LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(MultiTypeActivity.this, "LinearLayout", Toast
                                .LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.itemOne:
                        if (position % 10 != 0) {
                            // 设置拖拽
                            //itemTouchHelper.startDrag(recyclerView.getChildViewHolder(view));
                            Toast.makeText(MultiTypeActivity.this, "drag", Toast.LENGTH_SHORT)
                                    .show();
                        }
                        break;
                }
            }
        });


        /*
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        */
    }
}
