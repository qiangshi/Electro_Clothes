package com.business.electr.clothes.ui.activity.mine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.DataEvent;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.ui.activity.WebViewContainerActivity;
import com.business.electr.clothes.utils.CacheDataManager;
import com.business.electr.clothes.utils.SharePreferenceUtil;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/01/24. 描述：设置
 */
@RouterUri(path = {RouterCons.CREATE_SETTING})
public class SettingActivity extends BaseActivity {


    @BindView(R.id.tv_clear_cpu)
    TextView tvClearCpu;//缓存
    @BindView(R.id.tv_check_update)
    TextView tvCheckUpdate;//版本号
    @BindView(R.id.switch_msg)
    Switch switchMsg;//消息开关


    //更新缓存大小的Handler
    private NoLeakHandler mHandler = new NoLeakHandler(this);

    private static class NoLeakHandler extends Handler {

        private WeakReference<SettingActivity> activity;

        NoLeakHandler(SettingActivity activity) {
            this.activity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 999:
                    SettingActivity settingActivity = activity.get();
                    Toast.makeText(settingActivity, R.string.clear_success, Toast.LENGTH_SHORT)
                            .show();
                    settingActivity.tvClearCpu.setText(settingActivity.getCacheMemory());
                default:
                    break;
            }
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        initTitle(getResources().getString(R.string.set));
        switchMsg.setOnCheckedChangeListener(checkListener);
        tvClearCpu.setText(getCacheMemory());
    }


    private CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {//开
                toastMessage(R.string.msg_open);
            } else {//关
                toastMessage(R.string.msg_close);
            }
        }
    };


    private String getCacheMemory() {
        return new CacheDataManager().getTotalCacheSize(this);
    }

    @OnClick({R.id.lin_about_our, R.id.lin_link_our, R.id.lin_user_argument,
            R.id.lin_private_policy, R.id.lin_clear_cpu, R.id.lin_check_update, R.id.tv_log_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lin_about_our://关于我们
                new DefaultUriRequest(this,RouterCons.WEB_VIEW_INFO)
                        .putExtra(Constant.URL,"file:///android_asset/apps/web/html/about_us.html")
                        .putExtra(WebViewContainerActivity.TITLE,getResources().getString(R.string.about_our))
                        .start();
                break;
            case R.id.lin_link_our://联系我们
                new DefaultUriRequest(this, RouterCons.CREATE_LINK_OUR)
                        .start();
                break;
            case R.id.lin_user_argument://用户协议
                new DefaultUriRequest(this,RouterCons.WEB_VIEW_INFO)
                        .putExtra(Constant.URL,"file:///android_asset/apps/web/html/service.html")
                        .putExtra(WebViewContainerActivity.TITLE,getResources().getString(R.string.user_argument))
                        .start();
                break;
            case R.id.lin_private_policy://隐私政策
                new DefaultUriRequest(this,RouterCons.WEB_VIEW_INFO)
                        .putExtra(Constant.URL,"file:///android_asset/apps/web/html/privacy.html")
                        .putExtra(WebViewContainerActivity.TITLE,getResources().getString(R.string.private_policy))
                        .start();
                break;
            case R.id.lin_clear_cpu://清空缓存
                clearCache();
                break;
            case R.id.lin_check_update://检查更新
                toastMessage(R.string.used_update_news);
                break;
            case R.id.tv_log_out://退出登录
                DataCacheManager.saveUserInfo(null);
                DataCacheManager.saveToken("");
                SharePreferenceUtil.putBoolean(Constant.IS_LOGIN, false);
                SharePreferenceUtil.putInt(Constant.CLOCK_TYPE,0);
                EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_LOGIN, null));
                break;
        }
    }


    /**
     * 清除缓存
     */
    private void clearCache() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Looper.prepare();
                CacheDataManager manager = new CacheDataManager();
                manager.setListener(new CacheDataManager.ClearCacheListener() {
                    @Override
                    public void clearSuccess() {
                        mHandler.sendEmptyMessage(999);
                    }

                    @Override
                    public void clearFailed() {

                    }
                });
                manager.clearAllCache(SettingActivity.this);
            }
        }).start();
    }
}
