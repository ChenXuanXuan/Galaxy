package com.mex.GalaxyChain.ui.asset.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.mychart.ChartConstant;
import com.mex.GalaxyChain.mychart.ChartView;
import com.mex.GalaxyChain.utils.AppUtil;

import rx.subscriptions.CompositeSubscription;


/**
 * Created by Arvin on 2016/10/26.
 * 分时frag和k线frag的父布局
 */
public abstract class LineBaseFragment extends Fragment implements ChartConstant, TabLayout.OnTabSelectedListener, ChartView.OnDoubleTapListener, View.OnClickListener {
    protected String cid;
    protected boolean isShow;
    //K线类型：取值为ChartConstant的TYPE_RIK等,日k月k周k等,默认为0分时图
    protected int type;

    protected TabLayout indexTab;//分时线下指标

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        indexTab = (TabLayout) getView().findViewById(R.id.cfb_index_tab);
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isShow = isVisibleToUser;
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onDoubleTap() {
    }

    private Dialog dialog;
    protected ListView mListView;
    protected CompositeSubscription mCompositeSubscription;
  //  @Inject
  //  public ClientApi clientApi;
  //  @Inject
  //  public SharedPreferences sharedPreferences;
    private ImageView ivUnread;
    private LineBaseFragment.IMsgReceiver receiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCompositeSubscription = new CompositeSubscription();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
        super.onDestroyView();
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

    public void mDismissDialog() {
        if (dialog != null && dialog.isShowing() && !getActivity().isFinishing()) {
            dialog.dismiss();
            AppUtil.createLoadingDialog(getActivity(), "", false);
            dialog.setCanceledOnTouchOutside(false);
        }
    }

    protected void initData() {

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

    class IMsgReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (ivUnread != null)
                ivUnread.setVisibility(View.VISIBLE);
        }
    }
}
