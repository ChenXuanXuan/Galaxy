package com.mex.GalaxyChain.ui.common.afragmentTask;

import android.os.Bundle;

import com.mex.GalaxyChain.common.BaseFragment;


/**
 * name：
 * describe:
 * author: LSJ
 * time 22/3/18 下午11:14
 */
public abstract class BackHandledFragment extends BaseFragment {

	protected BackHandledInterface mBackHandledInterface;

	public abstract boolean onBackPressed();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(!(getActivity() instanceof BackHandledInterface)){
			throw new ClassCastException("Activity must implement BackHandledInterface");
		}else{
			this.mBackHandledInterface = (BackHandledInterface)getActivity();
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		mBackHandledInterface.setSelectedFragment(this);
	}
	
}
