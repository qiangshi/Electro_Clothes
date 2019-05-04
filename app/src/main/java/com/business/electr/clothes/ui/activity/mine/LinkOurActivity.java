package com.business.electr.clothes.ui.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.sankuai.waimai.router.annotation.RouterUri;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/01/25.
 * 描述：联系产品经理
 */
@RouterUri(path = {RouterCons.CREATE_LINK_OUR})
public class LinkOurActivity extends BaseActivity {

    @BindView(R.id.et_suggest)
    EditText etSuggest;
    @BindView(R.id.img_weixin)
    ImageView imgWeixin;
    @BindView(R.id.et_weixin)
    EditText etWeixin;
    @BindView(R.id.img_email)
    ImageView imgEmail;
    @BindView(R.id.et_email)
    EditText etEmail;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_link_our;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        initTitle(getResources().getString(R.string.link_our));
    }

    @OnClick({R.id.tv_commit,R.id.ll_weixin, R.id.ll_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                break;
            case R.id.ll_weixin:
                imgWeixin.setVisibility(View.VISIBLE);
                imgEmail.setVisibility(View.INVISIBLE);
                break;
            case R.id.ll_email:
                imgWeixin.setVisibility(View.INVISIBLE);
                imgEmail.setVisibility(View.VISIBLE);
                break;
        }
    }
}
