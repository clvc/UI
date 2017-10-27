package com.palfund.ui.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.palfund.ui.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by clvc on 2017/8/7.
 * 真正的速度是看不见的 !
 * Today is today , we will go !
 * <p>
 * 实现单选,多选功能
 */

public class ActivityRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // 实现单选/多选,存储选择的Item
    public static HashSet<Integer> mSet = new HashSet<>();
    // 单选
    private static View curView;
    private ArrayList<HashMap<String, Object>> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private static OnRecyclerViewItemClickListener mListener;

    public ActivityRecyclerViewAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("AdapterOld", "---onCreateViewHolder()--->");
        // 必须使用此方法填充布局,否则item的match_parent属性失效
        View view = mInflater.inflate(R.layout.item1_recycleview, parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i("AdapterOld", "---onBindViewHolder()--->" + position);
        HashMap<String, Object> hashMap = mData.get(position);
        String index = (String) hashMap.get("index");
        Bitmap bitmap = (Bitmap) hashMap.get("image");
        BaseViewHolder baseViewHolder = (BaseViewHolder) holder;
        baseViewHolder.mTextView.setText(index);
        baseViewHolder.mImageView.setImageBitmap(bitmap);
        if (mSet.contains(position)){
            baseViewHolder.mTextView.setBackgroundColor(Color.BLACK);
        }else {
            baseViewHolder.mTextView.setBackgroundResource(R.color.colorAccent);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;
        private TextView mTextView;

        public BaseViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView_item1);
            mTextView = (TextView) itemView.findViewById(R.id.textView_item1);
            //mImageView.setOnClickListener(this);
            mTextView.setOnClickListener(this);
            Log.i("BaseViewHolder", "---BaseViewHolder()--->");
        }

        @Override
        public void onClick(View v) {
            //获取子控件在布局中显示的位置
            int layoutPosition = getLayoutPosition();
            //获取在适配器中的位置
            int adapterPosition = getAdapterPosition();
            // 实现单选/多选
            if (mSet.contains(adapterPosition)) {
                // 包含,表明第二次点击,移除
                boolean removed = mSet.remove(adapterPosition);
                v.setBackgroundResource(R.color.colorAccent);
                curView = null;
                Log.i("BaseViewHolder", "---removed--->" + removed);
            } else {
                if (curView != null) {
                    curView.setBackgroundResource(R.color.colorAccent);
                    mSet.clear();
                }
                // 不包含,第一次点击,添加
                boolean added = mSet.add(adapterPosition);
                v.setBackgroundColor(Color.BLACK);
                Log.i("BaseViewHolder", "---onClick()--->" + added);
                curView = v;
            }
            Log.i("BaseViewHolder", "---onClick()--->" + layoutPosition + "   " + adapterPosition);
            mListener.onItemClick(v, adapterPosition);
        }
    }

    interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mListener = listener;
    }
    //----------------------------------------------------------------------------------------------
    //更新适配器中的数据

    /**
     * 适配器中添加单条数据，刷新UI
     *
     * @param position 要添加的数据所在适配器中的位置
     * @param item     要添加的数据
     */
    public void insertItem(int position, HashMap<String, Object> item) {
        mData.add(position, item);
        notifyItemInserted(position);
    }

    /**
     * 适配器中批量添加数据，刷新UI
     *
     * @param data          批量添加的集合
     * @param positionStart 添加到适配器中的起始位置
     */
    public void insertItemRange(int positionStart, List<HashMap<String, Object>> data) {
        mData.addAll(positionStart, data);
        int itemCount = data.size();
        notifyItemRangeInserted(positionStart, itemCount);
    }

    /**
     * 适配器中删除单条数据，刷新UI
     *
     * @param position 要删除的数据所在适配器中的位置
     */
    public void removeItem(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 适配器中批量删除多条数据，刷新UI
     *
     * @param positionStart 删除的起始位置
     * @param itemCount     删除的条数
     */
    public void removeItemRange(int positionStart, int itemCount) {
        for (int i = positionStart; i < (positionStart + itemCount); i++) {
            mData.remove(positionStart);
        }
        //所有的和detaDelete数据一样的都会删除掉
        //        mData.removeAll(dataDelete);
        notifyItemRangeRemoved(positionStart, itemCount);
    }

    /**
     * 更新适配器中单条数据，刷新UI
     *
     * @param position 更新的数据所在适配器中的位置
     * @param item     更新后的数据
     */
    public void updateItem(int position, HashMap<String, Object> item) {
        mData.remove(position);
        mData.add(position, item);
        notifyItemChanged(position);
    }

    /**
     * 批量更新适配器中的数据
     *
     * @param dataAdd       更新后的数据
     * @param positionStart 更新开始的位置
     */
    public void updateItemRange(int positionStart, List<HashMap<String, Object>> dataAdd) {
        int itemCount = dataAdd.size();
        for (int i = positionStart; i < (positionStart + itemCount); i++) {
            mData.remove(positionStart);
        }
        mData.addAll(positionStart, dataAdd);
        notifyItemRangeChanged(positionStart, itemCount);
    }

    /**
     * @param fromPosition 移动前的位置
     * @param toPosition   移动后的位置
     */
    public void itemMoved(int fromPosition, int toPosition) {
        HashMap<String, Object> data = mData.get(fromPosition);
        mData.remove(fromPosition);
        mData.add(toPosition, data);
        notifyItemMoved(fromPosition, toPosition);
    }
}
