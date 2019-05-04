package com.business.electr.clothes.ui.activity.mine;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.mine.ReSetPasswordPresenter;
import com.business.electr.clothes.mvp.view.mine.ReSetPasswordView;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import butterknife.BindView;
import butterknife.OnClick;

@RouterUri(path = {RouterCons.CREATE_RE_SET_PASSWORD})
public class ReSetPasswordActivity extends BaseActivity<ReSetPasswordPresenter> implements ReSetPasswordView {


    @BindView(R.id.tv_nick_name)
    TextView tvNickName;
    @BindView(R.id.et_old_password)
    EditText etOldPassword;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_new_again_password)
    EditText etNewAgainPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_re_set_password;
    }

    @Override
    protected ReSetPasswordPresenter getPresenter() {
        return new ReSetPasswordPresenter(this);
    }

    @Override
    protected void initDataAndEvent() {
        initTitle("重置密码");
        tvNickName.setText(DataCacheManager.getUserInfo().getPhone());
    }


    @OnClick({R.id.tv_forget_password, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forget_password:
                UserBean userBean = DataCacheManager.getUserInfo();
                new DefaultUriRequest(this, RouterCons.CREATE_GET_CODE)
                        .putExtra(Constant.EXTRA_AREA_CODE, "+86")
                        .putExtra(Constant.EXTRA_PHONE, userBean.getPhone())
                        .putExtra(Constant.TYPE, 3)
                        .putExtra(Constant.EXTRA_IS_NEW_USER, false)
                        .start();
                break;
            case R.id.tv_commit:
                mPresenter.updateUserPassword(etOldPassword.getText().toString(),etNewPassword.getText().toString(),etNewAgainPassword.getText().toString());
                break;
        }
    }
}
