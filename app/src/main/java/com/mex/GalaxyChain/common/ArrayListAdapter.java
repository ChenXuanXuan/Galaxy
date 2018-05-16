package com.mex.GalaxyChain.common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenote on 2015/9/7.
 */
public abstract class ArrayListAdapter<T> extends BaseAdapter {

	protected List<T> mList;
	protected Context mContext;


	public ArrayListAdapter(Context context) {
		this.mContext = context;
	}


	public int getCount() {
		if (mList != null)
			return mList.size();
		else
			return 0;
	}

	public T getItem(int position) {
		return mList == null ? null : mList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	abstract public View getView(int position, View convertView,
								 ViewGroup parent);

	/**
	 * 设置 adapter List 数据Listview 会自动更新数据
	 *
	 * @param list
	 */
	public void setList(List<T> list) {
		setList(list, true);
	}

	public void setList(List<T> list, boolean notify) {
		this.mList = list;
		if (notify) {
			notifyDataSetChanged();
		}
	}

	public List<T> getList() {
		return mList;
	}

	/**
	 * 设置 adapter List 数据Listview 会自动更新数据
	 *
	 * @param list
	 */
	public void setList(T[] list) {
		ArrayList<T> arrayList = new ArrayList<T>(list.length);
		for (T t : list) {
			arrayList.add(t);
		}
		setList(arrayList);
	}

	/**
	 * 添加一个数据
	 *
	 * @param object
	 */
	public void addItem(T object) {
		if (mList == null) {
			mList = new ArrayList<>();
		}
		mList.add(object);
		notifyDataSetChanged();
	}

	/**
	 * 添加一个数据
	 *
	 * @param object
	 */
	public void addItem(int location, T object) {
		if (mList == null) {
			mList = new ArrayList<>();
		}
		mList.add(location, object);
		notifyDataSetChanged();
	}

	/**
	 * 添加一组数据
	 *
	 * @param objects
	 */
	public void addItems(List<T> objects) {
		if (mList == null) {
			mList = new ArrayList<>();
		}
		mList.addAll(objects);
		notifyDataSetChanged();
	}

	/**
	 * 添加一组数据
	 *
	 * @param objects
	 */
	public void addItems(int position, List<T> objects) {
		if (mList == null) {
			mList = new ArrayList<>();
		}
		mList.addAll(position, objects);
		notifyDataSetChanged();
	}

	public void remove(T item) {
		if (mList == null) {
			return;
		}
		mList.remove(item);
		notifyDataSetChanged();
	}
}