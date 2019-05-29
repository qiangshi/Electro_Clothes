package com.business.electr.clothes.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.business.electr.clothes.App;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.DataEvent;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.mvp.view.IView;
import com.business.electr.clothes.ui.activity.login.LoginActivity;
import com.business.electr.clothes.ui.fragment.dialog.EmptyFragment;
import com.business.electr.clothes.ui.fragment.dialog.LoadingFragment;
import com.business.electr.clothes.ui.fragment.dialog.PopupFragment;
import com.business.electr.clothes.ui.fragment.dialog.PowerFragment;
import com.business.electr.clothes.ui.fragment.history.SelectTimeFragment;
import com.business.electr.clothes.utils.PermissionPageUtils;
import com.business.electr.clothes.utils.StatusBar.StatusBarUtil;
import com.business.electr.clothes.view.SearchBarView;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import butterknife.ButterKnife;



/**
 * Created by zenghaiqiang on 2019/1/5.
 * 描述：MVP 基础activity
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView {

    /**
     * 代理类
     */
    protected P mPresenter;
    /**
     * loading fragment
     */
    private LoadingFragment loadingFragment;
    /**
     * 普通弹框
     */
    protected PopupFragment popupFragment;
    /**
     * 权限申请弹框
     */
    protected PowerFragment powerFragment;
    /**
     * 空界面
     */
    protected EmptyFragment mEmptyFragment;
    /**
     * 权限检查回调接口
     */
    private OnPermissionCheckSuccess onPermissionCheckSuccess;

    private SelectTimeFragment selectTimeFragment;
    /**
     * 权限检查列表
     */
    protected String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE};
    protected boolean hasCheckPermission = false;
    /**
     * 权限申请提示信息
     */
    private String content;

    private RelativeLayout relativeLayout;
    protected ImageView mBackBtn, mRightBtn;
    protected TextView mTitleTv, mRightTv;
    protected SearchBarView mSearchBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = LayoutInflater.from(this).inflate(R.layout.activity_base, null);
        relativeLayout = root.findViewById(R.id.relativeLayout);
        this.setContentView(root);
        LayoutInflater.from(this).inflate(getLayoutId(), relativeLayout);
        setDarkStatusTheme();
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPresenter = getPresenter();

        ((App) getApplication()).getActivities().add(this);

        initDataAndEvent();
    }

    /**
     * 获取contentViewId
     *
     * @return contentViewId
     */
    protected abstract int getLayoutId();

    /**
     * 获取代理对象
     *
     * @return 代理对象
     */
    protected abstract P getPresenter();

    /**
     * 初始化数据和事件
     */
    protected abstract void initDataAndEvent();

    /**
     * 设置状态栏主题设置
     */
    public void setDarkStatusTheme() {
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
//        StatusBarUtil.setStatusBarColor(this,0xffff0000);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        // 所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
//        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
//            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
//            // 这样半透明+白=灰, 状态栏的文字能看得清
//            StatusBarUtil.setStatusBarColor(this,0x55000000);
//        }
    }


    /*初始化普通标题*/
    protected void initTitle(String title) {
        initTitle(true, title);
    }

    /*初始化普通标题 是否显示左侧标题*/
    protected void initTitle(boolean isShowLeft, String title) {
        initView();
        if (isShowLeft) {
            mBackBtn.setVisibility(View.VISIBLE);
        } else {
            mBackBtn.setVisibility(View.GONE);
        }
        mTitleTv.setText(title);
    }

    protected void initRightTitle(String title, String rightContent) {
        initTitle(title, 0, rightContent);
    }

    protected void initRightTitle(String title, int rightImgResId) {
        initTitle(title, rightImgResId, null);
    }

    private void initTitle(String title, int rightImgResId, String rightContent) {
        initView();
        mTitleTv.setText(title);
        if (rightImgResId > 0) {
            mRightBtn.setImageResource(rightImgResId);
            mRightBtn.setVisibility(View.VISIBLE);
        } else {
            mRightBtn.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(rightContent)) {
            mRightTv.setText(rightContent);
            mRightTv.setVisibility(View.VISIBLE);
        } else {
            mRightTv.setVisibility(View.GONE);
        }
    }

    private void initView() {
        mBackBtn = findViewById(R.id.btn_back);
        mTitleTv = findViewById(R.id.tv_title);
        mRightBtn = findViewById(R.id.btn_title_right);
        mRightTv = findViewById(R.id.tv_right_btn);
        mSearchBar = findViewById(R.id.search_bar_title);
        if (mBackBtn != null) {
            mBackBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }


    /**
     * 自定义按钮点击事件的 Fragment
     *
     * @param title    标题
     * @param message  内容
     * @param left     左边按钮
     * @param right    右边按钮
     * @param listener 点击监听
     */
    public void showNormalFragment(String title, String message, String left, String right, View.OnClickListener listener) {
        if (loadingFragment != null && loadingFragment.isAdded()) {
            loadingFragment.dismissAllowingStateLoss();
        }
        if (popupFragment == null) {
            popupFragment = new PopupFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.DIALOG_HINT, title);
            bundle.putString(Constant.DIALOG_CONTENT, message);
            bundle.putString(Constant.DIALOG_LEFT, left);
            bundle.putString(Constant.DIALOG_RIGHT, right);
            popupFragment.setArguments(bundle);
            popupFragment.setListener(listener);
            getSupportFragmentManager().beginTransaction().add(popupFragment, "normal_fragment").commitAllowingStateLoss();
        } else if (popupFragment.isVisible()) {
            popupFragment.setListener(listener);
        } else {
            popupFragment.setListener(listener);
            if (popupFragment.isAdded()) {
                popupFragment.dismiss();
            }
            getSupportFragmentManager().beginTransaction().add(popupFragment, "normal_fragment").commitAllowingStateLoss();
        }
    }


    /**
     * 无需自定义按钮点击事件的 Fragment
     *
     * @param title   标题
     * @param message 内容
     * @param left    左边按钮
     * @param right   右边按钮
     */
    public void showNormalFragment(String title, String message, String left, String right) {
        showNormalFragment(title, message, left, right, null);
    }

    /**
     * 关闭提示弹窗
     */
    public void hidePopupFragment() {
        if (popupFragment != null) {
            popupFragment.dismissAllowingStateLoss();
            popupFragment = null;
        }
    }


    /**
     * 显示 loading
     */
    public void showLoadingFragment() {
        if (loadingFragment != null) {
            loadingFragment.dismissAllowingStateLoss();
            loadingFragment = null;
        }
        loadingFragment = new LoadingFragment();
        getSupportFragmentManager().beginTransaction().add(loadingFragment, "loading_fragment").commitAllowingStateLoss();
    }

    /**
     * 隐藏loading
     */
    public void cancelLoadingFragment() {
        if (loadingFragment != null && loadingFragment.isAdded()) {
            loadingFragment.dismissAllowingStateLoss();
            loadingFragment = null;
        }
    }

    /**
     * 显示权限申请框
     */
    public void showPowerFragment(String content, View.OnClickListener onClickListener) {
        if (powerFragment == null) {
            powerFragment = new PowerFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putString("content", content);
        powerFragment.setArguments(bundle);
        powerFragment.setOnClickListener(onClickListener);
        if (!powerFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().add(powerFragment, "").commitAllowingStateLoss();
        }
    }

    /**
     * 隐藏权限申请框
     */
    public void cancelPowerFragment() {
        if (powerFragment != null) {
            powerFragment.dismissAllowingStateLoss();
            powerFragment = null;
        }
    }

    /**
     * 显示空页面
     *
     * @param contentId 要依附在哪个的id上
     */
    public void showEmpty(int contentId) {
        if (mEmptyFragment == null) {
            mEmptyFragment = new EmptyFragment();
            getSupportFragmentManager().beginTransaction().add(contentId, mEmptyFragment).commitAllowingStateLoss();
        } else
            getSupportFragmentManager().beginTransaction().show(mEmptyFragment).commitAllowingStateLoss();
    }

    /**
     * 关闭空页面
     */
    public void hideEmpty() {
        if (mEmptyFragment != null && mEmptyFragment.isAdded())
            getSupportFragmentManager().beginTransaction().hide(mEmptyFragment).commitAllowingStateLoss();
    }


    /**
     * 显示空页面
     */
    public void showSelectFragment(long month,SelectTimeFragment.SelectTimeListener listener) {
        selectTimeFragment = new SelectTimeFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("month",month);
        selectTimeFragment.setArguments(bundle);
        selectTimeFragment.setListener(listener);
        getSupportFragmentManager().beginTransaction().add(R.id.relativeLayout, selectTimeFragment).commitAllowingStateLoss();
    }

    /**
     * 关闭空页面
     */
    public void hideSelectFragment() {
        if (selectTimeFragment != null && selectTimeFragment.isAdded())
            getSupportFragmentManager().beginTransaction().hide(selectTimeFragment).commitAllowingStateLoss();
    }

    /**
     * 检查权限
     *
     * @param permissions 权限
     */
    public void checkPermission(final String[] permissions, String content, OnPermissionCheckSuccess onPermissionCheckSuccess) {
        hasCheckPermission = true;
        this.permissions = permissions;
        this.content = content;
        this.onPermissionCheckSuccess = onPermissionCheckSuccess;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Build.BRAND.equals("Meizu")) {
            if (!checkPermissionAllGranted(permissions)) {
                ActivityCompat.requestPermissions(this, permissions, Constant.PERMISSIONS_RESULT_CODE);
            } else {
                onPermissionCheckSuccess.checkSuccess();
            }
        } else {
            onPermissionCheckSuccess.checkSuccess();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constant.PERMISSIONS_RESULT_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Build.BRAND.equals("Meizu")) {
                if (!checkPermissionAllGranted(permissions)) {
                    showPowerFragment(content, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            switch (v.getId()) {
                                case R.id.tv_pression:
                                    if (onPermissionCheckSuccess != null) {
                                        onPermissionCheckSuccess.checkFailed();
                                    }
                                    cancelPowerFragment();
                                    break;
                                case R.id.tv_power_fragment:
                                    hasCheckPermission = false;
                                    PermissionPageUtils permissionPageUtils = new PermissionPageUtils(BaseActivity.this);
                                    permissionPageUtils.jumpPermissionPage();
                                    cancelPowerFragment();
                                    break;
                            }
                        }
                    });
                } else {
                    if (onPermissionCheckSuccess != null) {
                        onPermissionCheckSuccess.checkSuccess();
                    }
                }
            }
        }
    }

    /**
     * 检查是否拥有指定的所有权限
     */
    public boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            // 只要有一个权限没有被授予, 则直接返回 false
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取权限结果的回调接口
     */
    public interface OnPermissionCheckSuccess {
        /**
         * 权限获取成功
         */
        void checkSuccess();

        /**
         * 权限获取失败
         */
        void checkFailed();
    }

    @Subscribe
    public void onEvent(DataEvent event) {
        if (event.getType() == DataEvent.TYPE_LOGIN) {
            if (this instanceof MainActivity) {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
            finish();
        }
    }

//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        if (loadingFragment != null)
            loadingFragment.dismissAllowingStateLoss();
        if (popupFragment != null)
            popupFragment.dismissAllowingStateLoss();
        ((App) getApplication()).getActivities().remove(this);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    @Override
    public void showLoading() {
        showLoadingFragment();
    }

    @Override
    public void hideLoading() {
        cancelLoadingFragment();
    }

    @Override
    public void toastMessage(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show();
    }

}
