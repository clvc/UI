package com.palfund.ui.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.palfund.ui.R;

public class StaggeredActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        StaggeredAdapter adapter = new StaggeredAdapter();
        //adapter.STAGGERED_TYPE = 0;// VERTICAL
        adapter.STAGGERED_TYPE = 1;// HORIZONTAL
        recyclerView.setAdapter(adapter);
    }
}
