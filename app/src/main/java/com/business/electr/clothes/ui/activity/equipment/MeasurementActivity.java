package com.business.electr.clothes.ui.activity.equipment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.utils.StatusBar.StatusBarUtil;
import com.business.electr.clothes.view.QuantityView;
import com.sankuai.waimai.router.annotation.RouterUri;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import java.util.Random;

import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/5/2.
 * 描述：精测界面
 */
@RouterUri(path = {RouterCons.CREATE_MEASUREMENT})
public class MeasurementActivity extends BaseActivity {


    @BindView(R.id.qv_time)
    QuantityView qvTime;
    @BindView(R.id.img_voice)
    ImageView imgVoice;
    @BindView(R.id.rel_hint_60)
    RelativeLayout relHint60;
    @BindView(R.id.img_down_num)
    ImageView imgDownNum;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.tv_body)
    TextView tvBody;
    @BindView(R.id.img_start)
    ImageView imgStart;

    private int currTime = 60;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    relHint60.setVisibility(View.GONE);
                    imgStart.setVisibility(View.GONE);
                    tvBody.setVisibility(View.GONE);
                    imgDownNum.setVisibility(View.VISIBLE);
                    imgDownNum.setImageDrawable(ContextCompat.getDrawable(MeasurementActivity.this, R.drawable.icon_jingce_3));
                    handler.sendEmptyMessageDelayed(1, 1000);
                    break;
                case 1:
                    imgDownNum.setImageDrawable(ContextCompat.getDrawable(MeasurementActivity.this, R.drawable.icon_jingce_2));
                    handler.sendEmptyMessageDelayed(2, 1000);
                    break;
                case 2:
                    imgDownNum.setImageDrawable(ContextCompat.getDrawable(MeasurementActivity.this, R.drawable.icon_jingce_1));
                    handler.sendEmptyMessageDelayed(3, 1000);
                    break;
                case 3:
                    imgDownNum.setVisibility(View.GONE);
                    qvTime.setVisibility(View.VISIBLE);
                    imgVoice.setVisibility(View.VISIBLE);
                    tvEnd.setVisibility(View.VISIBLE);
                    tvBody.setVisibility(View.VISIBLE);
                    qvTime.setProcess(currTime);
                    handler.sendEmptyMessageDelayed(4, 1000);
                    break;
                case 4:
                    if (currTime > 0) {
                        currTime--;
                        qvTime.setProcess(currTime);
                        handler.sendEmptyMessageDelayed(4, 1000);
                    }
                    break;
            }

        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_measurement;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initDataAndEvent() {
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, false);
        qvTime.setIsDown(true);
    }


    @OnClick({R.id.img_close, R.id.img_voice, R.id.img_start, R.id.tv_end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_close:
                finish();
                break;
            case R.id.img_voice:
                break;
            case R.id.img_start:
                handler.sendEmptyMessageDelayed(0, 1000);
                break;
            case R.id.tv_end:
                if (currTime <= 0) {
                    new DefaultUriRequest(this, RouterCons.CREATE_MEASURE_RESULT)
                            .start();
                    finish();
                }
                break;
        }
    }
}
