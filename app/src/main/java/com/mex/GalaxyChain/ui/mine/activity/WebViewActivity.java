package com.mex.GalaxyChain.ui.mine.activity;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * name：
 * describe:
 * author: LSJ
 * time 24/4/18 下午1:35
 */
@EActivity(R.layout.activity_webview)
public class WebViewActivity extends BaseActivity {
	@ViewById(R.id.webview)
	WebView webView;
	@ViewById(R.id.title_view)
	TextView titleView;
	@Extra
	String url;
	@Extra
	String title;

	@AfterViews
	void init() {
		if (TextUtils.isEmpty(title)) {
			titleView.setText(title);
		}
		initWebView();
		loadData(url);
	}

	@Click(R.id.back)
	void onClick(View view) {
		switch (view.getId()) {
			case R.id.back:
				finish();
				break;
		}
	}

	@Override
	public void onBackPressed() {
		if (webView.canGoBack()) {
			webView.goBack();
		} else {
			super.onBackPressed();
		}
	}

	private void loadData(String webUrl) {
		if (TextUtils.isEmpty(webUrl)) {
			return;
		}
		if (!webUrl.startsWith("http://") && !webUrl.startsWith("https://")) {
			webUrl = "http://" + webUrl;
		}
		webView.loadUrl(webUrl);
	}

	private void initWebView() {
		webView.setVerticalScrollBarEnabled(false);
		webView.setHorizontalScrollBarEnabled(false);

		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setJavaScriptCanOpenWindowsAutomatically(true);
		settings.setDatabaseEnabled(true);
		settings.setAppCacheEnabled(true);
		settings.setCacheMode(WebSettings.LOAD_DEFAULT);
		settings.setPluginState(WebSettings.PluginState.ON);
		settings.setAllowFileAccess(true);
		settings.setLoadWithOverviewMode(false);
		settings.setDomStorageEnabled(true);
		settings.setUseWideViewPort(true);

		webView.setWebViewClient(new MyWebViewClient());
		webView.requestFocus();
		webView.requestFocusFromTouch();
		webView.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				return true;
			}
		});
		if (Build.VERSION.SDK_INT >= 19) {
			webView.getSettings().setLoadsImagesAutomatically(true);
		} else {
			webView.getSettings().setLoadsImagesAutomatically(false);
		}
	}


	private class MyWebViewClient extends WebViewClient {
		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			if (TextUtils.isEmpty(title)) {
				titleView.setText(view.getTitle());
			}
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			if (url.startsWith("intent://")) {
				return true;
			}
			return false;
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		if (webView != null) {
			webView.pauseTimers();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (webView != null) {
			webView.resumeTimers();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (webView != null) {
			webView.destroy();
			webView = null;
		}
	}
}

