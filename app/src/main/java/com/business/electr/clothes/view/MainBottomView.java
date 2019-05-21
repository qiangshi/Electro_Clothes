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
    @BindView(R.id.img_history)
    ImageView imgHistory;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.img_presentation)
    ImageView imgPresentation;
    @BindView(R.id.img_elect)
    ImageView imgElect;
    @BindView(R.id.rel_main_elect)
    RelativeLayout relMainElect;
    @BindView(R.id.tv_presentation)
    TextView tvPresentation;
    @BindView(R.id.img_task)
    ImageView imgTask;
    @BindView(R.id.tv_task)
    TextView tvTask;

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
        ButterKnife.bind(this, view);
    }

    public void switchBottom(int homedress) {
        switch (homedress) {
            case 0:
                tvHistory.setTextColor(getResources().getColor(R.color.color_353535));
                tvPresentation.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvTask.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvMine.setTextColor(getResources().getColor(R.color.color_8c919b));
                imgHistory.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_history_select));
                imgPresentation.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_presen_noselect));
                imgTask.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_task_noselect));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_mine_noselect));
                imgElect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_elect));
                relMainElect.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_main_circlure_noselect));
                if (homeBottomClick != null) {
                    homeBottomClick.historyClick();
                }
                break;
            case 1:
                tvPresentation.setTextColor(getResources().getColor(R.color.color_353535));
                tvHistory.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvTask.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvMine.setTextColor(getResources().getColor(R.color.color_8c919b));
                imgHistory.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_history_noselect));
                imgPresentation.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_presen_select));
                imgTask.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_task_noselect));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_mine_noselect));
                imgElect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_elect));
                relMainElect.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_main_circlure_noselect));
                if (homeBottomClick != null) {
                    homeBottomClick.presentation();
                }
                break;
            case 2:
                tvTask.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvHistory.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvPresentation.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvMine.setTextColor(getResources().getColor(R.color.color_8c919b));
                imgHistory.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_history_noselect));
                imgPresentation.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_presen_noselect));
                imgTask.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_task_noselect));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_mine_noselect));
                imgElect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_elect));
                relMainElect.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_main_circlure_select));
                if (homeBottomClick != null) {
                    homeBottomClick.electClick();
                }
                break;
            case 3:
                tvTask.setTextColor(getResources().getColor(R.color.color_353535));
                tvHistory.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvPresentation.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvMine.setTextColor(getResources().getColor(R.color.color_8c919b));
                imgHistory.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_history_noselect));
                imgPresentation.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_presen_noselect));
                imgTask.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_task_select));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_mine_noselect));
                imgElect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_elect));
                relMainElect.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_main_circlure_noselect));
                if (homeBottomClick != null) {
                    homeBottomClick.taskClick();
                }
                break;
            case 4:
                tvMine.setTextColor(getResources().getColor(R.color.color_353535));
                tvHistory.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvPresentation.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvTask.setTextColor(getResources().getColor(R.color.color_8c919b));
                imgHistory.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_history_noselect));
                imgPresentation.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_presen_noselect));
                imgTask.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_task_noselect));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_mine_select));
                imgElect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_elect));
                relMainElect.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_main_circlure_noselect));
                if (homeBottomClick != null) {
                    homeBottomClick.mineClick();
                }
                break;
        }
    }

    private HomeBottomClick homeBottomClick;

    public void setHomeBottomClick(HomeBottomClick homeBottomClick) {
        this.homeBottomClick = homeBottomClick;
    }

    @OnClick({R.id.rl_history, R.id.rl_presentation, R.id.rel_main_elect, R.id.rl_task, R.id.rl_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_history:
                tvHistory.setTextColor(getResources().getColor(R.color.color_353535));
                tvPresentation.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvTask.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvMine.setTextColor(getResources().getColor(R.color.color_8c919b));
                imgHistory.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_history_select));
                imgPresentation.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_presen_noselect));
                imgTask.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_task_noselect));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_mine_noselect));
                imgElect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_elect));
                relMainElect.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_main_circlure_noselect));
                if (homeBottomClick != null) {
                    homeBottomClick.historyClick();
                }
                break;
            case R.id.rl_presentation:
                tvPresentation.setTextColor(getResources().getColor(R.color.color_353535));
                tvHistory.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvTask.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvMine.setTextColor(getResources().getColor(R.color.color_8c919b));
                imgHistory.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_history_noselect));
                imgPresentation.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_presen_select));
                imgTask.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_task_noselect));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_mine_noselect));
                imgElect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_elect));
                relMainElect.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_main_circlure_noselect));
                if (homeBottomClick != null) {
                    homeBottomClick.presentation();
                }
                break;
            case R.id.rel_main_elect:
                tvTask.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvHistory.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvPresentation.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvMine.setTextColor(getResources().getColor(R.color.color_8c919b));
                imgHistory.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_history_noselect));
                imgPresentation.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_presen_noselect));
                imgTask.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_task_noselect));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_mine_noselect));
                imgElect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_elect));
                relMainElect.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_main_circlure_select));
                if (homeBottomClick != null) {
                    homeBottomClick.electClick();
                }
                break;
            case R.id.rl_task:
                tvTask.setTextColor(getResources().getColor(R.color.color_353535));
                tvHistory.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvPresentation.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvMine.setTextColor(getResources().getColor(R.color.color_8c919b));
                imgHistory.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_history_noselect));
                imgPresentation.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_presen_noselect));
                imgTask.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_task_select));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_mine_noselect));
                imgElect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_elect));
                relMainElect.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_main_circlure_noselect));
                if (homeBottomClick != null) {
                    homeBottomClick.taskClick();
                }
                break;
            case R.id.rl_mine:
                tvMine.setTextColor(getResources().getColor(R.color.color_353535));
                tvHistory.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvPresentation.setTextColor(getResources().getColor(R.color.color_8c919b));
                tvTask.setTextColor(getResources().getColor(R.color.color_8c919b));
                imgHistory.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_history_noselect));
                imgPresentation.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_presen_noselect));
                imgTask.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_task_noselect));
                imgMine.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_mine_select));
                imgElect.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.icon_main_elect));
                relMainElect.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_main_circlure_noselect));
                if (homeBottomClick != null) {
                    homeBottomClick.mineClick();
                }
                break;
        }
    }



    public interface HomeBottomClick {

        void historyClick();

        void presentation();

        void electClick();

        void taskClick();

        void mineClick();
    }
}
