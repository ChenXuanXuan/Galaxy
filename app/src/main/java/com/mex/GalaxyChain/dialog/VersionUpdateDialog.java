package com.mex.GalaxyChain.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.DownloadUtil;
import com.mex.GalaxyChain.utils.VersionUtil;
import com.mex.GalaxyChain.view.FlikerProgressBar;

import java.io.File;

/**
 * name：
 * describe:应用更新下载
 * author: LSJ
 * time 24/4/18 上午11:19
 */
public class VersionUpdateDialog extends Dialog implements View.OnClickListener {

	private Context context;
	private FlikerProgressBar flikerbar;
	private String url;
	private String version;

	public VersionUpdateDialog(Context context, int theme, String url, String version) {
		super(context, theme);
		this.context = context;
		this.url = url;
		this.version = version;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(AppUtil.getScreenPixel(context).widthPixels,
				AppUtil.getScreenPixel(context).heightPixels);
		View v = View.inflate(context, R.layout.layout_dialog_version_update, null);
		setContentView(v, p);
		findViewByIds();
		initUI();
	}

	private void findViewByIds() {
		flikerbar = (FlikerProgressBar) findViewById(R.id.round_flikerbar);
	}

	private void initUI() {
		VersionUtil.getInstance(getContext(),
				getContext().getResources().getString(R.string.app_name) + version,
				R.drawable.ic_launcher).download(url, new DownloadUtil.OnDownloadListener() {
			@Override
			public void onStart() {

			}

			@Override
			public void onProgress(int progress) {
				flikerbar.setProgress(progress);
			}

			@Override
			public void onFinish(File saveFile) {
				flikerbar.finishLoad();
				dismiss();
			}

			@Override
			public void onFailure() {

			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
		return true;
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			default:
				break;
		}
	}
}

