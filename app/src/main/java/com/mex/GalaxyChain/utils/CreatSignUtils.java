package com.mex.GalaxyChain.utils;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CreatSignUtils {

    public static  String creatSign(Map<String, Object> params) {
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, Object> sortedParams = new TreeMap<String, Object>(params);
        Set<Map.Entry<String, Object>> entrys = sortedParams.entrySet();

        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, Object> param : entrys) {
            if (param.getKey().equals("sign")) {//去掉签名字段
                continue;
            }
            basestring.append(param.getKey());
            if (param.getValue() != null) {
                basestring.append(param.getValue().toString());
            }
        }
        basestring.append("jiaoyisuo@2017");
        LogUtils.d("basestring = " + basestring.toString());
        // 使用MD5对待签名串求签
        String curSign = MD5Util.MD5(basestring.toString());
        LogUtils.d("test basesign={}==============sign={}, serverSign={}" + basestring.toString() + curSign);
        return curSign;
    }


}
