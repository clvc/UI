package com.palfund.ui.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.palfund.ui.R;

import java.util.List;
import java.util.Map;

/**
 * Created by clvc on 2017/9/5.
 * 真正的速度是看不见的 !
 * Today is today , we will go !
 */

public class OuterAdapter extends RecyclerViewAdapter<Map<String, Object>> {
    public OuterAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.item_rv_rv_coor, parent, false);
        Log.i("OuterAdapter", "---onCreateViewHolder()--->" + "外创建");
        return new OuterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.RecyclerViewHolder holder, int position) {
        Log.i("OuterAdapter", "---onBindViewHolder()--->" + "外绑定"+position);
        OuterViewHolder outerViewHolder = (OuterViewHolder) holder;
        Map<String, Object> map = mList.get(position);
        outerViewHolder.mIv.setImageResource((Integer) map.get("image"));
        InnerAdapter adapter = new InnerAdapter(mContext);
        adapter.setOnItemViewClickListener(mOnItemViewClickListener);
        List<Integer> list = (List<Integer>) map.get("list");
        adapter.addAll(list, true);
        GridLayoutManager manager = new GridLayoutManager(mContext, 2, GridLayoutManager
                .HORIZONTAL, false);
        outerViewHolder.mRv.setLayoutManager(manager);
         outerViewHolder.mRv.setAdapter(adapter);
    }

    class OuterViewHolder extends RecyclerViewHolder {
        ImageView mIv;
        RecyclerView mRv;

        public OuterViewHolder(View view) {
            super(view);
            mRv = (RecyclerView) view.findViewById(R.id.recyclerView_item);
            mIv = (ImageView) view.findViewById(R.id.iv_item);
            mIv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            switch (v.getId()) {
                case R.id.iv_item:
                    if (mOnItemViewClickListener != null) {
                        mOnItemViewClickListener.onItemViewClick(position, R.id.iv_item, mList
                                .get(position));
                    }
                    break;
            }
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
