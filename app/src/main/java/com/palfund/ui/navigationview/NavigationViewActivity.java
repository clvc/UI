package com.palfund.ui.navigationview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.internal.NavigationMenuPresenter;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.palfund.ui.R;

import java.lang.reflect.Field;

public class NavigationViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        // 使菜单中的图标显示原样
        navigationView.setItemIconTintList(null);
        // 获取到headerView
        View headerView = navigationView.getHeaderView(0);
        TextView textView = (TextView) headerView.findViewById(R.id.tv_header);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NavigationViewActivity.this, "header", Toast.LENGTH_SHORT).show();
            }
        });
        // 为菜单添加监听
        navigationView.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_add:
                        Toast.makeText(NavigationViewActivity.this, "add", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.action_share:
                        Toast.makeText(NavigationViewActivity.this, "share", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.action_star:
                        Toast.makeText(NavigationViewActivity.this, "star", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.action_new:
                        Toast.makeText(NavigationViewActivity.this, "new", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.action_palace:
                        Toast.makeText(NavigationViewActivity.this, "palace", Toast.LENGTH_SHORT)
                                .show();
                        break;
                }
                return true;
            }
        });
        setNavigationMenuLineStyle(navigationView, Color.RED, 2);
    }


    public void setNavigationMenuLineStyle(NavigationView navigationView, @ColorInt final int
            color, final int height) {
        try {

            Field fieldByPressenter = navigationView.getClass().getDeclaredField("mPresenter");
            fieldByPressenter.setAccessible(true);
            NavigationMenuPresenter menuPresenter = (NavigationMenuPresenter) fieldByPressenter
                    .get(navigationView);
            Field fieldByMenuView = menuPresenter.getClass().getDeclaredField("mMenuView");
            fieldByMenuView.setAccessible(true);
            final NavigationMenuView mMenuView = (NavigationMenuView) fieldByMenuView.get
                    (menuPresenter);
            mMenuView.addOnChildAttachStateChangeListener(new RecyclerView
                    .OnChildAttachStateChangeListener() {
                @Override
                public void onChildViewAttachedToWindow(View view) {
                    RecyclerView.ViewHolder viewHolder = mMenuView.getChildViewHolder(view);
                    if (viewHolder != null && "SeparatorViewHolder".equals(viewHolder.getClass()
                            .getSimpleName()) && viewHolder.itemView != null) {
                        if (viewHolder.itemView instanceof FrameLayout) {
                            FrameLayout frameLayout = (FrameLayout) viewHolder.itemView;
                            View line = frameLayout.getChildAt(0);
                            line.setBackgroundColor(color);

                            line.getLayoutParams().height = (int) (height * getResources()
                                    .getDisplayMetrics().density);
                            line.setLayoutParams(line.getLayoutParams());
                        }
                    }
                }

                @Override
                public void onChildViewDetachedFromWindow(View view) {

                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
