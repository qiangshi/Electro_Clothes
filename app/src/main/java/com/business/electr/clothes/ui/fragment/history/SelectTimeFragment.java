package com.business.electr.clothes.ui.fragment.history;


import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.utils.DateUtils;
import com.business.electr.clothes.utils.MLog;
import com.business.electr.clothes.utils.ScreenUtils;
import com.business.electr.clothes.view.CustomCalendar;

import java.text.SimpleDateFormat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/5/14.
 * 选择时间fragment
 */
public class SelectTimeFragment extends DialogFragment implements CustomCalendar.onClickListener {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.cal)
    CustomCalendar cal;
    private SelectTimeListener listener;
    private String time;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_time,container,false);
        ButterKnife.bind(this,view);
        Bundle bundle = getArguments();
        long time = bundle.getLong("month",System.currentTimeMillis());
        MLog.e("====zhq====>time<"+time);
        tvTitle.setText("选择时间");
        SimpleDateFormat sf = new SimpleDateFormat(Constant.DATE_FORMAT_5);
        cal.setMonth(DateUtils.getTime(time,sf));
        cal.setOnClickListener(this);
        return view;
    }


    public void setListener(SelectTimeListener listener) {
        this.listener = listener;
    }

    @OnClick({R.id.btn_back,R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                dismissAllowingStateLoss();
                break;
            case R.id.tv_commit:
                if(listener != null){
                    listener.onClickSelect(time);
                   dismissAllowingStateLoss();
                }
                break;

        }
    }

    @Override
    public void onDayClick(int day, String dayStr) {
        time = dayStr;
    }


    public interface SelectTimeListener {
        void onClickSelect(String day);
    }


}
