package com.business.electr.clothes.ui.activity.login;

import android.view.View;
import android.widget.TextView;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.login.LoginPresenter;
import com.business.electr.clothes.mvp.view.login.LoginView;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.utils.SharePreferenceUtil;
import com.business.electr.clothes.utils.ToastUtils;
import com.business.electr.clothes.view.PhoneCode;
import com.business.electr.clothes.view.TimeCounter;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import butterknife.BindView;
import butterknife.OnClick;

@RouterUri(path = {RouterCons.CREATE_GET_CODE})
public class GetPhoneCodeActivity extends BaseActivity<LoginPresenter> implements LoginView {


    @BindView(R.id.tv_area_code)
    TextView tvAreaCode;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.phone_code)
    PhoneCode phoneCode;
    @BindView(R.id.down_time)
    TextView downTime;

    private TimeCounter mTimeCounter;
    private String phone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_phone_code;
    }

    @Override
    protected LoginPresenter getPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initDataAndEvent() {
        init();
    }

    private void init() {
        String areaCode = getIntent().getStringExtra(Constant.EXTRA_AREA_CODE);
        phone = getIntent().getStringExtra(Constant.EXTRA_PHONE);
        tvAreaCode.setText("(" + areaCode + ")");
        tvPhone.setText(phone);
        mTimeCounter = new TimeCounter(60000, 1000, downTime, R.string.btn_re_send_code);
        mTimeCounter.start();
        phoneCode.setOnInputListener(new PhoneCode.OnInputListener() {
            @Override
            public void onSucess(String code) {
                // TODO: 2019/4/25 完善个人信息
                ToastUtils.showToast(GetPhoneCodeActivity.this, "输入完成");
                mPresenter.requestLogin(phone,code,true);
                loginSuccess(new UserBean());
            }

            @Override
            public void onInput() {
            }
        });
    }


    @OnClick({R.id.down_time,R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.down_time:
                mPresenter.sendVerificationCode(phone);
                break;
            case R.id.img_back:
                finish();
                break;
        }

    }

    @Override
    public void sendSuccess() {

    }

    @Override
    public void changeBtnStatus() {

    }

    @Override
    public void loginSuccess(UserBean userBean) {
        saveLoginInfo(userBean);
        new DefaultUriRequest(this, RouterCons.CREATE_MAIN)
                .start();
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
}
