package com.business.electr.clothes.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.HistoryTaskBean;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: 历史记录工具类
 * @Author: 曾海强
 * @CreateDate: 2019/5/22 14:35
 */
public class HistoryTaskAdapter extends RecyclerView.Adapter<HistoryTaskAdapter.ViewHolder> {


    private List<HistoryTaskBean> list = new ArrayList<>();
    private Context mContext;

    public HistoryTaskAdapter(Context mContext, List<HistoryTaskBean> list) {
        this.list = list;
        this.mContext = mContext;
    }


    public void setData(List<HistoryTaskBean> data) {
        if (data != null && data.size() > 0) {
            list.clear();
            list.addAll(data);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_history_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list != null && list.size() > 0 && list.get(position) != null) {
            holder.bindView(position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_record_time)
        TextView tvRecordTime;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_sleep_time)
        TextView tvSleepTime;
        @BindView(R.id.tv_elect_num)
        TextView tvElectNum;
        @BindView(R.id.tv_number)
        TextView tvNumber;
        private HistoryTaskBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(int pos) {
            bean = list.get(pos);
            tvRecordTime.setText(bean.getRecordTime());
            if(bean.getState() == 0){
                tvState.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bg_solid_979797_9));
                tvState.setText("未完成");
            }else {
                tvState.setBackground(ContextCompat.getDrawable(mContext,R.drawable.bg_solid_00b68d_9));
                tvState.setText("已完成");
            }
            tvSleepTime.setText(bean.getSleepTime());
            tvElectNum.setText(bean.getElectNum());
            tvNumber.setText(bean.getNumber());
        }
    }
}
