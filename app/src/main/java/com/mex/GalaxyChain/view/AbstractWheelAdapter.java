package com.mex.GalaxyChain.view;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * name：
 * describe:
 * author: LSJ
 * time 26/4/18 上午12:25
 */
public abstract class AbstractWheelAdapter implements WheelViewAdapter {
	// Observers
	private List<DataSetObserver> datasetObservers;

	@Override
	public View getEmptyItem(View convertView, ViewGroup parent) {
		return null;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		if (datasetObservers == null) {
			datasetObservers = new LinkedList<DataSetObserver>();
		}
		datasetObservers.add(observer);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		if (datasetObservers != null) {
			datasetObservers.remove(observer);
		}
	}

	/**
	 * Notifies observers about data changing
	 */
	protected void notifyDataChangedEvent() {
		if (datasetObservers != null) {
			for (DataSetObserver observer : datasetObservers) {
				observer.onChanged();
			}
		}
	}

	/**
	 * Notifies observers about invalidating data
	 */
	protected void notifyDataInvalidatedEvent() {
		if (datasetObservers != null) {
			for (DataSetObserver observer : datasetObservers) {
				observer.onInvalidated();
			}
		}
	}
}
