package com.palfund.ui.flexboxlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.palfund.ui.R;

public class RecyclerView4FBLActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view4_fbl);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        FlexboxLayoutManager manager = new FlexboxLayoutManager(FlexDirection.ROW, FlexWrap.WRAP);
        // 设置排列方式
        manager.setJustifyContent(JustifyContent.SPACE_BETWEEN);
        recyclerView.setLayoutManager(manager);
        //ImageViewAdapter adapter = new ImageViewAdapter();
        TextViewAdapter adapter = new TextViewAdapter();
        recyclerView.setAdapter(adapter);
    }
}
