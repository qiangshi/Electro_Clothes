package com.business.electr.clothes.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.business.electr.clothes.R;
import com.business.electr.clothes.utils.MLog;
import androidx.core.content.ContextCompat;
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
    @BindView(R.id.tv_fill_color)
    TextView tvFillColor;

    private Context context;
    /**
     * 各部分背景
     */
    private int mBgFirst, mBgAll, mBgUp,mBgIn;
    private float mProcess;
    private float mBgWidth;
    private String mTextDefault;
    private int mFillColor;
    private int mShowType;
    public DayTaskView(Context context) {
        this(context, null);
    }

    public DayTaskView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DayTaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        //获取自定义属性的值
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomDayTaskView, defStyleAttr, 0);
        mBgFirst = a.getResourceId(R.styleable.CustomDayTaskView_mBgFirst,R.drawable.icon_history_heartbeat);
        mBgAll = a.getResourceId(R.styleable.CustomDayTaskView_mBgAll, R.drawable.icon_task_bg);
        mBgUp = a.getResourceId(R.styleable.CustomDayTaskView_mBgUp,R.drawable.icon_up_elect);
        mBgIn = a.getResourceId(R.styleable.CustomDayTaskView_mBgIn, R.drawable.icon_in_elect);
        mProcess = a.getFloat(R.styleable.CustomDayTaskView_mProcess, 0.5f);
        mBgWidth = a.getDimension(R.styleable.CustomDayTaskView_mBgWidth, 150*3);
        mTextDefault = a.getString(R.styleable.CustomDayTaskView_mTextDefault);
        mFillColor = a.getResourceId(R.styleable.CustomDayTaskView_mFillColor,R.color.color_0bbb94);
        mShowType = a.getInteger(R.styleable.CustomDayTaskView_mShowType,2);
        MLog.e(mBgFirst+"====zhq====>dim<"+mProcess+"===>"+mBgWidth+"====>"+R.drawable.icon_history_heartbeat);
        a.recycle();
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.day_task_view, this);
        ButterKnife.bind(this, view);
        imgBg.setImageDrawable(ContextCompat.getDrawable(context,mBgAll));
        setBgWidth((int) mBgWidth);
        setFinishWidth(mProcess);
        imgLeftBgElect.setImageDrawable(ContextCompat.getDrawable(context,mBgFirst));
        imgInElect.setImageDrawable(ContextCompat.getDrawable(context,mBgIn));
        imgUpElect.setImageDrawable(ContextCompat.getDrawable(context,mBgUp));
        imgInElectLast.setImageDrawable(ContextCompat.getDrawable(context,mBgFirst));
        tvElectNum.setText(mTextDefault);
        tvFillColor.setBackgroundColor(ContextCompat.getColor(context,mFillColor));
        switch (mShowType){
            case 0:
                tvElect.setText(getResources().getString(R.string.sleep));
                break;
            case 1:
                tvElect.setText(getResources().getString(R.string.elect_num));
                break;
            case 2:
                tvElect.setText(getResources().getString(R.string.number));
                break;
        }
    }


    public void setFinishWidth(float process){
        if(process <= 0){
            imgInElectLast.setVisibility(GONE);
            tvFillColor.setVisibility(GONE);
            imgUpElect.setVisibility(GONE);
        }else {
            imgUpElect.setVisibility(VISIBLE);
            imgInElectLast.setVisibility(VISIBLE);
            tvFillColor.setVisibility(VISIBLE);
            int width = (int) ((mBgWidth - 96) * process)+96;
            RelativeLayout.LayoutParams params = (LayoutParams) llElectBg.getLayoutParams();
            params.width = width;
            llElectBg.setLayoutParams(params);
        }
    }

    public void setBgWidth(int width){
        RelativeLayout.LayoutParams params = (LayoutParams) imgBg.getLayoutParams();
        params.width = width;
        imgBg.setLayoutParams(params);
    }

    public void setProcess(String text,float process){
        setFinishWidth(process);
        tvElectNum.setText(text);
    }

}
