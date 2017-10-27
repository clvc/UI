package com.palfund.ui.autocompletetextview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.palfund.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clvc on 2017/10/18.
 * 真正的速度是看不见的 !
 * Today is today , we will go !
 */

public class ActvAdapter extends BaseAdapter implements Filterable {
    private LayoutInflater mInflater;
    private List<Book> mList;//展示的数据
    private ArrayFilter mArrayFilter;
    private List<Book> filterList;//需要过滤的数据

    public ActvAdapter(Context context, List<Book> list) {
        mInflater = LayoutInflater.from(context);
        mList = list;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_actv, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.id.setText(String.valueOf(mList.get(position).id));
        viewHolder.price.setText(String.valueOf(mList.get(position).price));
        viewHolder.name.setText(mList.get(position).name);
        viewHolder.author.setText(mList.get(position).author);
        viewHolder.pinyin.setText(mList.get(position).pinyin);
        return convertView;
    }

    class ViewHolder {
        public TextView id;
        public TextView name;
        public TextView author;
        public TextView price;
        public TextView pinyin;

        public ViewHolder(View view) {
            id = (TextView) view.findViewById(R.id.id);
            name = (TextView) view.findViewById(R.id.name);
            author = (TextView) view.findViewById(R.id.author);
            pinyin = (TextView) view.findViewById(R.id.pinyin);
            price = (TextView) view.findViewById(R.id.price);
        }

    }

    ///////////////////////////////////////////////////////////////////////////
    // 过滤
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public Filter getFilter() {
        if (mArrayFilter == null) {
            mArrayFilter = new ArrayFilter();
        }
        return mArrayFilter;
    }

    class ArrayFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (filterList == null) {
                filterList = new ArrayList<>(mList);
            }
            //如果没有过滤条件则不过滤
            if (constraint == null || constraint.length() == 0) {
                results.values = filterList;
                results.count = filterList.size();
            } else {
                List<Book> retList = new ArrayList<>();
                //过滤条件
                String str = constraint.toString().toLowerCase();
                //循环变量数据源，如果有属性满足过滤条件，则添加到result中
                for (Book book : filterList) {
                    if (book.getAuthor().contains(str) || book.getName().contains(str) || (book
                            .getId() + "").contains(str) || (book.getPrice() + "").contains(str)
                            || book.getPinyin().contains(str)) {
                        retList.add(book);
                    }
                }
                results.values = retList;
                results.count = retList.size();
            }
            return results;
        }

        //回过滤结果
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mList = (List<Book>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
