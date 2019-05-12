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
import com.business.electr.clothes.ui.adapter.TypeFilterAdapter;
import com.business.electr.clothes.utils.CommonUtils;
import com.business.electr.clothes.utils.ScreenUtils;

import java.util.List;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TypeGraderFragment extends DialogFragment {
    @BindView(R.id.recycler_dialog_type_filter)
    RecyclerView recyclerView;

    private TypeFilterAdapter adapter;
    private LinearLayoutManager layoutManager;

    // 当前选中的下标
    private int curPos;
    // 类型列表
    private List<String> types;
    //类型发生改变时的回调
    private TypeChangeListener typeChangeListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        Dialog dialog = new Dialog(getActivity(), R.style.UpDownDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View root = inflater.inflate(R.layout.fragment_type_grader, null, false);
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
        adapter = new TypeFilterAdapter(getContext(), types, curPos);
        adapter.setOnItemClickListener(pos -> {
            typeChangeListener.onTypeChange(pos);
            hideFragment();
        });
        recyclerView.setAdapter(adapter);
        if (curPos >= 0 && curPos < types.size()) {
            recyclerView.scrollToPosition(curPos);
        }
        return dialog;
    }

    public void setData(List<String> types, int position) {
        this.curPos = position;
        this.types = types;
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

    public static TypeGraderFragment showFragment(FragmentManager manager, List<String> types, int position, TypeChangeListener typeChangeListener) {
        TypeGraderFragment fragment = new TypeGraderFragment();
        fragment.setData(types, position);
        fragment.setListener(typeChangeListener);
        fragment.show(manager, "");
        return fragment;
    }

}
