package com.business.electr.clothes.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.HistoryBean;
import com.business.electr.clothes.mvp.view.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @ClassName: HistoryStateAdapter
 * @Description: 历史状态适配器
 * @Author: 曾海强
 * @CreateDate: 2019/5/12 18:30
 */
public class HistoryStateAdapter extends RecyclerView.Adapter<HistoryStateAdapter.ViewHolder> {


    private Context mContext;
    private List<HistoryBean> list = new ArrayList<>();

    public HistoryStateAdapter(Context mContext) {
        this.mContext = mContext;
        list.add(new HistoryBean("06:15-07:15","1小时","睡觉",true));
        list.add(new HistoryBean("18:45-20:15","1.5小时","",false));
        list.add(new HistoryBean("18:45-20:15","1.5小时","",false));
    }

    public void setData(List<HistoryBean> historyBeanList) {
        if (historyBeanList != null && historyBeanList.size() > 0) {
            list.clear();
            list.addAll(historyBeanList);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_history_tag, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.get(position) != null)
            holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @OnClick(R.id.rl_no_select)
    public void onViewClicked() {
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_time_)
        TextView tvTimeDis;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.rl_select)
        RelativeLayout rlSelect;
        @BindView(R.id.rl_no_select)
        RelativeLayout rlNoSelect;
        HistoryBean bean;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bindView(int position) {
            bean = list.get(position);
            if (bean.isSelect()){
                tvState.setText(bean.getState());
                rlNoSelect.setVisibility(View.GONE);
                rlSelect.setVisibility(View.VISIBLE);
            }else {
                rlSelect.setVisibility(View.GONE);
                rlNoSelect.setVisibility(View.VISIBLE);

            }
            tvTime.setText(bean.getTimeDes());
            tvTimeDis.setText(bean.getTimeDistance());
            rlNoSelect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener != null){
                        onItemClickListener.OnClickItemListener(bean,position);
                    }
                }
            });

        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
