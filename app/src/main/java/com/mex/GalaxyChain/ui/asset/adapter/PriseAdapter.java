package com.mex.GalaxyChain.ui.asset.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.control.BaseAbsListAdapter;
import com.mex.GalaxyChain.common.control.BaseViewHolder;
import com.mex.GalaxyChain.ui.asset.entity.NumEntity;

/**
 * name：
 * describe:
 * author: LSJ
 * time 26/4/18 下午9:23
 */
public class PriseAdapter extends BaseAbsListAdapter<NumEntity, PriseAdapter.PlatHolder> {




    public PriseAdapter(Context context ) {
		super(context);

	}

	@Override
	public PlatHolder onCreateViewHolder(int viewType) {
        View itemView  = View.inflate(context, R.layout.layout_view_handnum, null);
		return new PlatHolder(itemView,this);
	}

	class PlatHolder extends BaseViewHolder<NumEntity> {
		TextView handSum;

		public PlatHolder(View convertView, BaseAbsListAdapter absListAdapter) {
			super(convertView, absListAdapter);
			 handSum = (TextView) find(R.id.handSum);//文本 显示后台返回的几手
		}

		@Override
		public void loadDataToView(int position, NumEntity data) {
			super.loadDataToView(position, data);
			handSum.setText(data.getHandSum());
			if (data.isSelcet()){
				handSum.setBackgroundResource(R.drawable.bule_normal_bg);
				handSum.setTextColor(Color.parseColor("#ffffff"));
			}else {
				handSum.setBackgroundResource(R.drawable.bule_bg);
				handSum.setTextColor(Color.parseColor("#333333"));
			}
		}
	}
}
