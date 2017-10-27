package com.palfund.ui.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.palfund.ui.R;

/**
 * Created by clvc on 2017/9/6.
 * 真正的速度是看不见的 !
 * Today is today , we will go !
 */

public class InnerAdapter extends RecyclerViewAdapter<Integer> {
    public InnerAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_inner, null);
        Log.i("InnerAdapter", "---onCreateViewHolder()--->" + "内创建");
        return new InnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.RecyclerViewHolder holder, int position) {
        InnerViewHolder innerViewHolder = (InnerViewHolder) holder;
        innerViewHolder.mIv_inner.setImageResource(mList.get(position));
        Log.i("InnerAdapter", "---onBindViewHolder()--->" + "内绑定" + position);
    }

    class InnerViewHolder extends RecyclerViewHolder {
        ImageView mIv_inner;

        public InnerViewHolder(View view) {
            super(view);
            mIv_inner = (ImageView) view.findViewById(R.id.iv_itemInner);
            mIv_inner.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mOnItemViewClickListener.onItemViewClick(position, R.id.iv_itemInner, mList.get
                    (position));
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
