package com.mex.GalaxyChain.common.control;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.mex.GalaxyChain.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LSJ
 * @Description:BaseAbsListAdapter是所有AbsListView的适配器的基类
 * @date 18/3/16
 * @time 11:55
 */
public abstract class BaseAbsListAdapter<T, VH extends BaseViewHolder<T>> extends BaseAdapter {
    protected Context context;
//    protected Activity activity;

    protected ArrayList<T> itemDatas;

    private VH viewHolder;

    public BaseAbsListAdapter(Context context) {
        this.context = context;
        itemDatas = new ArrayList<>();
    }

    public VH getViewHolder() {
        return viewHolder;
    }

    @Override
    public int getCount() {
        return itemDatas.size();
//        return 1;
    }

    @Override
    public T getItem(int position) {
        if (position < getCount() && position < itemDatas.size()) {
            return itemDatas.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            viewHolder = onCreateViewHolder(getItemViewType(position));
            convertView = viewHolder.getConvertView();
            convertView.setTag(R.string.app_name, viewHolder);
        } else {
            viewHolder = (VH) convertView.getTag(R.string.app_name);
        }

        viewHolder.loadDataToView(position, getItem(position));

        return convertView;
    }

    public abstract VH onCreateViewHolder(int viewType);

    /**
     * 判断数据是否为空
     */
    public boolean isEmpty() {
        return itemDatas.isEmpty();
    }

    /**
     * 添加数据
     */
    public void addItems(List<T> list) {
        if (list != null && list.size() > 0) {
            itemDatas.addAll(list);
            notifyDataSetChanged();
        }
    }

    /**
     * 替换数据
     *
     * @param originItem
     * @param newItem
     */
    public void replaceItem(T originItem, T newItem) {
        if (itemDatas.contains(originItem)) {
            int position = itemDatas.indexOf(originItem);
            itemDatas.remove(position);
            itemDatas.add(position, newItem);
            notifyDataSetChanged();
        }
    }


    /**
     * 添加数据
     */
    public void addItems(int position, List<T> list) {
        if (list != null) {
            itemDatas.addAll(position, list);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加单个数据
     */
    public void addItem(T item) {
        if (item != null) {
            itemDatas.add(item);
            notifyDataSetChanged();
        }
    }

    public void addItemNotNotify(T item) {
        if (item != null) {
            itemDatas.add(item);
        }
    }

    public void addItemNotNotifys(List<T> list) {
        if (list != null) {
            itemDatas.addAll(list);
        }
    }

    /**
     * 添加单个数据
     */
    public void addItem(int position, T item) {
        if (item != null && position >= 0) {
            itemDatas.add(position, item);
            notifyDataSetChanged();
        }
    }

    /**
     * 删除数据
     */
    public void removeItem(int position) {
        if (position >= 0 && position < getCount()) {

            itemDatas.remove(position);
            notifyDataSetChanged();
        }
    }

    /**
     * 删除数据
     */
    public void removeItem(T data) {
        if (data != null && itemDatas.contains(data)) {

            itemDatas.remove(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 设置新数据，原来的清空
     */
    public void setItems(List<T> list) {
        if (list != null) {
            itemDatas.clear();
            itemDatas.addAll(list);
            notifyDataSetChanged();
        }

    }

    /**
     * 清空
     */
    public void clearItems() {
        if (!isEmpty()) {
            itemDatas.clear();
            notifyDataSetChanged();
        }
    }

    public ArrayList<T> getDatas() {
        return itemDatas;
    }


}
