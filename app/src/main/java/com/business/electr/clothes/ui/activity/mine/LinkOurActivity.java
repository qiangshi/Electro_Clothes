package com.business.electr.clothes.ui.activity.mine;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.sankuai.waimai.router.annotation.RouterUri;

import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/01/25.
 * 描述：联系我们
 */
@RouterUri(path = {RouterCons.CREATE_LINK_OUR})
public class LinkOurActivity extends BaseActivity {

    protected String[] permissions = {Manifest.permission.CALL_PHONE};
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

    /**
     * 点击事件
     *
     * @param view 对应的控件View
     */
    @OnClick({R.id.tv_phone, R.id.tv_company_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点击拨打电话
            case R.id.tv_phone:
                //申请权限
                checkPermission(permissions, getResources().getString(R.string.please_open_phone_permission), new OnPermissionCheckSuccess() {
                    @Override
                    public void checkSuccess() {
                        //执行打服务电话的操作
                        dialServicePhone();
                    }
                    @Override
                    public void checkFailed() {
                    }
                });
                break;
            //点击发送邮件
            case R.id.tv_company_email:
                sendEmail();
                break;
        }
    }


    /**
     * 拨打客服电话的方法
     */
    public void dialServicePhone() {
        showNormalFragment(getResources().getString(R.string.dial), getResources().getString(R.string.this_service_tel),
                getString(R.string.cancel),
                getString(R.string.commit),
                new View.OnClickListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.tv_commit:
                                Uri parse = Uri.parse("tel:" + getResources().getString(R.string.this_service_tel));
                                Intent intent = new Intent(Intent.ACTION_CALL, parse);
                                startActivity(intent);
                                break;
                        }
                    }
                });
    }


    /**
     * 发送邮件的方法
     */
    public void sendEmail() {
        //获得邮箱地址
        String enterprise_email = getResources().getString(R.string.company_email_code);
        // 必须明确使用mailto前缀来修饰邮件地址
        Uri uri = Uri.parse("mailto:" + enterprise_email);
        //设置Action类型
        Intent intent_email = new Intent(Intent.ACTION_SENDTO, uri);
        //收件人
        intent_email.putExtra(Intent.EXTRA_EMAIL, enterprise_email);
        //邮件的主题
        intent_email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_title));
        //邮件的内容
        intent_email.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body));
        //选择邮件应用的提示
        startActivity(Intent.createChooser(intent_email, getString(R.string.select_email)));
    }
}
