package com.palfund.ui.coordinatorlayout;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.palfund.ui.R;

import java.util.ArrayList;

public class AppBarActivity extends AppCompatActivity {

    private ArrayList<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar);
        TextView tv3 = (TextView) findViewById(R.id.ViewPager_shopT);
        final TextView tv3T = (TextView) findViewById(R.id.ViewPager_shopT);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        final int tv3Bottom = tv3.getBottom();
        // 监听
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset + tv3Bottom < 0) {
                    tv3T.setVisibility(View.VISIBLE);
                } else {
                    tv3T.setVisibility(View.GONE);
                }
                Log.i("AppBarActivity", "---tv3Bottom--->" + tv3Bottom);
                Log.i("AppBarActivity", "---verticalOffset--->" + verticalOffset);
                Log.i("AppBarActivity", "---totalScrollRange--->" + appBarLayout
                        .getTotalScrollRange());
            }
        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mList = new ArrayList<>();
        for (int i = 0; i < 51; i++) {
            mList.add("item" + i);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new BaseRecyclerAdapter());
    }

    // 可见
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new BaseViewHolder(LayoutInflater.from(AppBarActivity.this).inflate(R.layout
                    .atext, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((BaseViewHolder) holder).mTextView.setText(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class BaseViewHolder extends RecyclerView.ViewHolder {
            private TextView mTextView;

            public BaseViewHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.textView);
            }
        }
    }
}
