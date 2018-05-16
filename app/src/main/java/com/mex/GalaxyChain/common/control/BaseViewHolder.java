package com.mex.GalaxyChain.common.control;


import android.content.Context;
import android.view.View;

/**
 * @author LSJ
 * @Description:BaseViewHolder
 * @date 18/3/16
 * @time 11:52
 */
public abstract class BaseViewHolder<T> {
    protected Context context;
    protected BaseAbsListAdapter absListAdapter;

    protected View convertView;

    protected int position;
    protected T data;

    public BaseViewHolder(View convertView, BaseAbsListAdapter absListAdapter) {
        this.convertView = convertView;
        context = convertView.getContext();
        this.absListAdapter = absListAdapter;

    }

    public View getConvertView() {
        return convertView;
    }

    /**
     * 将ItemData加载到ItemView上面，在实际的Viewholder中，需要重写此方法
     *
     * @param position
     * @param data
     * @return
     */
    public void loadDataToView(int position, T data) {
        this.position = position;
        this.data = data;
    }

    /**
     * 查找控件
     *
     * @param viewId
     * @return
     */
    public View find(int viewId) {
        return convertView.findViewById(viewId);

    }

    /**
     * itemview子控件的点击事件的监听器
     */
    public static interface OnInnerViewClickListener<T> {
        public void onClick(View view, T itemData, int postition);
    }
}
