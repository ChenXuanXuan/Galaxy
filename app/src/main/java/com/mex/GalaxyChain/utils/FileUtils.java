package com.mex.GalaxyChain.utils;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lenote on 2016/1/11.
 */
public class FileUtils {

    public static void writeImage(Bitmap bitmap, String destPath, int quality) {

        new File(new File(destPath).getParent()).mkdirs();

        writeImage(bitmap, Bitmap.CompressFormat.JPEG, destPath, quality);
    }

    /**
     * 创建一个文件，创建成功返回true
     *
     * @param filePath
     * @return
     */
    public static boolean createFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                return file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 删除一个文件
     *
     * @param filePath
     *            要删除的文件路径名
     * @return true if this file was deleted, false otherwise
     */
    public static boolean deleteFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void writeImage(Bitmap bitmap, Bitmap.CompressFormat format,
                                  String destPath, int quality) {
        FileOutputStream out = null;
        try {
            deleteFile(destPath);
            if (createFile(destPath)) {
                out = new FileOutputStream(destPath);
                bitmap.compress(format, quality, out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                    out = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
