package com.business.electr.clothes.ui.fragment.equipment;


import android.app.Fragment;

import com.business.electr.clothes.R;
import com.business.electr.clothes.mvp.presenter.basePresenter.IPresenter;
import com.business.electr.clothes.mvp.view.OnItemClickListener;
import com.business.electr.clothes.ui.activity.equipment.EquipmentActivity;
import com.business.electr.clothes.ui.adapter.EquipmentAdapter;
import com.business.electr.clothes.ui.fragment.BaseFragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Equipment2Fragment extends BaseFragment implements OnItemClickListener {


    @BindView(R.id.rv_equip)
    RecyclerView rvEquip;

    public Equipment2Fragment() {}


    @Override
    protected void initEventAndData() {
        rvEquip.setLayoutManager(new LinearLayoutManager(getActivity()));
        EquipmentAdapter adapter = new EquipmentAdapter(getActivity());
        rvEquip.setAdapter(adapter);
        adapter.setListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_equipment2;
    }

    @Override
    protected IPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

    }

    @Override
    public void OnClickItemListener(Object obj, int position) {
        ((EquipmentActivity)getActivity()).setCurrentPage(2);
    }
}
