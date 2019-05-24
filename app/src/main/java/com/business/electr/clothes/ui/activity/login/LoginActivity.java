package com.business.electr.clothes.ui.activity.login;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.business.electr.clothes.App;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.login.LoginPresenter;
import com.business.electr.clothes.mvp.view.login.LoginView;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.ui.fragment.dialog.ThireLoginFragment;
import com.business.electr.clothes.utils.MLog;
import com.business.electr.clothes.utils.SharePreferenceUtil;
import com.business.electr.clothes.utils.StatusBar.StatusBarUtil;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.OnClick;
/**
 * Created by zenghaiqiang on 2019/4/28.
 * 描述：登录
 */
@RouterUri(path = {RouterCons.CREATE_LOGIN})
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.et_phone)
    EditText etPhone; //手机验证码登陆手机输入框
    @BindView(R.id.phone_close)
    ImageView phoneClose;
    @BindView(R.id.area_code)
    TextView areaCode;//手机验证码登陆的手机区号

    @BindView(R.id.et_phone_pass)
    EditText etPhonePass;//账号密码登陆的手机号输入框
    @BindView(R.id.img_phone_close)
    ImageView imgPhoneClose;

    @BindView(R.id.et_password)
    EditText etPassword;//密码输入框
    @BindView(R.id.img_password_close)
    ImageView imgPasswordClose;

    @BindView(R.id.tv_code_login)
    TextView tvCodeLogin;
    @BindView(R.id.ll_phone_login)
    LinearLayout llPhoneLogin;
    @BindView(R.id.ll_code_password)
    LinearLayout llCodePassword;

    @BindView(R.id.tv_code)
    TextView tvCode;//登陆或者获取验证码

    private ThireLoginFragment thireLoginFragment;
    private boolean isCodePassword = true;//是否是账号密码登陆； 默认账号密码登陆
    private boolean isOpen = false;//密码是否可见
    private int type;

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
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        if(SharePreferenceUtil.getBoolean(Constant.IS_REGISTER,false)){
            tvCodeLogin.setText(getResources().getString(R.string.phone_code_login));
        } else tvCodeLogin.setText(getResources().getString(R.string.phone_code_login_or_register));
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1) {//如果长度大于等于1,显示删除图片
                    phoneClose.setVisibility(View.VISIBLE);
                } else {//否则隐藏
                    phoneClose.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        etPhonePass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1) {//如果长度大于等于1,显示删除图片
                    imgPhoneClose.setVisibility(View.VISIBLE);
                } else {//否则隐藏
                    imgPhoneClose.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @Override
    public void sendSuccess(boolean isNewUser) {
        if(isNewUser) type = 0;
        new DefaultUriRequest(this, RouterCons.CREATE_GET_CODE)
                .putExtra(Constant.EXTRA_AREA_CODE, areaCode.getText().toString().trim())
                .putExtra(Constant.EXTRA_PHONE, etPhone.getText().toString())
                .putExtra(Constant.TYPE,type)
                .putExtra(Constant.EXTRA_IS_NEW_USER,isNewUser)
                .start();
    }

    @Override
    public void loginSuccess(UserBean userBean) {
        saveLoginInfo(userBean);
        new DefaultUriRequest(this,RouterCons.CREATE_PATTERN)
                .start();
        finish();
    }

    /**
     * 保存用户信息
     */
    private void saveLoginInfo(UserBean userBean) {
        MLog.e("====zhq====>111<"+userBean.getToken());
        DataCacheManager.saveToken(userBean.getToken());
        DataCacheManager.saveUserInfo(userBean);
        SharePreferenceUtil.putBoolean(Constant.IS_LOGIN, true);
    }

    @OnClick({R.id.img_logout, R.id.area_code, R.id.tv_code_area, R.id.phone_close, R.id.img_phone_close, R.id.img_password_close, R.id.tv_code, R.id.tv_code_login, R.id.tv_forget_password, R.id.ll_weixin, R.id.ll_qq, R.id.ll_weibo, R.id.ll_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_logout:
                App.getApp().quitApp();
                break;
            case R.id.tv_code_area://区号选择
            case R.id.area_code:
                break;
            case R.id.phone_close:
                etPhone.setText("");
                break;
            case R.id.img_phone_close:
                etPhonePass.setText("");
                break;
            case R.id.img_password_close:
                if (!isOpen) {
                    isOpen = true;
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    etPassword.setSelection(etPassword.getText().length());
                    imgPasswordClose.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_login_open_eye));
                } else {
                    isOpen = false;
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    etPassword.setSelection(etPassword.getText().length());
                    imgPasswordClose.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_login_close_eye));
                }
                break;
            case R.id.tv_code://发送验证码
                if (isCodePassword) {//登陆
                    mPresenter.requestLogin(etPhonePass.getText().toString(),etPassword.getText().toString(),true);
                } else {//发送验证码
                    mPresenter.sendVerificationCode(etPhone.getText().toString());
                }

                break;
            case R.id.tv_code_login:
                if (isCodePassword) {//账号密码登录
                    type =1;
                    isCodePassword = false;
                    llPhoneLogin.setVisibility(View.VISIBLE);
                    llCodePassword.setVisibility(View.GONE);
                    tvCodeLogin.setText(getResources().getString(R.string.code_password_login));
                    tvCode.setText(getResources().getString(R.string.get_phone_code));
                } else {//手机验证码登陆
                    type =0;
                    isCodePassword = true;
                    llCodePassword.setVisibility(View.VISIBLE);
                    llPhoneLogin.setVisibility(View.GONE);
                    if(SharePreferenceUtil.getBoolean(Constant.IS_REGISTER,false)){
                        tvCodeLogin.setText(getResources().getString(R.string.phone_code_login));
                    } else tvCodeLogin.setText(getResources().getString(R.string.phone_code_login_or_register));
                    tvCode.setText(getResources().getString(R.string.login));
                }
                break;
            case R.id.tv_forget_password://忘记密码
                type = 2;
                mPresenter.sendVerificationCode(etPhone.getText().toString());
                break;
            case R.id.ll_weixin:
                break;
            case R.id.ll_qq:
                break;
            case R.id.ll_weibo:
                break;
            case R.id.ll_more:
                showThireFragment();
                break;
        }
    }


    private void showThireFragment() {
        if (thireLoginFragment == null) {
            thireLoginFragment = new ThireLoginFragment();
        }
        if (!thireLoginFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().add(thireLoginFragment, "thireLoginFragment").commitAllowingStateLoss();
        }
    }


    private void hideThireFragment() {
        if (thireLoginFragment != null && thireLoginFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().hide(thireLoginFragment).commitAllowingStateLoss();
        }
    }

}
