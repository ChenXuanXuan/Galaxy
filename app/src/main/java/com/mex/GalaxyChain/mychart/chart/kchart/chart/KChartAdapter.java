package com.mex.GalaxyChain.mychart.chart.kchart.chart;



import com.lsj.kchart.kchartlib.chart.BaseKChartAdapter;
import com.mex.GalaxyChain.utils.LogUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据适配器
 * Created by LSJ on 2017/5/9.
 */


public class KChartAdapter extends BaseKChartAdapter {

    private List<KLineEntity> datas = new ArrayList<>();

    public KChartAdapter() {

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public Date getDate(int position) {
        try {
            String s = datas.get(position).Date;
            String[] split = s.split("/");
            Date date = new Date();
            date.setYear(Integer.parseInt(split[0]) - 1900);
            date.setMonth(Integer.parseInt(split[1]) - 1);
            date.setDate(Integer.parseInt(split[2]));
            LogUtils.d("TAG",date.toString());
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getStringDate(int position) {
        try {
            String s = datas.get(position).Date;
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 向头部添加数据
     */
    public void addHeaderData(List<KLineEntity> data) {
        if (data != null && !data.isEmpty()) {
            datas.addAll(data);
            notifyDataSetChanged();
        }
    }

    /**
     * 向尾部添加数据
     */
    public void addFooterData(List<KLineEntity> data) {
        if (data != null && !data.isEmpty()) {
            datas.addAll(0, data);
            notifyDataSetChanged();
        }
    }

    /**
     * 改变某个点的值
     * @param position 索引值
     */
    public void changeItem(int position,KLineEntity data)
    {
        datas.set(position,data);
        notifyDataSetChanged();
    }


    /**
     * 添加单个数据
     */
    public void addItem(int position, KLineEntity item) {
        if (item != null && position >= 0) {
            datas.add(position, item);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加单个数据
     */
    public void addItem(KLineEntity item) {
        if (item != null) {
            datas.add(item);
            notifyDataSetChanged();
        }
    }


    /**
     * 设置新数据，原来的清空
     */
    public void setItems(List<KLineEntity> data) {
        if (data != null) {
            datas.clear();
            datas.addAll(data);
            notifyDataSetChanged();
        }

    }


    /**
     * 清空
     */
    public void clearItems() {
        if (!isEmpty()) {
            datas.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * 判断数据是否为空
     */
    public boolean isEmpty() {
        return datas.isEmpty();
    }


}
