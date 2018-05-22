package com.lsj.kchart.kchartlib.chart.entity;

/**
 * 蜡烛图实体接口
 * Created by LSJ on 2016/6/9.
 */

public interface CandleImpl {

    /**
     * 开盘价
     */
    float getOpenPrice();

    String getData();

    /**
     * 最高价
     */
    float getHighPrice();

    /**
     * 最低价
     */
    float getLowPrice();

    /**
     * 收盘价
     */
    float getClosePrice();

    /**
     * 五(月，日，时，分，5分等)均价
     */
    float getMA5Price();

    /**
     * 十(月，日，时，分，5分等)均价
     */
    float getMA10Price();

    /**
     * 二十(月，日，时，分，5分等)均价
     */
    float getMA20Price();
}
