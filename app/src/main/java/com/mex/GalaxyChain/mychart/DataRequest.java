package com.mex.GalaxyChain.mychart;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mex.GalaxyChain.mychart.chart.kchart.DataHelper;
import com.mex.GalaxyChain.mychart.chart.kchart.chart.KLineEntity;

import org.apache.http.util.EncodingUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * 模拟网络请求
 * Created by LSJ on 2017/5/9.
 */

public class DataRequest {
    private static List<KLineEntity> datas = null;
    private static Random random = new Random();

    public static String getStringFromAssert(Context context, String fileName) {
        try {
            InputStream in = context.getResources().getAssets().open(fileName);
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            return EncodingUtils.getString(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static List<KLineEntity> getALL(Context context) {
        if (datas == null) {
            final List<KLineEntity> data =
                    new Gson().fromJson(getStringFromAssert(context, "ibm.json")
                            , new TypeToken<List<KLineEntity>>() {
                            }.getType());
            DataHelper.calculate(data);
            datas = data;
        }
        return datas;

    }

    /**
     * 分页查询
     *
     * @param context
     * @param offset  开始的索引
     * @param size    每次查询的条数
     */
    public static List<KLineEntity> getData(Context context, int offset, int size) {
        List<KLineEntity> all = getALL(context);
        List<KLineEntity> data = new ArrayList<>();
        if (all != null) {
            int start = Math.max(0, all.size() - 1 - offset - size);
            int stop = Math.min(all.size(), all.size() - offset);
            for (int i = start; i < stop; i++) {
                data.add(all.get(i));
            }
        }
        return data;
    }

}


