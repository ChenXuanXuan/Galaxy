package com.mex.GalaxyChain.ui.asset.fragment;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.common.BaseFragment;
import com.mex.GalaxyChain.ui.activity.BrowserActivity_;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;


/**
 * Created by LSJ on 18/3/4.
 */
@EFragment(R.layout.fragment_asset)
public class AssetFragment extends BaseFragment {
    @AfterViews
    void init(){
        BrowserActivity_.launch(getActivity(), "http://www.iqiyi.com/w_19rvw2t8wh.html");
    }
}
