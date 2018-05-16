package com.mex.GalaxyChain.utils;


import com.mex.GalaxyChain.MyApplication;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import java.util.List;

/**
 * Author: 夜天子丶
 * *
 * Date: 2016-05-10 12:02
 * *
 * QQ: 363246266
 * *
 * Version: V1.0
 */
public class RequestPermissionUtils {
    private static final String TAG = "RequestPermissionUtils";

    /**
     * @author Tzy°
     * created at 2016/5/10 11:53
     * desc: 批量申请权限
     */
    public static synchronized void requestPermission(final Runnable runnable, String... requestPermissions) {
        Acp.getInstance(MyApplication.appContext).request(new AcpOptions.Builder()
                        .setPermissions(requestPermissions)
//                .setDeniedMessage()
//                .setDeniedCloseBtn()
//                .setDeniedSettingBtn()
//                .setRationalMessage()
//                .setRationalBtn()
                        .build(),
                new AcpListener() {
                    @Override
                    public void onGranted() {
                        runnable.run();
                    }

                    @Override
                    public void onDenied(List<String> permissions) {
                        //ToastUtils.showShort(permissions.toString() + "权限拒绝");
                        ToastUtils.showErrorImage(permissions.toString() + "权限拒绝");
                    }
                });
    }


}
