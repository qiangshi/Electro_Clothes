package com.business.electr.clothes.ui.fragment.history;


import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.utils.ScreenUtils;
import com.business.electr.clothes.view.CustomCalendar;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/5/14.
 * 选择时间fragment
 */
public class SelectTimeFragment extends DialogFragment {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.cal)
    CustomCalendar cal;
    private SelectTimeListener listener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View root = inflater.inflate(R.layout.fragment_select_time, null, false);
        dialog.setContentView(root);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = ScreenUtils.getScreenWidth();
        attributes.height = ScreenUtils.getScreenHeight();
        window.setAttributes(attributes);
        window.setGravity(Gravity.CENTER);
        dialog.setCanceledOnTouchOutside(true);
        ButterKnife.bind(this, root);
        return dialog;
    }


    private void hideFragment() {
        try {
            if (isAdded()) dismissAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setListener(SelectTimeListener listener) {
        this.listener = listener;
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
    }


    public interface SelectTimeListener {
        void onClickSelect(String day);
    }

    public static SelectTimeFragment showFragment(FragmentManager manager, SelectTimeListener listener) {
        SelectTimeFragment fragment = new SelectTimeFragment();
        fragment.setListener(listener);
        fragment.show(manager, "selectTimeFragment");
        return fragment;
    }

}
