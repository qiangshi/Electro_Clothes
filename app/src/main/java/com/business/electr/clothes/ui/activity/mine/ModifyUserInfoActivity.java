package com.business.electr.clothes.ui.activity.mine;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
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
import com.business.electr.clothes.ui.fragment.dialog.TypeGraderFragment;
import com.business.electr.clothes.utils.GlidUtils;
import com.business.electr.clothes.utils.SelectImageUtils;
import com.business.electr.clothes.utils.SharePreferenceUtil;
import com.business.electr.clothes.utils.StringUtils;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
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

    @BindView(R.id.img_upload_pic)
    ImageView imgUploadPic;
    @BindView(R.id.tv_nick_name)
    TextView tvNickName;//用户昵称
    @BindView(R.id.tv_gender)
    TextView tvGender;//性别
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;//出生日期
    @BindView(R.id.et_name)
    EditText etName;//用户姓名


    @BindView(R.id.tv_height)
    TextView tvHeight;//身高
    @BindView(R.id.tv_weight)
    TextView tvWeight;//体重

    private List<String> heights;
    private List<String> weights;
    private UserBean userBean;
    private String logoUrl;

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
        initData();
    }


    private int genderPos = 2;
    private int heightPos = 160;
    private int weightPos = 50;

    @OnClick({R.id.img_upload_pic,R.id.tv_right_btn, R.id.lin_gender, R.id.lin_birthday, R.id.lin_password,R.id.lin_height,R.id.lin_weight,R.id.tv_log_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_right_btn://保存
                MultipartBody.Part portrait = null;
                if (StringUtils.isNotEmpty(logoUrl)) {
                    File file = new File(logoUrl);
                    RequestBody fileRQ = RequestBody.create(MediaType.parse("file/*"), file);
                    portrait = MultipartBody.Part.createFormData("content", file.getName(), fileRQ);
                }
                mPresenter.updateUserInfo(portrait,etName.getText().toString(),genderPos, tvHeight.getText().toString(), tvWeight.getText().toString(), tvBirthday.getText().toString());
                break;
            case R.id.img_upload_pic://上传图片
                mPresenter.autoObtainStoragePermission(this);
                break;
            case R.id.lin_gender://性别
                TypeGraderFragment.showFragment(getSupportFragmentManager(), Arrays.asList(getResources().getStringArray(R.array.gender_sex)), genderPos,
                        new TypeGraderFragment.TypeChangeListener() {
                            @Override
                            public void onTypeChange(int pos) {
                                ModifyUserInfoActivity.this.genderPos = pos+1;
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
            case R.id.lin_height:
                TypeFilterFragment.showFragment(getSupportFragmentManager(), heights, heightPos,
                        new TypeFilterFragment.TypeChangeListener() {
                            @Override
                            public void onTypeChange(int pos) {
                                ModifyUserInfoActivity.this.heightPos = pos;
                                tvHeight.setText(heights.get(pos));
                            }
                        });
                break;
            case R.id.lin_weight:
                TypeFilterFragment.showFragment(getSupportFragmentManager(), weights, weightPos,
                        new TypeFilterFragment.TypeChangeListener() {
                            @Override
                            public void onTypeChange(int pos) {
                                ModifyUserInfoActivity.this.weightPos = pos;
                                tvWeight.setText(weights.get(pos));
                            }
                        });
                break;
            case R.id.tv_log_out://退出登录
                DataCacheManager.saveUserInfo(null);
                DataCacheManager.saveToken("");
                SharePreferenceUtil.putBoolean(Constant.IS_LOGIN, false);
                SharePreferenceUtil.putInt(Constant.CLOCK_TYPE,0);
                EventBus.getDefault().post(new DataEvent(DataEvent.TYPE_LOGIN, null));
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
        userBean.setHeadImgUrl(bean.getHeadImgUrl());
        DataCacheManager.saveUserInfo(userBean);
        SynchronizationObserver.getInstance().onSynchronizationUpdate(SynchronizationObserver.TYPE_UPDATE_USER_INFO, userBean, SynchronizationObserver.PAGE_FRAGMENT_TYPE_MINE);
        GlidUtils.setCircleGrid(this,bean.getHeadImgUrl(),imgUploadPic);
        tvNickName.setText(bean.getNickName());
        etName.setText(bean.getUserName());
        genderPos = Integer.valueOf(bean.getSex());
        heightPos = bean.getHeight() -10;
        weightPos = bean.getWeight() - 10;
        if (1 == bean.getSex()) {
            tvGender.setText("男");
        } else if (2 == bean.getSex()) {
            tvGender.setText("女");
        } else {
            tvGender.setText("未知");
        }
        tvBirthday.setText(bean.getBirthDate().substring(0,10));
        tvHeight.setText(bean.getHeight() +"cm");
        tvWeight.setText(bean.getWeight() +"kg");
    }

    @Override
    public void updateUserInfoSuccess() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(this, requestCode, resultCode, data);
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
        imgUploadPic.setVisibility(View.VISIBLE);
        GlidUtils.setCircleGrid(this,imgUrl,imgUploadPic);
        logoUrl = imgUrl;
    }


    /**
     * 初始化身高体重数据
     */
    private void initData(){
        heights = new ArrayList<>();
        weights = new ArrayList<>();
        for (int i = 10; i < 259; i++) {
            heights.add(i+"cm");
        }
        for (int i = 10; i < 200; i++) {
            weights.add(i+"kg");
        }
    }

}
