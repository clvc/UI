package com.palfund.ui.countdowntimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.palfund.ui.R;

public class CountDownTimerActivity extends AppCompatActivity {
    private CountDownTimer timer = new CountDownTimer(10200, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mTextView.setText((millisUntilFinished / 1000) + "秒后可重发");
        }

        @Override
        public void onFinish() {
            mTextView.setClickable(true);
            mTextView.setText("获取验证码");
            Toast.makeText(CountDownTimerActivity.this, "finish", Toast.LENGTH_SHORT).show();
        }
    };
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_down_timer);
        mTextView = (TextView) findViewById(R.id.tv);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
                mTextView.setClickable(false);
            }
        });
        timer.start();
        mTextView.setClickable(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, 1, "cancel");
        menu.add(Menu.NONE, 2, 1, "finish");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                timer.cancel();
                break;
            case 2:
                timer.cancel();
                timer.onFinish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
