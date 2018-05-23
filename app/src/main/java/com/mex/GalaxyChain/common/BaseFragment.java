package com.mex.GalaxyChain.common;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mex.GalaxyChain.utils.AppUtil;
import com.mex.GalaxyChain.utils.LNLog;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by lenote on 2015/9/6.
 */
public abstract class BaseFragment extends Fragment {
	private static final String TAG = "BaseFragment";
	private Dialog dialog;

	protected void showToast(String errMsg) {
		if (!isAdd()) {
			LNLog.e(TAG, "is not add");
			return;
		}
		Toast.makeText(getActivity(), errMsg, Toast.LENGTH_SHORT).show();
	}

	public boolean isAdd() {
		return getActivity() != null && !isDetached() && isAdded();
	}

	protected void showToast(@StringRes int strRes) {
		if (!isAdd()) {
			LNLog.e(TAG, "is not add");
			return;
		}
		Toast.makeText(getActivity(), strRes, Toast.LENGTH_SHORT).show();
	}

	protected void showLoading(String msg) {
		if (getActivity() != null && isAdd()) {
//			mLoading = ProgressDialog.show(getActivity(), msg, null, true, true);
			mShowDialog(msg);
		}
	}

	public void mShowDialog(String msg) {
		if (dialog == null) {
			dialog = AppUtil.createLoadingDialog(getActivity(), true);
			dialog.setCanceledOnTouchOutside(false);
		}
		if (dialog != null && !getActivity().isFinishing()) {
			dialog.show();
			dialog.setCanceledOnTouchOutside(false);
		}
	}

	public void mShowDialog() {
		if (dialog == null) {
			dialog = AppUtil.createLoadingDialog(getActivity(), true);
			dialog.setCanceledOnTouchOutside(false);
		}
		if (dialog != null && !getActivity().isFinishing()) {
			dialog.show();
			dialog.setCanceledOnTouchOutside(false);
		}
	}

	protected void dismissLoading() {
		mDismissDialog();
	}

	public void mDismissDialog() {
		if (dialog != null && dialog.isShowing() && !getActivity().isFinishing()) {
			dialog.dismiss();
			AppUtil.createLoadingDialog(getActivity(), "", false);
			dialog.setCanceledOnTouchOutside(false);
		}
	}

	/**
	 * 权限拒绝
	 *
	 * @param requestCode
	 */
	public void denied(int requestCode) {
	}

	/**
	 * 权限同意
	 *
	 * @param requestCode
	 */
	public void grant(int requestCode) {
	}


	@Override
	public void onHiddenChanged(boolean hidden) {
		super.onHiddenChanged(hidden);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
	//	if (!EventBus.getDefault().isRegistered(getActivity()))
	//		EventBus.getDefault().register(getActivity());
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onDestroy() {
		if (EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().unregister(this);
        }
		super.onDestroy();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}



    protected View inflate(int resourceId) {
        View inflate = LayoutInflater.from(getActivity()).inflate(resourceId, null);
        return inflate;
    }


    public void onSelect() {
    }
    public void onLeave() {
    }

	public void setRefresh() {
	}

	public void finishRefresh() {
	}
}

