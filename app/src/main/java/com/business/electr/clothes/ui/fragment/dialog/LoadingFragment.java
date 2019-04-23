package com.business.electr.clothes.ui.fragment.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.business.electr.clothes.R;

import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zenghaiqiang on 2018/5/7.
 * 加载页面
 */
public class LoadingFragment extends DialogFragment {

    @BindView(R.id.image_loading)
    ImageView imageLoading;
    Unbinder unbinder;
    private View view;
    private Dialog dialog;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_load, null, false);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        //设置对话框内部的把背景为透明
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        unbinder = ButterKnife.bind(this, view);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        //设置对话框外部的背景为透明
        Window window = dialog.getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);
    }

    public void onResume() {
        super.onResume();
        if (view == null) return;
        final AnimationDrawable animationDrawable = (AnimationDrawable) imageLoading.getBackground();
        animationDrawable.start();
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
                    animationDrawable.stop();
                    dismissAllowingStateLoss();
                    return true;
                }
                return false;
            }
        });
    }

}
