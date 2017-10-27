package com.palfund.ui.recyclerview;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.palfund.ui.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by clvc on 2017/8/8.
 * 真正的速度是看不见的 !
 * Today is today , we will go !
 */

public class MultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private OnItemClickListener mListener;
    ArrayList<HashMap<String, Object>> mData = new ArrayList<>();
    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case TYPE_ONE:
                // 必须使用此方法填充布局,否则item的match_parent属性失效
                view = inflater.inflate(R.layout.item1_recycleview, null);
                return new ViewHolderONe(view);
            case TYPE_TWO:
                view = inflater.inflate(R.layout.item2_recycleview, null);
                return new ViewHolderTwo(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HashMap<String, Object> map = mData.get(position);
        if (holder instanceof ViewHolderONe) {
            ViewHolderONe viewHolderONe = (ViewHolderONe) holder;
            String indexOne = (String) map.get("index");
            Bitmap bitmapOne = (Bitmap) map.get("image");
            viewHolderONe.mTextView.setText(indexOne);
            viewHolderONe.mImageView.setImageBitmap(bitmapOne);
            return;
        }
        if (holder instanceof ViewHolderTwo) {
            ViewHolderTwo viewHolderTwo = (ViewHolderTwo) holder;
            String indexTwo = (String) map.get("index");
            viewHolderTwo.mTextView.setText(indexTwo);
            return;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 10 == 0 ? TYPE_TWO : TYPE_ONE;
    }

    class ViewHolderONe extends RecyclerView.ViewHolder implements View.OnClickListener, View
            .OnLongClickListener {
        private ImageView mImageView;
        private TextView mTextView;
        private LinearLayout mLinearLayout;

        public ViewHolderONe(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView_item1);
            mTextView = (TextView) itemView.findViewById(R.id.textView_item1);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.itemOne);
            //mImageView.setOnClickListener(this);
            mTextView.setOnClickListener(this);
            //mLinearLayout.setOnClickListener(this);
            mLinearLayout.setOnLongClickListener(this);
        }

        // 监听器
        @Override
        public void onClick(View v) {
            int layoutPosition = getLayoutPosition();
            int adapterPosition = getAdapterPosition();
            mListener.onItemClick(v, adapterPosition);
        }

        @Override
        public boolean onLongClick(View v) {
            mListener.onItemLongClick(v, getLayoutPosition());
            Log.i("ViewHolderONe", "---onLongClick()--->" + "long");
            return true;
        }
    }

    class ViewHolderTwo extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public ViewHolderTwo(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textView_item2);
        }
    }

    interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
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
