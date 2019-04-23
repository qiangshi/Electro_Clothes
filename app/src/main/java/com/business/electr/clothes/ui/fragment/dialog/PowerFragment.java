package com.business.electr.clothes.ui.fragment.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.business.electr.clothes.R;

import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 获取权限
 * Created on 2017/12/13.
 */
public class PowerFragment extends DialogFragment {
    public View.OnClickListener onClickListener;
    @BindView(R.id.tv_power_fragment)
    TextView tvPowerFragment;
    @BindView(R.id.tv_content)
    TextView tvContent;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_power, null, false);
        ButterKnife.bind(this, view);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);
        Bundle bundle = getArguments();
        String content = bundle.getString("content");
        tvContent.setText(content);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
        return dialog;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @OnClick({R.id.tv_power_fragment, R.id.tv_pression})
    public void onViewClicked(View v) {
        if (onClickListener != null) onClickListener.onClick(v);
    }
}
