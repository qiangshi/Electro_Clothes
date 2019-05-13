package com.business.electr.clothes.ui.fragment.dialog;


import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.business.electr.clothes.R;
import com.business.electr.clothes.ui.adapter.SelectStateAdapter;
import com.business.electr.clothes.utils.ToastUtils;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zenghaiqiang on 2019/5/13.
 * 描述：填写自定义状态
 */
public class CustomStateFragment extends DialogFragment {


    @BindView(R.id.et_custom)
    EditText etCustom;
    private CustomTextListener listener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View root = inflater.inflate(R.layout.fragment_custom_state, null, false);
        dialog.setContentView(root);
        Window window = dialog.getWindow();
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

    public void setListener(CustomTextListener listener) {
        this.listener = listener;
    }

    @OnClick(R.id.tv_confirm)
    public void onViewClicked() {//确定
        if(!TextUtils.isEmpty(etCustom.getText().toString())){
            if(listener != null) {
                listener.onCustomText(etCustom.getText().toString());
                hideFragment();
            }
        }else {
            ToastUtils.showToast(getActivity(),"请填写状态");
        }
    }

    public interface CustomTextListener {
        void onCustomText(String customText);
    }

    public static CustomStateFragment showFragment(FragmentManager manager, CustomTextListener listener) {
        CustomStateFragment fragment = new CustomStateFragment();
        fragment.setListener(listener);
        fragment.show(manager, "customStateFragment");
        return fragment;
    }

}
