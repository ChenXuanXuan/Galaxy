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
    private static final String KEY_STATUS_AUTH_C1="key_status_auth_c1";
    private static final String KEY_PHONENUMBER="key_phonenumber";


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


    public static void setPhoneNum(long phoneNumber) { sharePStore.save(KEY_PHONENUMBER,phoneNumber);}
    public static long getPhoneNum(){return sharePStore.getLong(KEY_PHONENUMBER,0);}
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
    public static void setStatus_auth_c1(int status) {//status=1 C1实名认证通过
        sharePStore.save(KEY_STATUS_AUTH_C1,status);
    }
    public static  int getStatus_auth_c1(){
	    return  sharePStore.getInt(KEY_STATUS_AUTH_C1,0);
    }

 //判断 是否C1 认证成功通过
    public static boolean  isRealnameAuthC1Success(){
	    return  getStatus_auth_c1()==1&&getStatus_auth_c1()!=0&&getStatus_auth_c1()>0;

    }









   public static void logOut() {
	   sharePStore.save(KEY_USER_ID,0);
	   sharePStore.save(KEY_USER_TOKEN,"");
        sharePStore.remove(KEY_USER_ID);
        sharePStore.remove(KEY_USER_TOKEN);
       sharePStore.remove(KEY_STATUS_AUTH_C1);
       setStatus_auth_c1(0);//sharePStore.save(KEY_STATUS_AUTH_C1,0);
       sharePStore.remove(KEY_PHONENUMBER);
       setPhoneNum(0);
    }





}
