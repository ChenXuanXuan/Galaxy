package com.mex.GalaxyChain.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.mex.GalaxyChain.common.ConfigManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * 获取设备、应用、系统等信息
 *
 * @author tanyq
 * @version 1.0
 */
public class DeviceUtil {

    private static final String TAG = "DeviceUtils";
    private static List<String> INVALID_IMEIs = new ArrayList<String>();

    static {
        INVALID_IMEIs.add("358673013795895");
        INVALID_IMEIs.add("004999010640000");
        INVALID_IMEIs.add("00000000000000");
        INVALID_IMEIs.add("000000000000000");
    }

    public static boolean isValidImei(String imei) {
        return !TextUtils.isEmpty(imei) &&
                imei.length() >= 10 &&
                !INVALID_IMEIs.contains(imei);
    }

    private static final String INVALID_ANDROIDID = "9774d56d682e549c";


    private static final String UDID_PATH = Environment.getExternalStorageDirectory().getPath() + "/data/.pushtalk_udid";


    public static String getWifiMacAddress(final Context context) {
        try {
            WifiManager wifimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            String mac = wifimanager.getConnectionInfo().getMacAddress();
            if (TextUtils.isEmpty(mac)) return null;
            return mac;
        } catch (Exception ignored) {
        }
        return null;
    }




    /**
     * 取得设备的唯一标识
     * <p/>
     * imei -> androidId -> mac address -> uuid saved in sdcard
     */
    public static String getUdid(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();

        try {
            int anInt = Integer.parseInt(imei);
            if (anInt != 0) {
                if (DeviceUtil.isValidImei(imei)) {
                    return imei;
                }
            }
        } catch (Exception e) {
            if (DeviceUtil.isValidImei(imei)) {
                return imei;
            }
        }


        String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (!TextUtils.isEmpty(androidId) && !INVALID_ANDROIDID.equals(androidId.toLowerCase(Locale.US))) {
            return androidId;
        }

        String macAddress = DeviceUtil.getWifiMacAddress(context);
        if (!TextUtils.isEmpty(macAddress)) {
            return Strings.toMD5(macAddress
                    + Build.MODEL + Build.MANUFACTURER
                    + Build.ID + Build.DEVICE);
        }

        return getSavedUuid();
    }


    private static String getSavedUuid() {
        String udid = ConfigManager.getUDeviceId();
        if (null != udid) return udid;

        File file = new File(UDID_PATH);
        if (file.exists()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String line = reader.readLine();
                if (!Strings.isNullOrEmpty(line)) {
                    udid = line;
                    ConfigManager.setUDeviceId(udid);
                    return udid;
                }
            } catch (IOException e) {
                LNLog.e(TAG,e.getMessage());
            } finally {
                BaseIO.silentClose(reader);
            }
        }

        String name = System.currentTimeMillis() + "";
        udid = UUID.nameUUIDFromBytes(name.getBytes()).toString();
        udid = Strings.toMD5(udid);

        ConfigManager.setUDeviceId(udid);

        try {
            file.createNewFile();
        } catch (IOException e) {
            return udid;
        }

         FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(udid.getBytes());
            fos.flush();
        } catch (IOException ignored) {
        } finally {
            try {
                if (null != fos) {
                    fos.close();
                }
            } catch (IOException ignored) {
            }
        }
        return udid;
    }





    public static String getCPUSerial() {
        String str = "", strCPU = "", cpuAddress = "0000000000000000";
        try {
            //读取CPU信息
            Process pp = Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            //查找CPU序列号
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    //查找到序列号所在行
                    if (str.indexOf("Serial") > -1) {
                        //提取序列号
                        strCPU = str.substring(str.indexOf(":") + 1,
                                str.length());
                        //去空格
                        cpuAddress = strCPU.trim();
                        break;
                    }
                } else {
                    //文件结尾
                    break;
                }
            }
        } catch (IOException ex) {
            //赋予默认值
            ex.printStackTrace();
        }
        return cpuAddress;
    }

    public static void openSoftKeyword(Context context, View mEditText) {
        InputMethodManager mInputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.showSoftInput(mEditText,
                InputMethodManager.SHOW_IMPLICIT);
    }

    public static void closeSoftKeyword(Context context, View view) {
        InputMethodManager mInputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static String getOperatorName(Context context) {
        TelephonyManager telephonyManager = getTelephonyManager(context);
        if (telephonyManager == null) return "";
        return telephonyManager.getSimOperatorName();
    }

    @SuppressLint("MissingPermission")
    public static String getPhoneNumber(Context context) {
        TelephonyManager telephonyManager = getTelephonyManager(context);
        if (telephonyManager == null) return "";
        return telephonyManager.getLine1Number();
    }

    public static String getQEmuDriverFile() {
        File driver_file = new File("/proc/tty/drivers");
        if (driver_file.exists() && driver_file.canRead()) {
            byte[] data = new byte[(int) driver_file.length()];
            try {
                InputStream inStream = new FileInputStream(driver_file);
                inStream.read(data);
                inStream.close();
            } catch (FileNotFoundException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
            String driver_data = new String(data);
            return driver_data;
        }
        return "";
    }

    public static String getEmulatorFiles() {
        try {

            boolean exists = false;
            JSONArray json = new JSONArray();

            String[] known_files = {
                    "/system/lib/libc_malloc_debug_qemu.so",
                    "/sys/qemu_trace",
                    "/system/bin/qemu-props"
            };
            for (String file_name : known_files) {
                JSONObject jsonObject = new JSONObject();
                File qemu_file = new File(file_name);
                exists = qemu_file.exists();
                if (exists) {
                    jsonObject.put("file_name", file_name);
                    jsonObject.put("exists", qemu_file.exists());
                    json.put(jsonObject);
                }

            }
            return exists ? json.toString() : "";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    private static TelephonyManager getTelephonyManager(Context context) {
        return (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public static String getProp(Context context, String property) {

        try {
            ClassLoader classLoader = context.getClassLoader();
            Class<?> systemProperties = classLoader.loadClass("android.os.SystemProperties");
            Method get = systemProperties.getMethod("get", String.class);
            Object[] objects = new Object[1];
            objects[0] = property;
            return (String) get.invoke(systemProperties, objects);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
