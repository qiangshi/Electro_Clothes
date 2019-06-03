package com.business.electr.clothes.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.business.electr.clothes.App;

/**
 * 常用工具类
 */
public class CommonUtils {

    /**
     * dp转px
     *
     * @param dpValue dp
     * @return px
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 判断设备是否具有虚拟导航栏
     *
     * @return 设备是否具有虚拟导航栏
     */
//    public static boolean hasNavigationBar() {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) return true;
//        WindowManager wm = (WindowManager) AnyinfoApplicationLike.self().getApplication().getSystemService(Context.WINDOW_SERVICE);
//        Display d = wm.getDefaultDisplay();
//        DisplayMetrics realDisplayMetrics = new DisplayMetrics();
//        d.getRealMetrics(realDisplayMetrics);
//        int realHeight = realDisplayMetrics.heightPixels;
//        int realWidth = realDisplayMetrics.widthPixels;
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        d.getMetrics(displayMetrics);
//        int displayHeight = displayMetrics.heightPixels;
//        int displayWidth = displayMetrics.widthPixels;
//        return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0;
//    }
//
//    /**
//     * 得到以px为单位的虚拟导航栏高度，若设备没有虚拟导航栏，返回0.
//     *
//     * @return 虚拟导航栏高度(px)，若设备没有虚拟导航栏，返回0.
//     */
//    public static int getNavigationBarHeightInPx() {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) return dp2px(48);
//        int navBarHeightInPx = 0;
//        Resources rs = AnyinfoApplicationLike.self().getApplication().getResources();
//        int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
//        if (id > 0 && hasNavigationBar()) navBarHeightInPx = rs.getDimensionPixelSize(id);
//        return navBarHeightInPx;
//    }
//
//    /**
//     * 创建File对象，对应于data/data/${packageName}/cache/fileName.
//     *
//     * @param fileName 文件名
//     * @return File
//     */
//    @SuppressWarnings("ResultOfMethodCallIgnored")
//    public static File createImageFile(String fileName) {
//        File dir = new File(AnyinfoApplicationLike.self().getApplication().getCacheDir(), "images");
//        if (!dir.exists()) dir.mkdirs();
//        return new File(dir, fileName);
//    }
//
//    /**
//     * 获取进程名
//     *
//     * @param cxt
//     * @param pid
//     * @return
//     */
//    public static String getProcessName(Context cxt, int pid) {
//        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
//        if (runningApps == null) {
//            return null;
//        }
//        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
//            if (procInfo.pid == pid) {
//                return procInfo.processName;
//            }
//        }
//        return null;
//    }

    /**
     * 获取版本名称
     *
     * @return
     */
    public static String getVersionName() {
        PackageManager packageManager = App.getApp().getPackageManager();
        try {
            PackageInfo packInfo = packageManager.getPackageInfo(App.getApp().getApplicationContext().getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return " ";
    }

    /**
     * 获取版本号
     *
     * @return
     */
    public static long getVersionCode() {
        PackageManager packageManager = App.getApp().getPackageManager();
        try {
            PackageInfo packInfo = packageManager.getPackageInfo(App.getApp().getApplicationContext().getPackageName(), 0);
            return packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 100;
    }
}
