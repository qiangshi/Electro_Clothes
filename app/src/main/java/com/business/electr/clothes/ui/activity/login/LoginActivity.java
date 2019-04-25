package com.business.electr.clothes.ui.activity.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.login.LoginPresenter;
import com.business.electr.clothes.mvp.view.login.LoginView;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.ui.activity.MainActivity;
import com.business.electr.clothes.utils.SharePreferenceUtil;
import com.business.electr.clothes.utils.StatusBar.StatusBarUtil;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;
import butterknife.BindView;
import butterknife.OnClick;

@RouterUri(path = {RouterCons.CREATE_LOGIN})
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.area_code)
    TextView areaCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initDataAndEvent() {
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
    }

    @Override
    public void loginSuccess(UserBean userBean) {
        saveLoginInfo(userBean);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /**
     * 保存用户信息
     */
    private void saveLoginInfo(UserBean userBean) {
        DataCacheManager.saveToken(userBean.getToken());
        DataCacheManager.saveUserInfo(userBean);
        SharePreferenceUtil.putBoolean(Constant.IS_LOGIN, true);
    }

    @OnClick({R.id.img_logout, R.id.area_code, R.id.phone_close, R.id.tv_code, R.id.tv_code_login, R.id.tv_forget_password, R.id.tv_register, R.id.ll_weixin, R.id.ll_qq, R.id.ll_weibo, R.id.ll_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_logout:
                break;
            case R.id.area_code:
                break;
            case R.id.phone_close:
                break;
            case R.id.tv_code://发送验证码
                mPresenter.sendVerificationCode(etPhone.getText().toString());
                sendSuccess();
                break;
            case R.id.tv_code_login:
//                new DefaultUriRequest(this, RouterCons.WEB_VIEW_INFO)
//                        .putExtra(Constant.URL, "file:///android_asset/apps/web/html/service.html")
//                        .putExtra(WebViewContainerActivity.TITLE, getResources().getString(R.string.user_argument))
//                        .start();
                break;
            case R.id.tv_forget_password:
                break;
            case R.id.tv_register:
                break;
            case R.id.ll_weixin:
                break;
            case R.id.ll_qq:
                break;
            case R.id.ll_weibo:
                break;
            case R.id.ll_more:
                break;
        }
    }

    @Override
    public void sendSuccess() {
        new DefaultUriRequest(this, RouterCons.CREATE_GET_CODE)
                .putExtra(Constant.EXTRA_AREA_CODE, areaCode.getText().toString().trim())
                .putExtra(Constant.EXTRA_PHONE,etPhone.getText().toString())
                .start();
    }

    @Override
    public void changeBtnStatus() {

    }
}
