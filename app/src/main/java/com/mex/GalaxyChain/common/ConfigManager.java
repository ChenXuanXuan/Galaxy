package com.mex.GalaxyChain.common;


import android.content.Context;

import com.mex.GalaxyChain.bean.eventbean.VarietyHoldPosiBean;
import com.mex.GalaxyChain.data.sp.SharePStore;

/**
 * Created by lenote on 2015/9/11.
 */
public class ConfigManager {
	private static SharePStore sharePStore;
	private static final String KEY_ISLOGIN = "isLogin";
	private static final String KEY_USER_TOKEN = "key_user_token";
    private static final String KEY_U_DEVICE_ID = "key_udid";
	private static final String KEY_CONFIG = "key_config";
	private static final String KEY_ASSET = "key_asset";
	private static VarietyHoldPosiBean varietyEntity;
    private static final String KEY_USER_ID = "key_user_id";


	public static void init(Context context) {
		sharePStore = SharePStore.getStore(context, "config");
	}

	public static boolean getIsLogin() {
		return sharePStore.getBoolean(KEY_ISLOGIN, false);
	}

	public static void setIsLogin(boolean isHide) {
		sharePStore.save(KEY_ISLOGIN, isHide);
	}






  public static  void setUDeviceId(String udid){
	   sharePStore.save(KEY_U_DEVICE_ID,udid);
  }

    public static String getUDeviceId() {
        return sharePStore.getString(KEY_U_DEVICE_ID,"");
    }

	public static VarietyHoldPosiBean getVarietyHold() {
		if (varietyEntity == null) {
			return varietyEntity = sharePStore.getObject(KEY_ASSET, VarietyHoldPosiBean.class);
		}
		return varietyEntity;
	}

	public static void setVarietyHold(VarietyHoldPosiBean result) {
		varietyEntity = result;
		sharePStore.save(KEY_ASSET, result);
	}


  public static void setMoneyFlowCatheBean(){}



    public static void setUserToken(String userToken) { sharePStore.save(KEY_USER_TOKEN, userToken); }
    public static String getUserToken() { return sharePStore.getString(KEY_USER_TOKEN, " "); }
    public static void setUserId(int userId){
	    sharePStore.save(KEY_USER_ID,userId);
   }
   public static int getUserId(){
	    return sharePStore.getInt(KEY_USER_ID,0);
   }
    public static boolean isLogin() {
        return sharePStore.getInt(KEY_USER_ID, 0) > 0;
    }

   public static void logOut() {
        sharePStore.remove(KEY_USER_ID);
        sharePStore.remove(KEY_USER_TOKEN);
    }



}
