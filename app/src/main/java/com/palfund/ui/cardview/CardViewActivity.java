package com.palfund.ui.cardview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.palfund.ui.R;

import java.util.Random;

public class CardViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        int i = new Random().nextInt(10);
    }
}
