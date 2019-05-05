package com.business.electr.clothes.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.business.electr.clothes.R;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/5 9:58
 */
public class TimeTypeView extends LinearLayout {

    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_week)
    TextView tvWeek;
    @BindView(R.id.tv_month)
    TextView tvMonth;
    @BindView(R.id.tv_year)
    TextView tvYear;
    private Context context;
    private OnClickItemListener listener;

    public TimeTypeView(Context context) {
        this(context, null);
    }

    public TimeTypeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeTypeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();

    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.time_type_view, this);
        ButterKnife.bind(this, view);
    }



    @OnClick({R.id.tv_day, R.id.tv_week, R.id.tv_month, R.id.tv_year})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_day:
                tvDay.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_time_day_select));
                tvMonth.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_time_month_noselect));
                tvWeek.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_time_month_noselect));
                tvYear.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_time_year_noselect));
                tvDay.setTextColor(ContextCompat.getColor(context,R.color.white));
                tvMonth.setTextColor(ContextCompat.getColor(context,R.color.color_979797));
                tvWeek.setTextColor(ContextCompat.getColor(context,R.color.color_979797));
                tvYear.setTextColor(ContextCompat.getColor(context,R.color.color_979797));
                if(listener != null) listener.onDayClick();
                break;
            case R.id.tv_week:
                tvDay.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_time_day_noselect));
                tvMonth.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_time_month_noselect));
                tvWeek.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_time_month_select));
                tvYear.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_time_year_noselect));
                tvDay.setTextColor(ContextCompat.getColor(context,R.color.color_979797));
                tvMonth.setTextColor(ContextCompat.getColor(context,R.color.color_979797));
                tvWeek.setTextColor(ContextCompat.getColor(context,R.color.white));
                tvYear.setTextColor(ContextCompat.getColor(context,R.color.color_979797));
                if(listener != null) listener.onWeekClick();
                break;
            case R.id.tv_month:
                tvDay.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_time_day_noselect));
                tvMonth.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_time_month_select));
                tvWeek.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_time_month_noselect));
                tvYear.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_time_year_noselect));
                tvDay.setTextColor(ContextCompat.getColor(context,R.color.color_979797));
                tvMonth.setTextColor(ContextCompat.getColor(context,R.color.white));
                tvWeek.setTextColor(ContextCompat.getColor(context,R.color.color_979797));
                tvYear.setTextColor(ContextCompat.getColor(context,R.color.color_979797));
                if(listener != null) listener.onMonthClick();
                break;
            case R.id.tv_year:
                tvDay.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_time_day_noselect));
                tvMonth.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_time_month_noselect));
                tvWeek.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_time_month_noselect));
                tvYear.setBackground(ContextCompat.getDrawable(context,R.drawable.bg_time_year_select));
                tvDay.setTextColor(ContextCompat.getColor(context,R.color.color_979797));
                tvMonth.setTextColor(ContextCompat.getColor(context,R.color.color_979797));
                tvWeek.setTextColor(ContextCompat.getColor(context,R.color.color_979797));
                tvYear.setTextColor(ContextCompat.getColor(context,R.color.white));
                if(listener != null) listener.onYearClick();
                break;
        }
    }


    public void setListener(OnClickItemListener listener) {
        this.listener = listener;
    }

    public interface OnClickItemListener{
        void onDayClick();

        void onWeekClick();

        void onMonthClick();

        void onYearClick();
    }
}
