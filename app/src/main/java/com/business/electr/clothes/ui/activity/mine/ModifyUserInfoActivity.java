package com.business.electr.clothes.ui.activity.mine;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.UserBean;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.helper.ToolHelper;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.mine.ModifyUserInfoPresenter;
import com.business.electr.clothes.mvp.view.mine.ModifyUserInfoView;
import com.business.electr.clothes.observer.SynchronizationObserver;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.ui.fragment.dialog.TypeFilterFragment;
import com.business.electr.clothes.utils.MLog;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/01/24.
 * 描述：修改用户信息
 */
@RouterUri(path = {RouterCons.MODIFY_USER_INFO})
public class ModifyUserInfoActivity extends BaseActivity<ModifyUserInfoPresenter> implements ModifyUserInfoView {


    @BindView(R.id.tv_nick_name)
    TextView tvNickName;//用户昵称
    @BindView(R.id.tv_gender)
    TextView tvGender;//性别
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;//出生日期
    @BindView(R.id.et_name)
    EditText etName;//用户姓名


    @BindView(R.id.et_height)
    EditText etHeight;//身高
    @BindView(R.id.et_weight)
    EditText etWeight;//体重


    private UserBean userBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_user_info;
    }

    @Override
    protected ModifyUserInfoPresenter getPresenter() {
        return new ModifyUserInfoPresenter(this);
    }

    @Override
    protected void initDataAndEvent() {
        initRightTitle(getResources().getString(R.string.my_info), getResources().getString(R.string.modify));
        userBean = DataCacheManager.getUserInfo();
        mPresenter.getUserInfo();
    }


    private int genderPos = 2;

    @OnClick({R.id.tv_right_btn, R.id.lin_gender, R.id.lin_birthday, R.id.lin_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right_btn://保存
                mPresenter.updateUserInfo(genderPos, etHeight.getText().toString(), etWeight.getText().toString(), tvBirthday.getText().toString());
                break;
            case R.id.lin_gender://性别
                TypeFilterFragment.showFragment(getSupportFragmentManager(), Arrays.asList(getResources().getStringArray(R.array.gender_sex)), genderPos,
                        new TypeFilterFragment.TypeChangeListener() {
                            @Override
                            public void onTypeChange(int pos) {
                                ModifyUserInfoActivity.this.genderPos = pos;
                                tvGender.setText(Arrays.asList(getResources().getStringArray(R.array.gender_sex)).get(pos));
                            }
                        });
                break;
            case R.id.lin_birthday://出生日期
                ToolHelper.selectTime(this, tvBirthday, Constant.DATE_FORMAT_6);
                break;
            case R.id.lin_password://修改密码
                new DefaultUriRequest(this,RouterCons.CREATE_RE_SET_PASSWORD)
                        .start();
                break;
        }
    }

    @Override
    public void getUserInfoSuccess(UserBean bean) {
        userBean.setBirthDate(bean.getBirthDate());
        userBean.setHeight(bean.getHeight());
        userBean.setWeight(bean.getWeight());
        userBean.setUserName(bean.getUserName());
        userBean.setNickName(bean.getNickName());
        userBean.setSex(bean.getSex());
        DataCacheManager.saveUserInfo(userBean);
        SynchronizationObserver.getInstance().onSynchronizationUpdate(SynchronizationObserver.TYPE_UPDATE_USER_INFO, userBean, SynchronizationObserver.PAGE_FRAGMENT_TYPE_MINE);
        tvNickName.setText(bean.getPhone());
        etName.setText(bean.getUserName());
        genderPos = Integer.valueOf(bean.getSex());
        if (0 == bean.getSex()) {
            tvGender.setText("男");
        } else if (1 == bean.getSex()) {
            tvGender.setText("女");
        } else {
            tvGender.setText("未知");
        }
        tvBirthday.setText(bean.getBirthDate());
        etHeight.setText(bean.getHeight() +"");
        etWeight.setText(bean.getWeight() +"");
    }

    @Override
    public void updateUserInfoSuccess() {
        finish();
    }

}
