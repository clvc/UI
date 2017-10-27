package com.palfund.ui.actionbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by clvc on 2017/7/31.
 * 真正的速度是看不见的 !
 * Today is today , we will go !
 */

public class SecondFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText("Second Fragment");
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout layout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout
                .LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(textView, params);
        return layout;
    }
}
