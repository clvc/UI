package com.palfund.ui.flexboxlayout;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.palfund.ui.R;

/**
 * Created by clvc on 2017/8/11.
 * 真正的速度是看不见的 !
 * Today is today , we will go !
 */

public class ImageViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    int[] imageIds = new int[]{R.drawable.water8, R.drawable.water1, R.drawable.water2, R
            .drawable.water3, R.drawable.water4, R.drawable.water5, R.drawable.water8, R.drawable
            .water1, R.drawable.water2, R.drawable.water3, R.drawable.water4, R.drawable.water8,
            R.drawable.water5, R.drawable.water6, R.drawable.water7, R.drawable.water8, R
            .drawable.water1, R.drawable.water8, R.drawable.water2, R.drawable.water3, R.drawable
            .water4, R.drawable.water5};

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .item_flexboxlayout4recyclerview_imageview2, parent, false);
        return new FlexBoxLayoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((FlexBoxLayoutViewHolder) holder).bindTo(position);
    }

    @Override
    public int getItemCount() {
        return imageIds.length;
    }

    class FlexBoxLayoutViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        public FlexBoxLayoutViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }

        void bindTo(int position) {
            mImageView.setImageResource(imageIds[position]);
            ViewGroup.LayoutParams lp = mImageView.getLayoutParams();
            if (lp instanceof FlexboxLayoutManager.LayoutParams) {
                Log.i("FlexBoxLayoutViewHolder", "---bindTo()--->");
                FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams)
                        lp;
                flexboxLp.setFlexGrow(1.0f);
            }
        }
    }
}
