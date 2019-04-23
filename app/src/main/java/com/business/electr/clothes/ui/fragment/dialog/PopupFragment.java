package com.business.electr.clothes.ui.fragment.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.ui.activity.BaseActivity;

import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by zenghaiqiang on 2018/6/7.
 * 描述：提示弹框
 */
public class PopupFragment extends DialogFragment {

    public View.OnClickListener clickListener;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_commit)
    TextView tvCommit;


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Dialog dialog = new Dialog(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_popup_dialog, null, false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        tvHint.setText(bundle.getString(Constant.DIALOG_HINT));
        tvContent.setText(bundle.getString(Constant.DIALOG_CONTENT));
        tvCancel.setText(bundle.getString(Constant.DIALOG_LEFT));
        tvCommit.setText(bundle.getString(Constant.DIALOG_RIGHT));
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });
        return dialog;
    }

    public void setListener(View.OnClickListener listener) {
        clickListener = listener;
    }

    @OnClick({R.id.tv_cancel, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
            case R.id.tv_commit:
                if (clickListener != null) clickListener.onClick(view);
                ((BaseActivity) getActivity()).hidePopupFragment();
                break;
        }
    }
}
