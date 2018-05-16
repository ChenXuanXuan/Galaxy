package com.mex.GalaxyChain.ui.market;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mex.GalaxyChain.R;
import com.mex.GalaxyChain.UIHelper;
import com.mex.GalaxyChain.bean.QuitEvent;
import com.mex.GalaxyChain.bean.SymbolBean;
import com.mex.GalaxyChain.common.ArrayListAdapter;
import com.mex.GalaxyChain.common.BaseFragment;
import com.mex.GalaxyChain.ui.exchange.ITitleCreator;
import com.mex.GalaxyChain.ui.market.entity.MarketSession;
import com.mex.GalaxyChain.view.MarketPairItemView;
import com.mex.GalaxyChain.view.MarketPairItemView_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by LSJ on 18/3/10.
 */
@EFragment(R.layout.fragment_list)
public class MarketListChildFragment extends BaseFragment implements ITitleCreator {
	@ViewById
	ListView listView;
	@FragmentArg
	String type;
	private MyAdapter adapter;
	//private List<MarketSession> dataList = new ArrayList<>();
	private String pageTitle;
    private SymbolBean symbolBean;


    public void transmitData(SymbolBean symbolBean){
	    this.symbolBean=symbolBean;

    }

	@Override
	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	@AfterViews
	void initView() {
		adapter = new MyAdapter(getActivity());
		listView.setAdapter(adapter);
		//adapter.setList(dataList);
     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             UIHelper.toMarkMainAct_kLine(getActivity());
         }
     });
	}






   // public void refresh(List<MarketSession> pairList) {
	//	dataList.clear();
	//	dataList.addAll(pairList);
	//}
//

	private static class MyAdapter extends ArrayListAdapter<MarketSession> {
		MyAdapter(Context context) {
			super(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			MarketPairItemView view;
			if (convertView == null) {
				view = MarketPairItemView_.build(parent.getContext());
			} else {
				view = (MarketPairItemView) convertView;
			}
			MarketSession item = getItem(position);
			view.setTag(item);
			view.bindView(item);
			return view;
		}
	}




    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(QuitEvent event) {

    }



}
