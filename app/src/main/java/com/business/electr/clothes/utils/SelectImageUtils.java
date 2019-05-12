package com.business.electr.clothes.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;

import com.business.electr.clothes.R;
import com.business.electr.clothes.utils.StatusBar.StatusBarUtil;
import com.yalantis.ucrop.UCrop;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.config.ISListConfig;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.app.Activity.RESULT_OK;

/**
 * 图片选择封装工具类
 */
public class SelectImageUtils {

    public static final int REQUEST_CODE_CHOOSE = 998;
    // 读写文件请求码
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;

    public static void selectPhoto(Activity context, String title, boolean isMulti, boolean isNeedCamera, int maxNum) {
        // 自由配置选项
        ISListConfig config = new ISListConfig.Builder()
                // 是否多选, 默认true
                .multiSelect(isMulti)
                // 是否记住上次选中记录, 仅当multiSelect为true的时候配置，默认为true
                .rememberSelected(false)
                // “确定”按钮背景色
                .btnBgColor(Color.GRAY)
                // “确定”按钮文字颜色
                .btnTextColor(Color.BLUE)
                // 返回图标ResId
                .backResId(R.drawable.ic_goback)
                // 标题
                .title(title)
                // 标题文字颜色
                .titleColor(Color.BLACK)
                // TitleBar背景色
                .titleBgColor(context.getResources().getColor(R.color.white))
                // 第一个是否显示相机，默认true
                .needCamera(isNeedCamera)
                // 最大选择图片数量，默认9
                .maxNum(maxNum)
                .build();

        ISNav.getInstance().toListActivity(context, config, REQUEST_CODE_CHOOSE);
    }

    /**
     * 图片选择及裁剪的回调
     *
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data        数据
     */
    public static void onActivityResult(int requestCode, int resultCode, Intent data, @NonNull OnSelectImageListener listener) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CHOOSE:
                    if (data != null) {
                        List<String> pathList = data.getStringArrayListExtra("result");
                        listener.onSelectImage(pathList);
                    }
                    break;
                case UCrop.REQUEST_CROP:
                    Uri output = UCrop.getOutput(data);
                    if (output != null) {
                        File file = new File(output.getPath());
                        if (file.exists()) {
                            listener.onCropImage(file.getAbsolutePath());
                        }
                    }
                    break;
            }
        }
        if (resultCode == UCrop.RESULT_ERROR) {
            Throwable cropError = UCrop.getError(data);
            if (cropError != null) {
                cropError.printStackTrace();
            }
        }
    }

    /**
     * 裁剪图片，裁剪之后的照片存储到一个新的文件
     */
    public static void cropPhoto(Activity activity, String imgPath) {
        File file = new File(imgPath);
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        fileName = System.currentTimeMillis() + "." + suffix;
        File targetFile = new File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName);
        Uri parse = Uri.fromFile(file);
        UCrop.of(parse, Uri.fromFile(targetFile))
                .withMaxResultSize(1000, 1000)
                .start(activity);
    }

    /**
     * 获取权限
     *
     * @param activity
     * @param listener 权限检查回调接口
     */
    public static void autoObtainStoragePermission(Activity activity, OnObtainPermissionListener listener) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            listener.onObtainedPermission();
        }
    }

    /**
     * 权限申请回调方法
     *
     * @param activity     activity
     * @param requestCode  请求码
     * @param permissions  权限组
     * @param grantResults 权限申请结果
     * @param listener     权限检查回调接口
     */
    public static void onRequestPermissionsResult(Activity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, OnRequestPermissionListener listener) {
        switch (requestCode) {
            case STORAGE_PERMISSIONS_REQUEST_CODE://调用系统相册申请Sdcard权限回调
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    listener.onGrantPermission();
                } else {
                    listener.onDenyPermission();
                }
                break;
        }
    }

    /**
     * 图片操作回调
     */
    public interface OnSelectImageListener {

        /**
         * 图片选择回调
         *
         * @param pathList 图片选择列表
         */
        void onSelectImage(List<String> pathList);

        /**
         * 图片裁剪回调
         *
         * @param filePath 图片文件地址
         */
        void onCropImage(String filePath);

    }

    /**
     * 权限检查回调接口
     */
    public interface OnObtainPermissionListener {

        /**
         * 已有权限回调
         */
        void onObtainedPermission();

        @Deprecated
        void onRequestPermission();
    }

    /**
     * 权限检查回调接口
     */
    public interface OnRequestPermissionListener {

        /**
         * 授权回调
         */
        void onGrantPermission();

        /**
         * 拒绝回调
         */
        void onDenyPermission();
    }
}
