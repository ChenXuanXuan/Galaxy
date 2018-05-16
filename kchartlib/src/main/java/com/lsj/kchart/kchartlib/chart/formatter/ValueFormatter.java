package com.lsj.kchart.kchartlib.chart.formatter;

import com.lsj.kchart.kchartlib.chart.base.IValueFormatter;

/**
 * Value格式化类
 * Created by LSJ on 2016/6/21.
 */

public class ValueFormatter implements IValueFormatter {
    @Override
    public String format(float value) {
        return String.format("%.2f", value);
    }
}
