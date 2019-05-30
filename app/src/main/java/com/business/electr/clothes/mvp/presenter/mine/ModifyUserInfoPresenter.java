package com.business.electr.clothes.mvp.presenter.mine;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;

import com.alibaba.fastjson.JSONObject;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.MapModel;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.mvp.view.mine.ModifyUserInfoView;
import com.business.electr.clothes.net.ApiClient;
import com.business.electr.clothes.net.BaseApiResponse;
import com.business.electr.clothes.net.BaseObserver;
import com.business.electr.clothes.net.exception.ResponseException;
import com.business.electr.clothes.observer.SynchronizationObserver;
import com.business.electr.clothes.utils.BitmapUtils;
import com.business.electr.clothes.utils.DataCheckUtils;
import com.business.electr.clothes.utils.MLog;
import com.business.electr.clothes.utils.SelectImageUtils;
import com.business.electr.clothes.utils.ToastUtils;
import com.yalantis.ucrop.UCrop;
import com.yuyh.library.imgsel.utils.LogUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

/**
 * Created by zenghaiqiang on 2019/1/24.
 * 我的信息 P 层
 */
public class ModifyUserInfoPresenter extends BasePresenter<ModifyUserInfoView> {
    public ModifyUserInfoPresenter(ModifyUserInfoView view) {
        super(view);
    }


    /**
     * 获取用户信息
     */
    public void getUserInfo() {
        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addCommonMap()
                .addParams("userId", DataCacheManager.getUserInfo().getUserId())
                .toRequestBody();
        addSubscription(apiStores.requestUserInfo(requestBody),
                new BaseObserver<BaseApiResponse<MapModel<UserBean>>>() {
                    @Override
                    public void onError(ResponseException e) {

                    }

                    @Override
                    public void onNext(BaseApiResponse<MapModel<UserBean>> data) {
                        if (data.getData() != null) {
                            mView.getUserInfoSuccess(data.getData().getMap());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void updateUserHead(String headFile) {
        mView.showLoading();
        String extName = "jpg";
        if (headFile.indexOf(".") > 0) {
            if (headFile.contains(".png")) {
                extName = "png";
            } else if (headFile.contains(".jpg")) {
                extName = "jpg";
            }else {
                extName = "jpeg";
            }
        }
        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addParams("userId", DataCacheManager.getUserInfo().getUserId())
                .addParams("content", imgToBase64String(headFile))
                .addParams("extName",extName)
                .toRequestBody();
        addSubscription(apiStores.requestUserHead(requestBody),
                new BaseObserver<BaseApiResponse<MapModel<String>>>() {
                    @Override
                    public void onError(ResponseException e) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onNext(BaseApiResponse<MapModel<String>> data) {
                        mView.hideLoading();
                        JSONObject object = JSONObject.parseObject(data.getData().getMap());
                        String imgUrl = object.getString("headImgUrl");
                        getUserInfo();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }


    /**
     * 更新用户信息
     */
    public void updateUserInfo(String headImg, String nickName, int sex, String height, String weight, String birthDate) {
        birthDate = birthDate + " 00:00:00";
        mView.showLoading();
        if (headImg != null) updateUserHead(headImg);
        int heigh = 0, weigh = 0;
        if (!TextUtils.isEmpty(height)) {
            heigh = Integer.valueOf(height.substring(0, height.length() - 2));
        }
        if (!TextUtils.isEmpty(weight)) {
            weigh = Integer.valueOf(weight.substring(0, weight.length() - 2));
        }
        RequestBody requestBody = ApiClient.getInstance().getBuilder()
                .addCommonMap()
                .addParams("userId", DataCacheManager.getUserInfo().getUserId())
                .addParams("sex", sex)
                .addParams("height", heigh)
                .addParams("weight", weigh)
                .addParams("birthDate", birthDate)
                .addParams("nickName", nickName)
                .toRequestBody();
        addSubscription(apiStores.requestUpdateUserInfo(requestBody),
                new BaseObserver<BaseApiResponse<String>>() {
                    @Override
                    public void onError(ResponseException e) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onNext(BaseApiResponse<String> data) {
                        mView.hideLoading();
                        mView.toastMessage(R.string.preservation_success);
                        mView.updateUserInfoSuccess();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });
    }

    // 读写文件请求码
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;

    /**
     * 获取权限
     */
    public void autoObtainStoragePermission(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            mView.selectPhoto();
        }
    }

    public void onRequestPermissionsResult(Activity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case STORAGE_PERMISSIONS_REQUEST_CODE://调用系统相册申请Sdcard权限回调
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mView.selectPhoto();
                } else {
                    ToastUtils.showToast(activity, "请授予内存空间访问权限");
                }
                break;
        }
    }

    /**
     * 图片选择及裁剪的回调
     *
     * @param activity    目标activity
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data        数据
     */
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SelectImageUtils.REQUEST_CODE_CHOOSE:
                    List<String> pathList = data.getStringArrayListExtra("result");
                    for (String path : pathList) {
                        cropPhoto(activity, path);
                    }
                    break;
                case UCrop.REQUEST_CROP:
                    if (data != null) {
                        File file = uriToRoundFileImg(UCrop.getOutput(data));
//                        String base64Url = imgToBase64String(file.getAbsolutePath());
                        mView.onUploadSuccess(file.getAbsolutePath());
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
     * 将图片转为base64 位 * * @param url 图片的地址 * @return
     */
    public static String imgToBase64String(String url) {
        if (url == null) {
            return null;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(url);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if (url.indexOf(".") > 0) {
            if (url.contains(".png")) {
                url = url.substring(0, url.indexOf(".png"));
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            } else if (url.contains(".jpg")) {
                url = url.substring(0, url.indexOf(".jpg"));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            }
        }
        byte[] byteServer = stream.toByteArray();
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MLog.e( "图片的大小：" + byteServer.length);
        String base64String = Base64.encodeToString(byteServer, 0, byteServer.length, Base64.DEFAULT);
        return base64String;
    }


    /**
     * 图片转换成圆形图片
     *
     * @param uri 文件uri
     * @return 图片文件
     */
    private File uriToRoundFileImg(Uri uri) {
        String path = uri.getPath();
        File file = new File(path);
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        Bitmap roundBitmap = BitmapUtils.toRoundBitmap(bitmap);
        if (!path.endsWith(".png")) {
            String name = file.getName();
            if (name.contains(".")) {
                int i1 = name.lastIndexOf(".");
                String substring = name.substring(0, i1);
                name = substring + ".png";
            }
            path = file.getParent() + name;
        }
        bitmap.recycle();
        return BitmapUtils.saveBitmapFile(roundBitmap, path);
    }

    /**
     * 裁剪图片，裁剪之后的照片存储到一个新的文件
     */
    private void cropPhoto(Activity activity, String imgPath) {
        File file = new File(imgPath);
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        fileName = System.currentTimeMillis() + "." + suffix;
        File targetFile = new File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), fileName);
        UCrop.Options options = new UCrop.Options();
        options.setCircleDimmedLayer(true);
        Uri parse = Uri.fromFile(file);
        UCrop.of(parse, Uri.fromFile(targetFile))
                .withAspectRatio(1, 1)
                .withMaxResultSize(1000, 1000)
                .withOptions(options)
                .start(activity);
    }

}
