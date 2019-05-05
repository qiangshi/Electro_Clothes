package com.business.electr.clothes.ui.activity.mine;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.mvp.presenter.mine.ModifyPasswordPresenter;
import com.business.electr.clothes.mvp.view.mine.ModifyPasswordView;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/4/28.
 * 描述：设置用户信息
 */
@RouterUri(path = {RouterCons.CREATE_SET_PASSWORD})
public class ModifyPasswordActivity extends BaseActivity<ModifyPasswordPresenter> implements ModifyPasswordView {


    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_again_password)
    EditText etAgainPassword;
    @BindView(R.id.img_new_pass)
    ImageView imgNewPass;
    @BindView(R.id.img_again_pass)
    ImageView imgAgainPass;

    private String phone;
    private String vertify;
    private int type;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_password;
    }

    @Override
    protected ModifyPasswordPresenter getPresenter() {
        return new ModifyPasswordPresenter(this);
    }

    @Override
    protected void initDataAndEvent() {
        phone = getIntent().getStringExtra(Constant.EXTRA_PHONE);
        vertify = getIntent().getStringExtra(Constant.EXTRA_CODE);
        type = getIntent().getIntExtra(Constant.TYPE,0);
        etNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1) {//如果长度大于等于1,显示删除图片
                    imgNewPass.setVisibility(View.VISIBLE);
                } else {//否则隐藏
                    imgNewPass.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etAgainPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1) {//如果长度大于等于1,显示删除图片
                    imgAgainPass.setVisibility(View.VISIBLE);
                } else {//否则隐藏
                    imgAgainPass.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @OnClick({R.id.img_back, R.id.tv_confirm,R.id.img_new_pass, R.id.img_again_pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_confirm:
                if(type == 0){
                    mPresenter.registerUser(phone, etNewPassword.getText().toString(), etAgainPassword.getText().toString(), vertify);
                }
                break;
            case R.id.img_new_pass:
                etNewPassword.setText("");
                break;
            case R.id.img_again_pass:
                etAgainPassword.setText("");
                break;
        }
    }

    @Override
    public void registerSuccess(UserBean userBean) {
        new DefaultUriRequest(this, RouterCons.CREATE_PERFECT_INFO)
                .start();
    }

    @Override
    public void loginSuccess(UserBean userBean) {
        new DefaultUriRequest(this,RouterCons.CREATE_MAIN)
                .start();
        finish();
    }


}
