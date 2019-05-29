package com.business.electr.clothes.ui.activity.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.mvp.presenter.login.LoginPresenter;
import com.business.electr.clothes.mvp.view.login.LoginView;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/4/28.
 * 描述：注册
 */
@RouterUri(path = {RouterCons.CREATE_REGISTER})
public class RegisterActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.area_code)
    TextView areaCode;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.phone_close)
    ImageView phoneClose;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initDataAndEvent() {
        initTitle("注册");
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
    }

    @OnClick({R.id.tv_right_btn, R.id.area_code, R.id.phone_close, R.id.tv_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.area_code:
                break;
            case R.id.phone_close:
                etPhone.setText("");
                break;
            case R.id.tv_code:
                mPresenter.sendVerificationCode(etPhone.getText().toString());
                break;
        }
    }

    @Override
    public void sendSuccess(boolean isNewUser) {
        new DefaultUriRequest(this, RouterCons.CREATE_GET_CODE)
                .putExtra(Constant.EXTRA_AREA_CODE, areaCode.getText().toString().trim())
                .putExtra(Constant.EXTRA_PHONE, etPhone.getText().toString())
                .putExtra(Constant.TYPE, 0)
                .start();
    }

    @Override
    public void loginSuccess(UserBean userBean) {

    }

    @Override
    public void checkPhoneCodeSuccess(String phoneCode) {

    }

}
