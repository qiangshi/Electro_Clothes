package com.business.electr.clothes.ui.fragment.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.business.electr.clothes.R;

import androidx.fragment.app.DialogFragment;

/**
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/2/20 16:53
 */

public class EmptyFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty,container,false);
        return view;
    }
}
