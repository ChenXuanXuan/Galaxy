package com.mex.GalaxyChain.mychart.chart.linechat.charting.data.realm.implementation;


import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.RadarData;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.data.realm.base.RealmUtils;
import com.mex.GalaxyChain.mychart.chart.linechat.charting.interfaces.datasets.IRadarDataSet;

import java.util.List;

import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Philipp Jahoda on 19/12/15.
 */
public class RealmRadarData extends RadarData {

    public RealmRadarData(RealmResults<? extends RealmObject> result, String xValuesField, List<IRadarDataSet> dataSets) {
        super(RealmUtils.toXVals(result, xValuesField), dataSets);
    }
}
