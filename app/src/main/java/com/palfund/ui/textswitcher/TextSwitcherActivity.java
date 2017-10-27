package com.palfund.ui.textswitcher;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.palfund.ui.R;

public class TextSwitcherActivity extends AppCompatActivity {
    private String[] news = {"中国", "湖北", "武汉", "武汉理工大学", "计算机科学与技术"};
    private TextSwitcher mTextSwitcher;
    private int index = 0;
    // 是否翻滚
    private boolean runned = true;
    private Handler mHandler = new Handler();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mTextSwitcher.setText(news[index]);
                    index++;
                    if (index == news.length) {
                        index = 0;
                    }
                    if (runned) {
                        handler.sendEmptyMessageDelayed(0, 2000);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_switcher);
        mTextSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(TextSwitcherActivity.this);
                textView.setSingleLine();
                textView.setTextSize(22);
                textView.setTextColor(Color.parseColor("#ffffff"));
                textView.setEllipsize(TextUtils.TruncateAt.END);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup
                        .LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_VERTICAL;
                textView.setLayoutParams(params);
                return textView;
            }
        });
        mTextSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TextSwitcherActivity.this, news[index - 1], Toast.LENGTH_SHORT)
                        .show();
            }
        });
        handler.sendEmptyMessage(0);
        //mHandler.post(new SwitcherRunnable());

    }

    class SwitcherRunnable implements Runnable {
        @Override
        public void run() {
            mTextSwitcher.setText(news[index]);
            index++;
            if (index == news.length) {
                index = 0;
            }
            mHandler.postDelayed(this, 3000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, 1, "cancel");
        menu.add(Menu.NONE, 2, 1, "ViewFlipper");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                if (!runned) {
                    handler.sendEmptyMessage(0);
                }
                runned = !runned;
                break;
            case 2:
                startActivity(new Intent(this, ViewFlipperActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
