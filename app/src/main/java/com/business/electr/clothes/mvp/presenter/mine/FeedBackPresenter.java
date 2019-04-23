package com.business.electr.clothes.mvp.presenter.mine;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.mvp.view.mine.FeedBackView;
import com.business.electr.clothes.net.BaseObserver;
import com.business.electr.clothes.net.exception.ResponseException;
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
 * Created by zenghaiqiang on 2019/1/25.
 * 问题反馈
 */
public class FeedBackPresenter extends BasePresenter<FeedBackView> {
    public FeedBackPresenter(FeedBackView view) {
        super(view);
    }



    public void requestFeedBack(MultipartBody.Part user_id,
                                MultipartBody.Part token,
                                MultipartBody.Part content,
                                MultipartBody.Part pic1,
                                MultipartBody.Part pic2,
                                MultipartBody.Part pic3,
                                MultipartBody.Part contact){

        if(TextUtils.isEmpty(user_id.toString())){
            mView.toastMessage(R.string.please_input_feedback_content);
            return;
        }
        if(TextUtils.isEmpty(contact.toString())){
            mView.toastMessage(R.string.contact_email_text);
            return;
        }
        mView.showLoading();
        addSubscription(apiStores.requestFeedBack(user_id, token, content, pic1, pic2, pic3, contact),
                new BaseObserver() {
                    @Override
                    public void onError(ResponseException e) {
                        mView.hideLoading();
                    }

                    @Override
                    public void onNext(Object o) {
                        mView.hideLoading();
                        mView.toastMessage(R.string.commit_success);
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });

    }









    // 读写文件请求码
    private static final int STORAGE_PERMISSIONS_REQUEST_CODE = 0x04;
    private int curIndex;

    /**
     * 获取权限
     */
    public void autoObtainStoragePermission(Activity activity,int position) {
        curIndex = position;
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSIONS_REQUEST_CODE);
        } else {
            mView.selectPhoto(position);
        }
    }

    public void onRequestPermissionsResult(Activity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case STORAGE_PERMISSIONS_REQUEST_CODE://调用系统相册申请Sdcard权限回调
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mView.selectPhoto(curIndex);
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
                    Uri output = UCrop.getOutput(data);
                    if (output != null) {
                        File file = new File(output.getPath());
                        if (file.exists()) {
                            mView.onUploadSuccess(file.getAbsolutePath(), curIndex);
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
