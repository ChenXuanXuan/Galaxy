package com.mex.GalaxyChain.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.MyApplication;
import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.net.bean.BaseBean;
import com.mex.GalaxyChain.ui.common.AppManager;

/**
 * name：
 * describe:版本更新
 * author: LSJ
 * time 24/4/18 上午11:18
 */
public class VersionDialog extends Dialog {

	private static final String TAG_EXIT = "exit";
	private ImageView btCancel;
	private TextView btConfirm;
	private BaseBean mVersion;
	private TextView tvVersion;
	private ImageView titleDes;
	private Context mContext;
	private boolean isMustUpdate;
	private TextView versionName;

	public VersionDialog(Context context, int theme, BaseBean version, boolean isMustUpdate) {
		super(context, theme);
		this.mVersion = version;
		this.mContext = context;
		this.isMustUpdate = isMustUpdate;
	}

	public VersionDialog(Context context, BaseBean version) {
		super(context);
		this.mVersion = version;
		this.mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_dialog_version);
		findViewByIds();
		initUI();
		setListener();
	}

	private void findViewByIds() {
		versionName = (TextView) findViewById(R.id.versionName);
		titleDes = (ImageView) findViewById(R.id.titleDes);
		btCancel = (ImageView) findViewById(R.id.bt_version_cancle);
		btConfirm = (TextView) findViewById(R.id.bt_version_confirm);
		tvVersion = (TextView) findViewById(R.id.tv_version_name);
	}

	private void initUI() {
		if (isMustUpdate) {
			setCanceledOnTouchOutside(false);

		} else {
			setCanceledOnTouchOutside(false);
		}
		tvVersion.setText(mVersion.getDesc());
		String laun = MyApplication.getInstance().getString(R.string.lang);
		if ("en".equals(laun))
			titleDes.setImageResource(R.drawable.tit_en);
		else
			titleDes.setImageResource(R.drawable.tit_cn);
		versionName.setText(mVersion.getVersion());
	}

	private void setListener() {
		btCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				VersionDialog.this.dismiss();
				if (isMustUpdate) {
					outApp();
				}

			}
		});
		btConfirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				VersionDialog.this.dismiss();
				VersionUpdateDialog dialog = new VersionUpdateDialog(mContext, R.style.dialog,
						mVersion.getUrl(), mVersion.getDesc());
				dialog.show();
			}
		});
	}

	/**
	 * 强制升级，退出程序
	 */
	private void outApp() {
		AppManager.AppExit(mContext);
	}

	@Override
	public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
		if (isMustUpdate)
			return true;
		else
			return super.onKeyDown(keyCode, event);
	}
}
