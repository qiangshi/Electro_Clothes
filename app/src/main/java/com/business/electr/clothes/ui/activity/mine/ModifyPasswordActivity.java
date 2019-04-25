package com.business.electr.clothes.ui.activity.mine;

import android.view.View;
import android.widget.EditText;
import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.mine.ModifyPasswordPresenter;
import com.business.electr.clothes.mvp.view.mine.ModifyPasswordView;
import com.business.electr.clothes.ui.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ModifyPasswordActivity extends BaseActivity<ModifyPasswordPresenter> implements ModifyPasswordView {


    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_again_password)
    EditText etAgainPassword;

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

    }


    @OnClick({R.id.img_back, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_confirm:
                break;
        }
    }
}
