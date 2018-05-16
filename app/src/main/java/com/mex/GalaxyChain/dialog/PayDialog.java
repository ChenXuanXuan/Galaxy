package com.mex.GalaxyChain.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.view.GridPasswordView;


public class PayDialog {
	public static String passWord;
	public static DialogInterface.OnDismissListener listener;
	private static GridPasswordView gv_normai;

	public static void show(final Activity activity, final PaySuccessListener paySuccessListener) {
		final Dialog dialog = new Dialog(activity, R.style.transparentFrameWindowStyle);
		View view = View.inflate(activity, R.layout.pay_pwd, null);
		final TextView confirm = (TextView) view.findViewById(R.id.confirm);
		ImageView cancle = (ImageView) view.findViewById(R.id.cancle);
		cancle.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog.dismiss();
			}
		});

		confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!TextUtils.isEmpty(passWord)){
					dialog.dismiss();
					if (paySuccessListener != null)
						paySuccessListener.paySuccessedListener(passWord);
				}
			}
		});
		gv_normai = (GridPasswordView) view.findViewById(R.id.gv_normai);
		gv_normai.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
			@Override
			public void onTextChanged(final String psw) {
				passWord = psw;
				if (psw.length() == 6) {
					confirm.setEnabled(true);
					InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
					inputmanger.hideSoftInputFromWindow(gv_normai.getWindowToken(), 0);
					//输入后弹框不消失
					//dialog.dismiss();
				}else {
					confirm.setEnabled(false);
				}
			}

			@Override
			public void onInputFinish(String psw) {

			}

		});
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
		int screenWidth = activity.getWindowManager().getDefaultDisplay().getWidth();
		wmlp.width = screenWidth - 80;
		wmlp.gravity = Gravity.CENTER;
		dialog.onWindowAttributesChanged(wmlp);
		if (!activity.isFinishing())
			dialog.show();
		if (listener != null)
			dialog.setOnDismissListener(listener);
	}


	public interface PaySuccessListener {
		/**
		 * @param pwd
		 */
		void paySuccessedListener(String pwd);
	}

}
