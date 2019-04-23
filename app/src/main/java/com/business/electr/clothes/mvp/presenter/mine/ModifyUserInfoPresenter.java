package com.business.electr.clothes.mvp.presenter.mine;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.mvp.view.mine.ModifyUserInfoView;
import com.business.electr.clothes.net.BaseApiResponse;
import com.business.electr.clothes.net.BaseObserver;
import com.business.electr.clothes.net.exception.ResponseException;
import com.business.electr.clothes.utils.BitmapUtils;
import com.business.electr.clothes.utils.SelectImageUtils;
import com.business.electr.clothes.utils.ToastUtils;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import okhttp3.MultipartBody;

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
    public void getUserInfo(){
        addSubscription(apiStores.requestUserInfo(DataCacheManager.getUserInfo().getUserId()),
                new BaseObserver<BaseApiResponse<UserBean>>() {
                    @Override
                    public void onError(ResponseException e) {

                    }

                    @Override
                    public void onNext(BaseApiResponse<UserBean> data) {
                        if(data.getData() != null){
                            mView.getUserInfoSuccess(data.getData());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 更新用户信息
     */
    public void updateUserInfo(MultipartBody.Part user_id,
                               MultipartBody.Part token,
                               MultipartBody.Part portrait,
                               MultipartBody.Part nickname,
                               MultipartBody.Part contact,
                               MultipartBody.Part gender,
                               MultipartBody.Part birthday){
        mView.showLoading();
        addSubscription(apiStores.requestUpdateUserInfo(user_id,token,portrait,nickname,contact,gender,birthday),
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
                        getUserInfo();
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
