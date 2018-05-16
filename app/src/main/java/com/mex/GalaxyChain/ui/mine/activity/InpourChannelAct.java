package com.mex.GalaxyChain.ui.mine.activity;

import android.view.View;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.dialog.PayDialog;
import com.mex.GalaxyChain.utils.ToastUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * name：
 * describe:
 * author: LSJ
 * time 23/4/18 下午11:57
 */
@EActivity(R.layout.layout_inpour_channel)
public class InpourChannelAct extends BaseActivity {

	@ViewById
	TextView mTitle;

	@AfterViews
	void init() {
		mTitle.setText("充值");
	}

	@Click({R.id.next})
	void onClick(View view) {
		switch (view.getId()) {
			case R.id.next:
				PayDialog.show(this, new PayDialog.PaySuccessListener() {
					@Override
					public void paySuccessedListener(String pwd) {
						//拿到密码进行网络请求
						ToastUtils.showCorrectImage("网络请求");
						InpourSuccess_.intent(InpourChannelAct.this).start();
					}
				});
				break;
		}
	}
}
