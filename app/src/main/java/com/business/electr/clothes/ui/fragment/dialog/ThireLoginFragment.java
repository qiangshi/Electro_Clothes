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
import android.widget.Toast;

import com.business.electr.clothes.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName: ThireLoginFragment
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/4/25 21:22
 */
public class ThireLoginFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_thire_login, null, false);
        ButterKnife.bind(this, view);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
        return dialog;
    }


    @OnClick({R.id.ll_facebook, R.id.ll_twitter, R.id.ll_google})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_facebook:
                Toast.makeText(getActivity(),"facebook",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_twitter:
                Toast.makeText(getActivity(),"twitter",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_google:
                Toast.makeText(getActivity(),"google",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
