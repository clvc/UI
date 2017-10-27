package com.palfund.ui.viewstub;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;

import com.palfund.ui.R;

/**
 * ViewStub 是一个轻量级的View，没有尺寸，不绘制任何东西，因此绘制或者移除时更省时。(ViewStub不可见，大小为0)
 * 实现View的延迟加载，避免资源的浪费，减少渲染时间，在需要的时候才加载View
 * <p>
 * ViewStub所要替代的layout文件中不能有<merge>标签
 * ViewStub在加载完后会被移除，或者说是被加载进来的layout替换掉了
 * <p>
 * 一旦ViewStub visible/inflated，则ViewStub将从视图框架中移除，其id stub_import 也会失效
 * ViewStub被绘制完成的layout文件取代，并且该layout文件的root view的id是android:inflatedId指定的id panel_import，root
 * view的布局和ViewStub视图的布局保持一致
 */
public class ViewStubActivity extends AppCompatActivity {
    private ViewStub mViewStub;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_view);
        mViewStub = (ViewStub) findViewById(R.id.viewSub);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, 1, "show");
        menu.add(Menu.NONE, 2, 1, "hide");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                // 显示ViewStub
                //mViewStub.setVisibility(View.VISIBLE);
                if (findViewById(R.id.viewSub) != null) {
                    mView = mViewStub.inflate();
                } else {
                    mView.setVisibility(View.VISIBLE);
                }
                break;
            case 2:
                mView.setVisibility(View.GONE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
