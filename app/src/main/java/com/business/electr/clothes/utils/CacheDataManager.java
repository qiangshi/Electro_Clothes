package com.business.electr.clothes.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Administrator on 2017/8/14.
 * desc: 清理缓存工具类
 */

public class CacheDataManager {

    private ClearCacheListener sListener;

    public interface ClearCacheListener {
        void clearSuccess();

        void clearFailed();
    }

    public void setListener(ClearCacheListener listener) {
        sListener = listener;
    }


    public void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
        sListener.clearSuccess();
    }


    private boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {

            String[] children = dir.list();

            for (String aChildren : children) {

                boolean success = deleteDir(new File(dir, aChildren));

                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public String getTotalCacheSize(Context context) {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(Objects.requireNonNull(context.getExternalCacheDir()));
        }
        return getFormatSize(cacheSize);
    }

    private long getFolderSize(File file) {
        long size = 0;
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                size += getFolderSize(f);
            } else {
                size += f.length();
            }
        }
        return size;
    }

    private String getFormatSize(double size) {

        double megaByte = size / 1024 / 1024;

        double gigaByte = megaByte / 1024;

        if (gigaByte < 1) {

            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));

            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";

        }

        double teraBytes = gigaByte / 1024;

        if (teraBytes < 1) {

            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));

            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";

        }

        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }
}
