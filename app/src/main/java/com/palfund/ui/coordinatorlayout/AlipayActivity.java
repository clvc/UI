package com.palfund.ui.coordinatorlayout;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.palfund.ui.R;

public class AlipayActivity extends AppCompatActivity {
    private boolean collapsed = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alipay);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        final View toolbar1 = findViewById(R.id.toolbar1);
        final View toolbar2 = findViewById(R.id.toolbar2);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                int toolbar1Bottom = toolbar1.getBottom();
                int toolbar2Bottom = toolbar2.getBottom();
                Log.i("AlipayActivity", "---toolbar1 Bottom--->" + toolbar1Bottom);
                Log.i("AlipayActivity", "---toolbar2 Bottom--->" + toolbar2Bottom);
                int appBarLayoutBottom = appBarLayout.getBottom();
                Log.i("AlipayActivity", "---appBarLayout bottom--->" + appBarLayoutBottom);
                float percent = Math.abs(((float) (verticalOffset)) / totalScrollRange);
                Log.i("AlipayActivity", "---verticalOffset--->" + verticalOffset);
                Log.i("AlipayActivity", "---totalScrollRange--->" + totalScrollRange);
                Log.i("AlipayActivity", "------>" + percent);
                toolbar1.setAlpha(1 - 3 * percent);
                toolbar2.setAlpha(percent);
                if (percent < 0.2) {
                    toolbar1.setVisibility(View.VISIBLE);
                    toolbar2.setVisibility(View.GONE);
                } else {
                    toolbar1.setVisibility(View.GONE);
                    toolbar2.setVisibility(View.VISIBLE);
                }
            }
        });
        View header = findViewById(R.id.header);
        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("AlipayActivity", "---onClick()--->" + "header");
            }
        });
        toolbar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("AlipayActivity", "---onClick()--->" + "toolbar1");
            }
        });
        toolbar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("AlipayActivity", "---onClick()--->" + "toolbar2");
            }
        });

    }
}
