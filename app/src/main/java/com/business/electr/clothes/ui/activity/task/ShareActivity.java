package com.business.electr.clothes.ui.activity.task;


import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.utils.MLog;
import com.business.electr.clothes.utils.StatusBar.StatusBarUtil;
import com.sankuai.waimai.router.annotation.RouterUri;

import java.io.File;
import java.io.FileOutputStream;

@RouterUri(path = {RouterCons.CREATE_SHARE_TASK})
public class ShareActivity extends BaseActivity {

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            View dView = getWindow().getDecorView();
            dView.setDrawingCacheEnabled(true);
            dView.buildDrawingCache();
            Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
            if (bitmap != null) {
                try {
                    // 获取内置SD卡路径
                    String sdCardPath = Environment.getExternalStorageDirectory().getPath();
                    // 图片文件路径
                    String filePath = sdCardPath + File.separator + "screenshot.png";
                    File file = new File(filePath);
                    FileOutputStream os = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                    os.flush();
                    os.close();
                    MLog.d("====zhq====", "存储完成");
                } catch (Exception e) {
                    MLog.d("====zhq====", "存储失败");
                }
            }
        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        handler.sendEmptyMessageDelayed(1,2000);
    }
}
