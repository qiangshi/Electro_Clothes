package com.business.electr.clothes.ui.activity.mine;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.DataEvent;
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
import com.business.electr.clothes.utils.DataCheckUtils;
import com.business.electr.clothes.utils.GlidUtils;
import com.business.electr.clothes.utils.SelectImageUtils;
import com.business.electr.clothes.utils.StringUtils;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by zenghaiqiang on 2019/01/24.
 * 描述：修改用户信息
 */
@RouterUri(path = {RouterCons.MODIFY_USER_INFO})
public class ModifyUserInfoActivity extends BaseActivity<ModifyUserInfoPresenter> implements ModifyUserInfoView {

    @BindView(R.id.img_my_head)
    ImageView imgMyHead;//用户头像
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;//用户昵称
    @BindView(R.id.tv_phone_code)
    TextView tvPhoneCode;//手机号
    @BindView(R.id.tv_gender)
    TextView tvGender;//性别
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;//出生日期
    @BindView(R.id.tv_time_bean)
    TextView tvTimeBean;//时间豆
    private String logoUrl;//图片资源

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
        initRightTitle(getResources().getString(R.string.my_info), getResources().getString(R.string.preservation));
        userBean = DataCacheManager.getUserInfo();
//        mPresenter.getUserInfo();
    }


    private int genderPos = 2;

    @OnClick({R.id.tv_right_btn, R.id.lin_my_head, R.id.lin_nick_name, R.id.lin_phone_code, R.id.lin_gender, R.id.lin_birthday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right_btn://保存
                MultipartBody.Part portrait = null;
                if (StringUtils.isNotEmpty(logoUrl)) {
                    File file = new File(logoUrl);
                    RequestBody fileRQ = RequestBody.create(MediaType.parse("image/*"), file);
                    portrait = MultipartBody.Part.createFormData("portrait", file.getName(), fileRQ);
                }
                MultipartBody.Part userId = MultipartBody.Part.createFormData("user_id", String.valueOf(DataCacheManager.getUserInfo().getUserId()));
                MultipartBody.Part token = MultipartBody.Part.createFormData("token", DataCacheManager.getToken());
                MultipartBody.Part nickName = MultipartBody.Part.createFormData("nickname", tvNickName.getText().toString());
                MultipartBody.Part contact = MultipartBody.Part.createFormData("contact", tvPhoneCode.getText().toString());
                MultipartBody.Part gender = MultipartBody.Part.createFormData("gender", String.valueOf(ModifyUserInfoActivity.this.genderPos));
                MultipartBody.Part birthday = MultipartBody.Part.createFormData("birthday", tvBirthday.getText().toString());
                mPresenter.updateUserInfo(DataCacheManager.getUserInfo().getUserId(), 1,100,200, "1993-05-14");
                break;
            case R.id.lin_my_head://点击修改头像
                mPresenter.autoObtainStoragePermission(this);
                break;
            case R.id.lin_nick_name://昵称
                new DefaultUriRequest(this, RouterCons.CREATE_CPY_SET_INFO)
                        .putExtra(RouterCons.TYPE, 5)
                        .activityRequestCode(Constant.REQUEST_CODE_USER_NICK_NAME)
                        .start();
                break;
            case R.id.lin_phone_code://修改手机号
                new DefaultUriRequest(this, RouterCons.CREATE_CPY_SET_INFO)
                        .putExtra(RouterCons.TYPE, 6)
                        .activityRequestCode(Constant.REQUEST_CODE_PHONE)
                        .start();
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
                ToolHelper.selectTime(this, tvBirthday, Constant.DATE_FORMAT_0);
                break;
        }
    }

    @Override
    public void getUserInfoSuccess(UserBean bean) {
        userBean.setPhone(bean.getPhone());
        userBean.setNickName(bean.getNickName());
        // TODO: 2019/4/23 添加用户信息
        DataCacheManager.saveUserInfo(userBean);
        EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_CHANGE_USERINFO, userBean));
        SynchronizationObserver.getInstance().onSynchronizationUpdate(SynchronizationObserver.TYPE_UPDATE_USER_INFO, userBean, SynchronizationObserver.PAGE_FRAGMENT_TYPE_MINE);
        if (!TextUtils.isEmpty(bean.getHeadImgUrl())) {
            imgMyHead.setVisibility(View.VISIBLE);
            GlidUtils.setCircleGrid(this, bean.getHeadImgUrl(), imgMyHead);
        } else {
            imgMyHead.setVisibility(View.GONE);
        }
        tvNickName.setText(bean.getNickName());
        tvPhoneCode.setText(bean.getPhone());
        genderPos = Integer.valueOf(bean.getSex());
        if ("0".equals(bean.getSex())) {
            tvGender.setText("男");
        } else if ("1".equals(bean.getSex())) {
            tvGender.setText("女");
        } else {
            tvGender.setText("未知");
        }
        tvBirthday.setText(bean.getBirthDate());
        tvTimeBean.setText(bean.getAccountRest());
    }

    @Override
    public void updateUserInfoSuccess() {
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(this, requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constant.REQUEST_CODE_USER_NICK_NAME://用户昵称
                    String reason = data.getStringExtra(RouterCons.DATA);
                    tvNickName.setText(reason);
                    break;
                case Constant.REQUEST_CODE_PHONE://用户手机号
                    String mobilePhone = data.getStringExtra(RouterCons.DATA);
                    if (!DataCheckUtils.checkPhone(mobilePhone)) {
                        toastMessage(R.string.msg_phone_num_error);
                        break;
                    }
                    tvPhoneCode.setText(mobilePhone);
                    break;
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPresenter.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void selectPhoto() {
        SelectImageUtils.selectPhoto(this, getString(R.string.takephoto), false, true, 1);
    }

    @Override
    public void onUploadSuccess(String imgUrl) {
        imgMyHead.setVisibility(View.VISIBLE);
        Glide.with(this).load(imgUrl).into(imgMyHead);
        logoUrl = imgUrl;
    }
}
