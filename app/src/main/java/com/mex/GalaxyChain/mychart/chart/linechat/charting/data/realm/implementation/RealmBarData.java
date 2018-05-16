package com.mex.GalaxyChain.mychart.chart.linechat.charting.data.realm.implementation;


import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.BarData;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.realm.base.RealmUtils;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.datasets.IBarDataSet;

import java.util.List;

import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Philipp Jahoda on 19/12/15.
 */
public class RealmBarData extends BarData {

    public RealmBarData(RealmResults<? extends RealmObject> result, String xValuesField, List<IBarDataSet> dataSets) {
        super(RealmUtils.toXVals(result, xValuesField), dataSets);
    }
}
