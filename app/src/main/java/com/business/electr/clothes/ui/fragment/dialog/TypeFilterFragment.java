package com.business.electr.clothes.ui.fragment.dialog;


import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.business.electr.clothes.R;
import com.business.electr.clothes.ui.adapter.TypeFilterAdapter;
import com.business.electr.clothes.utils.CommonUtils;
import com.business.electr.clothes.utils.MLog;
import com.business.electr.clothes.utils.ScreenUtils;

import java.util.List;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangzhenhua on 2018/8/30.
 * 描述：搜索结果类型筛选控件
 */
public class TypeFilterFragment extends DialogFragment {
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
        View root = inflater.inflate(R.layout.dialog_type_filter, null, false);
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

    public static TypeFilterFragment showFragment(FragmentManager manager, List<String> types, int position, TypeChangeListener typeChangeListener) {
        TypeFilterFragment fragment = new TypeFilterFragment();
        fragment.setData(types, position);
        fragment.setListener(typeChangeListener);
        fragment.show(manager, "");
        return fragment;
    }

    public static TypeFilterFragment showFragment(WindowManager wm, FragmentManager manager, List<String> types, int position, TypeChangeListener typeChangeListener) {
        TypeFilterFragment fragment = new TypeFilterFragment();
        fragment.setData(types, position);
        fragment.setListener(typeChangeListener);
        fragment.show(manager, "");
        System.err.println(fragment+"==="+fragment.getDialog()+"====zhq====>111<");
        setDialogHeight(wm, types.size(), fragment);
        return fragment;
    }


    /**
     * 设置 dialog的高度
     * 可根据list的条数来设置高度
     *
     * @param dialog
     */
    private static void setDialogHeight(WindowManager wm, int count, DialogFragment dialog) {
        WindowManager.LayoutParams p = dialog.getDialog().getWindow().getAttributes();  //获取对话框当前的参数值
        if (count > 4) p.height = CommonUtils.dp2px(dialog.getContext(),200);//设置为当前屏幕高度的一半
        if (count <= 4) p.height = RecyclerView.LayoutParams.WRAP_CONTENT;
        dialog.getDialog().getWindow().setAttributes(p);     //设置生效
    }
}
