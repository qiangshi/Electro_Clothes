package com.business.electr.clothes.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.business.electr.clothes.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: DayTaskView
 * @Description: 历史中的今日任务类别
 * @Author: 曾海强
 * @CreateDate: 2019/5/22 22:36
 */
public class DayTaskView extends RelativeLayout {

    @BindView(R.id.img_bg)
    ImageView imgBg;
    @BindView(R.id.img_left_bg_elect)
    ImageView imgLeftBgElect;
    @BindView(R.id.img_in_elect_last)
    ImageView imgInElectLast;
    @BindView(R.id.img_up_elect)
    ImageView imgUpElect;
    @BindView(R.id.ll_elect_bg)
    LinearLayout llElectBg;
    @BindView(R.id.img_in_elect)
    ImageView imgInElect;
    @BindView(R.id.tv_elect)
    TextView tvElect;
    @BindView(R.id.tv_elect_num)
    TextView tvElectNum;
    @BindView(R.id.rel_elect_back)
    RelativeLayout relElectBack;
    @BindView(R.id.view)
    View view;
    private Context context;

    public DayTaskView(Context context) {
        this(context, null);
    }

    public DayTaskView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DayTaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.day_task_view, this);
        ButterKnife.bind(this, view);
    }


    public void setFinishWidth(int width){
        ViewGroup.LayoutParams params = llElectBg.getLayoutParams();
        params.width = width;
        llElectBg.setLayoutParams(params);
    }
}
