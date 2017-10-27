package com.palfund.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.palfund.ui.R;

/**
 * Created by clvc on 2017/8/11.
 * 真正的速度是看不见的 !
 * Today is today , we will go !
 */

public class StaggeredAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public int STAGGERED_TYPE;

    int[] imageIds = new int[]{R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R
            .drawable.p5, R.drawable.p6, R.drawable.p7, R.drawable.p8, R.drawable.p9, R.drawable.p10, R.drawable.p11, R.drawable.p12, R.drawable.p13};

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (STAGGERED_TYPE) {
            case 0:
                view = inflater.inflate(R.layout.item_staggered_vertical, null);
                return new StaggeredViewHolder(view);
            case 1:
                view = inflater.inflate(R.layout.item_staggered_horizontal, null);
                return new StaggeredViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((StaggeredViewHolder) holder).mImageView.setImageResource(imageIds[position]);
    }

    @Override
    public int getItemCount() {
        return imageIds.length;
    }

    class StaggeredViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public StaggeredViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
