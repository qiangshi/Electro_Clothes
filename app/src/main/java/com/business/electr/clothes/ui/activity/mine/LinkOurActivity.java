package com.business.electr.clothes.ui.activity.mine;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.mine.LinkOurPresenter;
import com.business.electr.clothes.mvp.view.mine.LinkOurView;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.sankuai.waimai.router.annotation.RouterUri;

import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/01/25.
 * 描述：联系产品经理
 */
@RouterUri(path = {RouterCons.CREATE_LINK_OUR})
public class LinkOurActivity extends BaseActivity<LinkOurPresenter> implements LinkOurView {

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
    private int contactType;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_link_our;
    }

    @Override
    protected LinkOurPresenter getPresenter() {
        return new LinkOurPresenter(this);
    }

    @Override
    protected void initDataAndEvent() {
        initTitle(getResources().getString(R.string.link_our));
    }

    @OnClick({R.id.tv_commit,R.id.ll_weixin, R.id.ll_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                String contact;
                if(contactType == 0){
                    contact = etWeixin.getText().toString();
                }else {
                    contact = etEmail.getText().toString();
                }
                mPresenter.linkOur(etSuggest.getText().toString(),contactType,contact);
                break;
            case R.id.ll_weixin:
                contactType = 0;
                imgWeixin.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_my_ihi));
                imgEmail.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.bg_stroke_e3e3e3_19));
                break;
            case R.id.ll_email:
                contactType = 1;
                imgEmail.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.icon_my_ihi));
                imgWeixin.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.bg_stroke_e3e3e3_19));
                break;
        }
    }

    @Override
    public void linkSuccess() {
        finish();
    }
}
