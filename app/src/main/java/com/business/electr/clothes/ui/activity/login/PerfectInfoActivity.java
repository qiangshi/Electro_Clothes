package com.business.electr.clothes.ui.activity.login;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.helper.ToolHelper;
import com.business.electr.clothes.mvp.presenter.mine.ModifyUserInfoPresenter;
import com.business.electr.clothes.mvp.view.mine.ModifyUserInfoView;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.ui.fragment.dialog.TypeFilterFragment;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

@RouterUri(path = {RouterCons.CREATE_PERFECT_INFO})
public class PerfectInfoActivity extends BaseActivity<ModifyUserInfoPresenter> implements ModifyUserInfoView {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.et_height)
    EditText etHeight;
    @BindView(R.id.et_weight)
    EditText etWeight;
    private int genderPos;//性别

    @Override
    protected int getLayoutId() {
        return R.layout.activity_perfect_info;
    }

    @Override
    protected ModifyUserInfoPresenter getPresenter() {
        return new ModifyUserInfoPresenter(this);
    }

    @Override
    protected void initDataAndEvent() {
        initRightTitle(getResources().getString(R.string.user_info), getResources().getString(R.string.register));
        mBackBtn.setVisibility(View.GONE);
    }

    @OnClick({R.id.tv_right_btn, R.id.lin_gender, R.id.lin_birthday, R.id.lin_height, R.id.lin_weight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right_btn:
                mPresenter.updateUserInfo(genderPos,etHeight.getText().toString(),etWeight.getText().toString(),tvBirthday.getText().toString());
                break;
            case R.id.lin_gender:
                TypeFilterFragment.showFragment(getSupportFragmentManager(), Arrays.asList(getResources().getStringArray(R.array.gender_sex)), genderPos,
                        new TypeFilterFragment.TypeChangeListener() {
                            @Override
                            public void onTypeChange(int pos) {
                                PerfectInfoActivity.this.genderPos = pos;
                                tvGender.setText(Arrays.asList(getResources().getStringArray(R.array.gender_sex)).get(pos));
                            }
                        });
                break;
            case R.id.lin_birthday:
                ToolHelper.selectTime(this, tvBirthday, Constant.DATE_FORMAT_6);
                break;
        }
    }

    @Override
    public void getUserInfoSuccess(UserBean bean) {

    }

    @Override
    public void updateUserInfoSuccess() {
        new DefaultUriRequest(this,RouterCons.CREATE_MAIN)
                .start();
    }
}
