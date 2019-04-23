package com.business.electr.clothes.ui.activity.mine;

import android.widget.EditText;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.LoginBean;
import com.business.electr.clothes.mvp.presenter.login.LoginPresenter;
import com.business.electr.clothes.mvp.view.login.LoginView;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.view.TimeCounter;

import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.OnClick;

public class ModifyPhoneActivity extends BaseActivity<LoginPresenter> implements LoginView {


    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    private TimeCounter mTimeCounter;//验证码倒计时

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_phone;
    }

    @Override
    protected LoginPresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        initRightTitle(getResources().getString(R.string.modify_phone_code), getResources().getString(R.string.confirm));
        mRightTv.setTextColor(ContextCompat.getColor(this, R.color.color_252631));
    }

    @Override
    public void changeBtnStatus() {
        mTimeCounter = new TimeCounter(60000, 1000, tvCode, R.string.btn_re_send_code);
        mTimeCounter.start();
    }

    @Override
    public void loginSuccess(LoginBean loginBean) {

    }


    @OnClick(R.id.tv_code)
    public void onViewClicked() {
        mPresenter.sendVerificationCode(etPhone.getText().toString(),etCode);
    }
}
