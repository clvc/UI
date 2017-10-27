package com.palfund.ui.flexboxlayout;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.palfund.ui.R;

/**
 * Created by clvc on 2017/8/11.
 * 真正的速度是看不见的 !
 * Today is today , we will go !
 */

public class TextViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String[] texts = new String[]{"aaaaaaa", "aaaaaaaaa", "aaaaaaa", "aaaaaaaaaa",
            "aaaaaa", "aaaaaaaaaaa", "aaaaa", "aaaaaaaaaaaa", "aaaa", "aaaaaaaaaaaaa", "aaa",
            "aaaaaaaaaaaaaa", "aa", "aaaaaaaaaaaaaaa", "a", "aaaaaaaaaaaaaaa"};

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_flexboxlayout4recyclerview_textview, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TextViewHolder) holder).mTextView.setText(texts[position]);
    }

    @Override
    public int getItemCount() {
        return texts.length;
    }


    class TextViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public TextViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textView);
            CardView cardView = (CardView) itemView.findViewById(R.id.cardView);
            ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
            if (layoutParams instanceof FlexboxLayoutManager.LayoutParams) {
                Log.i("TextViewHolder", "---TextViewHolder()--->");
                FlexboxLayoutManager.LayoutParams lp = (FlexboxLayoutManager.LayoutParams)
                        layoutParams;
                lp.setFlexGrow(1.0f);
            }
        }
    }
}
