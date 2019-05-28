package com.business.electr.clothes.ui.activity.task;


import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.BasePresenter;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.ui.fragment.dialog.ThireLoginFragment;
import com.business.electr.clothes.utils.CommonUtils;
import com.business.electr.clothes.utils.MLog;
import com.business.electr.clothes.utils.StatusBar.StatusBarUtil;
import com.sankuai.waimai.router.annotation.RouterUri;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

@RouterUri(path = {RouterCons.CREATE_SHARE_TASK})
public class ShareActivity extends BaseActivity {

    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_month_year)
    TextView tvMonthYear;
    @BindView(R.id.rel_time)
    RelativeLayout relTime;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_2)
    TextView tv2;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_finish_tag)
    TextView tvFinishTag;
    @BindView(R.id.tv_sleep)
    TextView tvSleep;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_elect)
    TextView tvElect;
    @BindView(R.id.tv_jump)
    TextView tvJump;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_ihi)
    TextView tvIhi;
    @BindView(R.id.rel_code)
    RelativeLayout relCode;
    @BindView(R.id.rel_img)
    RelativeLayout relImg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_close)
    ImageView imgClose;
    @BindView(R.id.img_share)
    ImageView imgShare;
    @BindView(R.id.rel_share)
    RelativeLayout relShare;
    @BindView(R.id.rel_share_three)
    RelativeLayout relShareThree;
    @BindView(R.id.rel_content)
    RelativeLayout relContent;
    private Bitmap bitmap;
    private int height;
    private RelativeLayout.LayoutParams params;
    private ValueAnimator startAnimation, endAnimation;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            height = CommonUtils.dp2px(ShareActivity.this, 123);
            View dView = getWindow().getDecorView();
            dView.setDrawingCacheEnabled(true);
            dView.buildDrawingCache();
            bitmap = Bitmap.createBitmap(dView.getDrawingCache());
            if (bitmap != null) {
                MLog.d("====zhq====", "生成图片成功");
                if (!startAnimation.isRunning()) {
                    startAnimation.start();
                    imgShare.setImageBitmap(bitmap);
                    relShare.setVisibility(View.VISIBLE);
                    relContent.setBackgroundColor(getResources().getColor(R.color.color_f8f8f8));
                }
            }
        }};


        @Override
        protected int getLayoutId() {
            return R.layout.activity_share;
        }

        @Override
        protected BasePresenter getPresenter() {
            return null;
        }

        @Override
        protected void initDataAndEvent() {
            StatusBarUtil.setRootViewFitsSystemWindows(this, false);
            handler.sendEmptyMessageDelayed(1, 2000);
            params = (RelativeLayout.LayoutParams) relShareThree.getLayoutParams();
            initAnimation();
        }


        /**
         * 初始化动画
         */
        private void initAnimation() {
            startAnimation = ValueAnimator.ofInt(100, 0);
            startAnimation.addUpdateListener(updateListener);
            startAnimation.setDuration(500);
        }

        /**
         * 动画监听
         */
        private ValueAnimator.AnimatorUpdateListener updateListener = new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                double scale = div((int) animation.getAnimatedValue(), 100, 2);
                int scaleY = (int) (scale * height);
                MLog.e(scale + "====zhq====>scaleY<" + scaleY);
                params.bottomMargin = -scaleY;
                relShareThree.setLayoutParams(params);
                relShare.setAlpha((float) (1 -scale));
                relImg.setAlpha((float) scale);
            }
        };

        /**
         * @param v1
         * @param v2
         * @param scale
         * @return
         */
        public double div(double v1, double v2, int scale) {
            if (scale < 0)
                throw new IllegalArgumentException("The scale must be a positive integer or zero");
            BigDecimal b1 = new BigDecimal(Double.toString(v1));
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        }

        private ThireLoginFragment thireLoginFragment;

        /**
         * 显示第三方登录平台
         */
        private void showThreeFragment() {
            if (thireLoginFragment == null) {
                thireLoginFragment = new ThireLoginFragment();
            }
            if (!thireLoginFragment.isVisible()) {
                getSupportFragmentManager().beginTransaction().add(thireLoginFragment, "thireLoginFragment").commitAllowingStateLoss();
            }
        }


        @OnClick({R.id.img_close, R.id.ll_weixin, R.id.ll_qq, R.id.ll_weibo, R.id.ll_more})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.img_close:
                    finish();
                    break;
                case R.id.ll_weixin:
                    Toast.makeText(this,"微信",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ll_qq:
                    Toast.makeText(this,"朋友圈",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ll_weibo:
                    Toast.makeText(this,"微博",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ll_more:
                    showThreeFragment();
                    break;
            }
        }
    }
