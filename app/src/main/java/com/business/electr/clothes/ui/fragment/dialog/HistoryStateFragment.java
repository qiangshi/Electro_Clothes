package com.business.electr.clothes.ui.fragment.dialog;


import android.app.Dialog;
import android.os.Bundle;
import android.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.business.electr.clothes.R;
import com.business.electr.clothes.ui.adapter.SelectStateAdapter;
import com.business.electr.clothes.ui.adapter.TypeFilterAdapter;
import com.business.electr.clothes.utils.ScreenUtils;

import java.util.List;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zenghaiqiang on 2019/5/13.
 * 描述：历史状态选择
 */
public class HistoryStateFragment extends DialogFragment {
    @BindView(R.id.rv_history_state)
    RecyclerView recyclerView;

    private SelectStateAdapter adapter;
    private LinearLayoutManager layoutManager;

    // 当前选中的下标
    private int curPos;
    private String customText = "自定义";
    //类型发生改变时的回调
    private TypeChangeListener typeChangeListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        Dialog dialog = new Dialog(getActivity(), R.style.UpDownDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View root = inflater.inflate(R.layout.fragment_history_state, null, false);
        dialog.setContentView(root);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = ScreenUtils.getScreenWidth();
        window.setAttributes(attributes);
        window.setGravity(Gravity.BOTTOM);
        dialog.setCanceledOnTouchOutside(true);

        ButterKnife.bind(this, root);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SelectStateAdapter(getContext(),curPos);
        adapter.setListener(new SelectStateAdapter.OnCustomListener() {
            @Override
            public void onClickListener(int pos) {
                typeChangeListener.onTypeChange(pos);
            }
        });

        recyclerView.setAdapter(adapter);
        return dialog;
    }

    public void setCurPos(int position) {
        this.curPos = position;
    }

    public void setCustomText(String customText){
        this.customText = customText;
        adapter.setCustomText(customText);
    }

    private void hideFragment() {
        try {
            if (isAdded()) dismissAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setListener(TypeChangeListener typeChangeListener) {
        this.typeChangeListener = typeChangeListener;
    }

    public interface TypeChangeListener {
        void onTypeChange(int pos);
    }

    public HistoryStateFragment showFragment(FragmentManager manager, int position,String customText, TypeChangeListener typeChangeListener) {
        HistoryStateFragment fragment = new HistoryStateFragment();
        fragment.setCurPos(position);
        fragment.setCustomText(customText);
        fragment.setListener(typeChangeListener);
        fragment.show(manager, "historyStateFragment");
        return fragment;
    }

}
