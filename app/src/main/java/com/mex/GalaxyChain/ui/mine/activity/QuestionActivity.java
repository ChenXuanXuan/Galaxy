package com.mex.GalaxyChain.ui.mine.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * name：
 * describe:
 * author: LSJ
 * time 24/4/18 上午10:58
 */
@EActivity(R.layout.frag_mine_question)
public class QuestionActivity extends BaseActivity {
	@ViewById
	ImageView back;

	@ViewById
	TextView mTitle;

	@AfterViews
	void init() {
		initView();
	}

	private void initView() {
		mTitle.setText("常见问题");
		back.setVisibility(View.VISIBLE);
	}

	@Click({R.id.back})
	void onClick(View view) {
		switch (view.getId()) {
			case R.id.back:
				finish();
				break;
		}
	}
}
