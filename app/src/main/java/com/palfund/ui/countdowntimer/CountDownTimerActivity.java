package com.palfund.ui.countdowntimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.palfund.ui.R;

public class CountDownTimerActivity extends AppCompatActivity {
    private CountDownTimer timer = new CountDownTimer(10200, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            mTextView.setText((millisUntilFinished / 1000) + "秒后可重发");
        }

        @Override
        public void onFinish() {
            mTextView.setEnabled(true);
            mTextView.setText("获取验证码");
        }
    };
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);
        mTextView = (TextView) findViewById(R.id.tv);
        timer.start();
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, 1, "finish");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        timer.cancel();
        SystemClock.sleep(3000);
        timer.onFinish();
        return super.onOptionsItemSelected(item);
    }
}
