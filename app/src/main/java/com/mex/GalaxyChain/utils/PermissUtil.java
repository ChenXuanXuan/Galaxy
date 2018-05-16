package com.mex.GalaxyChain.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.mex.GalaxyChain.common.BaseActivity;
import com.mex.GalaxyChain.common.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * name：
 * describe:
 * author: LSJ
 * time 29/3/18 下午8:56
 */
public class PermissUtil {
    private static final int WRITE_PERMISSION_REQ_CODE = 100;


    /**
     * 权限请求
     *
     * @param fragment
     * @param requestCode
     * @param permissions
     */
    public static void needPermission(Fragment fragment, int requestCode, String[] permissions) {
        requestPermissions(fragment, requestCode, permissions);
    }

    /**
     * 权限请求
     *
     * @param act
     * @param requestCode
     * @param permissions
     */
    public static void needPermission(Activity act, int requestCode, String[] permissions) {
        requestPermissions(act, requestCode, permissions);
    }

    /**
     * 检查并申请权限
     *
     * @param object
     * @param requestCode
     * @param permissions
     */
    @TargetApi(value = Build.VERSION_CODES.M)
    private static void requestPermissions(Object object, int requestCode, String[] permissions) {
        if (!isOverMarshmallow()) {
            doExecuteSuccess(object, requestCode);
            return;
        }
        List<String> deniedPermissions = findDeniedPermissions(getActivity(object), permissions);

        if (deniedPermissions.size() > 0) {
            if (object instanceof Activity) {
                ((Activity) object).requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            } else if (object instanceof Fragment) {
                ((Fragment) object).requestPermissions(deniedPermissions.toArray(new String[deniedPermissions.size()]), requestCode);
            } else {
                throw new IllegalArgumentException(object.getClass().getName() + " is not supported");
            }

        } else {
            doExecuteSuccess(object, requestCode);
        }
    }

    /**
     * 已经有此权限或者授权成功
     *
     * @param object
     * @param requestCode
     */
    private static void doExecuteSuccess(Object object, int requestCode) {
        if (object instanceof BaseActivity) {
            ((BaseActivity) object).grant(requestCode);
        } else if (object instanceof BaseFragment) {
            ((BaseFragment) object).grant(requestCode);
        }
    }


    /**
     * 查找所需权限组是否有拒绝的权限
     *
     * @param activity
     * @param permission
     * @return
     */
    @TargetApi(value = Build.VERSION_CODES.M)
    public static List<String> findDeniedPermissions(Activity activity, String... permission) {
        List<String> denyPermissions = new ArrayList<>();
        for (String value : permission) {
            if (ContextCompat.checkSelfPermission(activity, value) != PackageManager.PERMISSION_GRANTED) {
                denyPermissions.add(value);
            }
        }
        return denyPermissions;
    }

    /**
     * 判断sdk版本
     *
     * @return
     */
    public static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static Activity getActivity(Object object) {
        if (object instanceof Fragment) {
            return ((Fragment) object).getActivity();
        } else if (object instanceof Activity) {
            return (Activity) object;
        }
        return null;
    }

    /**
     * 处理用户处理权限结果
     *
     * @param obj
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public static void doPerResult(Object obj, int requestCode, String[] permissions,
                                   int[] grantResults, Activity act, Fragment fg) {
        List<String> deniedPermissions = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permissions[i]);
            }
        }

        if (!ActivityCompat.shouldShowRequestPermissionRationale
                (act, permissions[0])) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", act.getPackageName(), null);
            intent.setData(uri);
            act.startActivityForResult(intent, 500);
        }


        if (deniedPermissions.size() > 0) {
            doExecuteFail(obj, requestCode);
        } else {
            doExecuteSuccess(obj, requestCode);
        }
    }


    public static void doPerResult(Object obj, int requestCode, String[] permissions,
                                   int[] grantResults) {
        List<String> deniedPermissions = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                deniedPermissions.add(permissions[i]);
            }
        }

        if (deniedPermissions.size() > 0) {
            doExecuteFail(obj, requestCode);
        } else {
            doExecuteSuccess(obj, requestCode);
        }
    }

    /**
     * 用户拒绝权限
     *
     * @param object
     * @param requestCode
     */
    private static void doExecuteFail(Object object, int requestCode) {

        if (object instanceof BaseActivity) {
            ((BaseActivity) object).denied(requestCode);
        } else if (object instanceof BaseFragment) {
            ((BaseFragment) object).denied(requestCode);
        }
    }
}
