package com.mex.GalaxyChain.net.repo;

import com.mex.GalaxyChain.net.BaseRepo;
import com.mex.GalaxyChain.net.HttpFunc;
import com.mex.GalaxyChain.net.ReponseData;
import com.mex.GalaxyChain.net.UICompose;
import com.mex.GalaxyChain.net.service.IMarketService;
import com.mex.GalaxyChain.ui.market.entity.MarketSession;

import rx.Observable;

/**
 * Created by LSJ on 18/3/6.
 */

public class MarketRepo extends BaseRepo<IMarketService> {
	private static MarketRepo instance;

	public static MarketRepo getInstance() {
		if (instance == null) {
			instance = new MarketRepo();
		}
		return instance;
	}

	private MarketRepo() {
	}



}
