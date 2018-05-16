package com.mex.GalaxyChain.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * name：
 * describe:
 * author: LSJ
 * time 28/4/18 下午2:09
 */
public class PropertiesUtils {

	private static Properties props;
	static{
		loadProps();
	}

	synchronized static private void loadProps(){
		props = new Properties();
		InputStream in = null;
		try {
			// 要加载的属性文件
			in = PropertiesUtils.class.getClassLoader().getResourceAsStream("conf/setting/vas.properties");
			props.load(in);
		} catch (FileNotFoundException e) {
			LogUtils.d("jdbc.properties文件未找到");
		} catch (IOException e) {
			LogUtils.d("出现IOException");
		} finally {
			try {
				if(null != in) {
					in.close();
				}
			} catch (IOException e) {
				LogUtils.d("vas.properties文件流关闭出现异常");
			}
		}
		LogUtils.d("加载properties文件内容完成...........");
		LogUtils.d("properties文件内容：" + props);
	}

	public static String getProperty(String key){
		if(null == props) {
			loadProps();
		}
		return props.getProperty(key);
	}

	public static String getProperty(String key, String defaultValue) {
		if(null == props) {
			loadProps();
		}
		return props.getProperty(key, defaultValue);
	}

}
