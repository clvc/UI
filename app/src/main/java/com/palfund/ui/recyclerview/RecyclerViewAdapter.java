package com.palfund.ui.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clvc on 2017/9/5.
 * 真正的速度是看不见的 !
 * Today is today , we will go !
 */

public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewAdapter
        .RecyclerViewHolder> {
    // 数据源
    List<T> mList = new ArrayList<>();
    Context mContext;
    LayoutInflater mInflater;
    OnItemClickListener mOnItemClickListener;
    OnItemLongClickListener mOnItemLongClickListener;
    OnItemViewClickListener mOnItemViewClickListener;

    public RecyclerViewAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
    //----------------------------------------------------------------------------------------------
    //各种监听器

    /**
     * item中子view的点击事件（回调）
     */
    public interface OnItemViewClickListener<T> {
        /**
         * @param position item position
         * @param clickId 点击的view的类型，调用时根据不同的view传入不同的值加以区分
         */
        void onItemViewClick(int position, int clickId, T itemData);
    }

    /**
     * item点击事件
     */
    public interface OnItemClickListener<T> {
        void onItemClick(int position, T itemData);
    }

    /**
     * item长按事件
     */
    public interface OnItemLongClickListener<T> {
        void onItemLongClick(int position, T itemData);
    }


    /**
     * item中子view的点击事件
     */
    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener) {
        mOnItemViewClickListener = onItemViewClickListener;
    }

    /**
     * 设置item点击事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * 设置item长按事件
     *
     * @param onItemLongClickListener
     */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    //----------------------------------------------------------------------------------------------
    //更新适配器中的数据

    /**
     * 适配器中添加单条数据，刷新UI
     *
     * @param position 要添加的数据所在适配器中的位置
     * @param item     要添加的数据
     */
    public void insertItem(int position, T item) {
        mList.add(position, item);
        notifyItemInserted(position);
    }

    /**
     * 适配器中批量添加数据，刷新UI
     *
     * @param data          批量添加的集合
     * @param positionStart 添加到适配器中的起始位置
     */
    public void insertItemRange(int positionStart, List<T> data) {
        mList.addAll(positionStart, data);
        int itemCount = data.size();
        notifyItemRangeInserted(positionStart, itemCount);
    }

    /**
     * 适配器中删除单条数据，刷新UI
     *
     * @param position 要删除的数据所在适配器中的位置
     */
    public void removeItem(int position) {
        mList.remove(position);
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
            mList.remove(positionStart);
        }
        //所有的和detaDelete数据一样的都会删除掉
        //mList.removeAll(dataDelete);
        notifyItemRangeRemoved(positionStart, itemCount);
    }

    /**
     * 更新适配器中单条数据，刷新UI
     *
     * @param position 更新的数据所在适配器中的位置
     * @param item     更新后的数据
     */
    public void updateItem(int position, T item) {
        mList.remove(position);
        mList.add(position, item);
        notifyItemChanged(position);
    }

    /**
     * 批量更新适配器中的数据
     *
     * @param data          更新后的数据
     * @param positionStart 更新开始的位置
     */
    public void updateItemRange(int positionStart, List<T> data) {
        int itemCount = data.size();
        for (int i = positionStart; i < (positionStart + itemCount); i++) {
            mList.remove(positionStart);
        }
        mList.addAll(positionStart, data);
        notifyItemRangeChanged(positionStart, itemCount);
    }

    /**
     * @param fromPosition 移动前的位置
     * @param toPosition   移动后的位置
     */
    public void itemMoved(int fromPosition, int toPosition) {
        T data = mList.get(fromPosition);
        mList.remove(fromPosition);
        mList.add(toPosition, data);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void addAll(List<T> data, boolean cleared) {
        if (cleared) {
            mList.clear();
            notifyDataSetChanged();
        }
        insertItemRange(mList.size(), data);
    }

    //ViewHolder
    abstract class RecyclerViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener, View.OnLongClickListener {

        public RecyclerViewHolder(View view) {
            super(view);
            if (mOnItemClickListener != null) {
                itemView.setOnClickListener(this);
            }
            if (mOnItemLongClickListener != null) {
                itemView.setOnLongClickListener(this);
            }

        }
    }
}

