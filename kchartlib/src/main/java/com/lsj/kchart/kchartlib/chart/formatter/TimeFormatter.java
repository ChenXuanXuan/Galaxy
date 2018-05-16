package com.lsj.kchart.kchartlib.chart.formatter;

import com.lsj.kchart.kchartlib.chart.base.IDateTimeFormatter;
import com.lsj.kchart.kchartlib.utils.DateUtil;

import java.util.Date;

/**
 * 时间格式化器
 * Created by LSJ on 2016/6/21.
 */

public class TimeFormatter implements IDateTimeFormatter {
    @Override
    public String format(Date date) {
        if (date == null) {
            return "";
        }
        return DateUtil.shortTimeFormat.format(date);
    }
}
