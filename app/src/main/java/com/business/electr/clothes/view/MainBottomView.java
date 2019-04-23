package com.business.electr.clothes.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.business.electr.clothes.R;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/1/11.
 * 主页面——底部按钮VIEW
 */
public class MainBottomView extends LinearLayout {

    @BindView(R.id.img_self)
    ImageView imgSelf;
    @BindView(R.id.tv_self)
    TextView tvSelf;
    @BindView(R.id.ll_self)
    LinearLayout llSelf;
    @BindView(R.id.img_red_top_self)
    ImageView imgRedTopSelf;
    @BindView(R.id.rl_self)
    RelativeLayout rlSelf;
    @BindView(R.id.img_macroscopic)
    ImageView imgMacroscopic;
    @BindView(R.id.tv_macroscopic)
    TextView tvMacroscopic;
    @BindView(R.id.ll_macroscopic)
    LinearLayout llMacroscopic;
    @BindView(R.id.img_red_top_macroscopic)
    ImageView imgRedTopMacroscopic;
    @BindView(R.id.rl_macroscopic)
    RelativeLayout rlMacroscopic;
    @BindView(R.id.img_mine)
    ImageView imgMine;
    @BindView(R.id.tv_mine)
    TextView tvMine;
    @BindView(R.id.ll_mine)
    LinearLayout llMine;
    @BindView(R.id.img_red_top_mine)
    ImageView imgRedTopMine;
    @BindView(R.id.rl_mine)
    RelativeLayout rlMine;
    @BindView(R.id.bottom_bar_layout)
    LinearLayout bottomBarLayout;

    private Context context;

    public MainBottomView(Context context) {
        this(context, null);
    }

    public MainBottomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainBottomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_main, this);
        ButterKnife.bind(this,view);
    }

    public void switchBottom(int homedress) {
        switch (homedress) {
            case 0:
                tvSelf.setTextColor(getResources().getColor(R.color.color_4C84FF));
                tvMacroscopic.setTextColor(getResources().getColor(R.color.color_8D92A3));
                tvMine.setTextColor(getResources().getColor(R.color.color_8D92A3));
                imgSelf.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_selection_iconxhdpi));
                imgMacroscopic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_unselection_iconxhdpi));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_me_unselection_iconxhdpi));
                if (homeBottomClick != null) {
                    homeBottomClick.selfClick();
                }
                break;
            case 1:
                tvMacroscopic.setTextColor(getResources().getColor(R.color.color_4C84FF));
                tvSelf.setTextColor(getResources().getColor(R.color.color_8D92A3));
                tvMine.setTextColor(getResources().getColor(R.color.color_8D92A3));
                imgMacroscopic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_selection_iconxhdpi));
                imgSelf.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_unselection_iconxhdpi));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_me_unselection_iconxhdpi));
                if (homeBottomClick != null) {
                    homeBottomClick.macroscopicClick();
                }
                break;
            case 2:
                tvMine.setTextColor(getResources().getColor(R.color.color_4C84FF));
                tvSelf.setTextColor(getResources().getColor(R.color.color_8D92A3));
                tvMacroscopic.setTextColor(getResources().getColor(R.color.color_8D92A3));
                imgMacroscopic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_unselection_iconxhdpi));
                imgSelf.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_unselection_iconxhdpi));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_me_selection_iconxhdpi));
                if (homeBottomClick != null) {
                    homeBottomClick.mineClick();
                }
                break;
        }
    }

    public void showSelfDot(boolean isVisible) {
        if (isVisible) {
            imgRedTopSelf.setVisibility(VISIBLE);
        } else {
            imgRedTopSelf.setVisibility(GONE);
        }
    }



    private HomeBottomClick homeBottomClick;

    public void setHomeBottomClick(HomeBottomClick homeBottomClick) {
        this.homeBottomClick = homeBottomClick;
    }

    @OnClick({R.id.rl_self, R.id.rl_macroscopic, R.id.rl_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_self:
                tvSelf.setTextColor(getResources().getColor(R.color.color_4C84FF));
                tvMacroscopic.setTextColor(getResources().getColor(R.color.color_8D92A3));
                tvMine.setTextColor(getResources().getColor(R.color.color_8D92A3));
                imgSelf.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_selection_iconxhdpi));
                imgMacroscopic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_unselection_iconxhdpi));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_me_unselection_iconxhdpi));
                if (homeBottomClick != null) {
                    homeBottomClick.selfClick();
                }
                break;
            case R.id.rl_macroscopic:
                tvMacroscopic.setTextColor(getResources().getColor(R.color.color_4C84FF));
                tvSelf.setTextColor(getResources().getColor(R.color.color_8D92A3));
                tvMine.setTextColor(getResources().getColor(R.color.color_8D92A3));
                imgMacroscopic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_selection_iconxhdpi));
                imgSelf.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_unselection_iconxhdpi));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_me_unselection_iconxhdpi));
                if (homeBottomClick != null) {
                    homeBottomClick.macroscopicClick();
                }
                break;
            case R.id.rl_mine:
                tvMine.setTextColor(getResources().getColor(R.color.color_4C84FF));
                tvSelf.setTextColor(getResources().getColor(R.color.color_8D92A3));
                tvMacroscopic.setTextColor(getResources().getColor(R.color.color_8D92A3));
                imgMacroscopic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_unselection_iconxhdpi));
                imgSelf.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_news_unselection_iconxhdpi));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.label_me_selection_iconxhdpi));
                if (homeBottomClick != null) {
                    homeBottomClick.mineClick();
                }
                break;
        }
    }

    public interface HomeBottomClick {

        void selfClick();

        void macroscopicClick();

        void mineClick();
    }
}
