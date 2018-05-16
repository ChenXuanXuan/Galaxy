package com.mex.GalaxyChain.mychart.chart.linechat.charting.data.realm.implementation;


import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.LineData;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.realm.base.RealmUtils;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.datasets.ILineDataSet;

import java.util.List;

import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Philipp Jahoda on 19/12/15.
 */
public class RealmLineData extends LineData {

    public RealmLineData(RealmResults<? extends RealmObject> result, String xValuesField, List<ILineDataSet> dataSets) {
        super(RealmUtils.toXVals(result, xValuesField), dataSets);
    }
}
