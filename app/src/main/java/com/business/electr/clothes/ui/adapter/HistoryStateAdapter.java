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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ClassName: HistoryStateAdapter
 * Description: 历史状态适配器
 * Author: 曾海强
 * CreateDate: 2019/5/12 18:30
 */
public class HistoryStateAdapter extends RecyclerView.Adapter<HistoryStateAdapter.ViewHolder> {


    private Context mContext;
    private List<HistoryBean> list = new ArrayList<>();

    public HistoryStateAdapter(Context mContext, List<HistoryBean> list) {
        this.mContext = mContext;
        this.list.addAll(list);
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


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_time_)
        TextView tvTimeDis;
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.tv_circular)
        TextView tvCircular;
        @BindView(R.id.rl_select)
        RelativeLayout rlSelect;
        @BindView(R.id.rl_no_select)
        RelativeLayout rlNoSelect;
        HistoryBean bean;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(int position) {
            bean = list.get(position);
            this.position = position;
            if (bean.isSelect()) {
                tvState.setText(bean.getState());
                rlNoSelect.setVisibility(View.GONE);
                rlSelect.setVisibility(View.VISIBLE);
            } else {
                rlSelect.setVisibility(View.GONE);
                rlNoSelect.setVisibility(View.VISIBLE);
            }
            tvTime.setText(bean.getTimeDes());
            tvTimeDis.setText(bean.getTimeDistance());
            switch (bean.getState()){
                case "睡觉":
                    tvCircular.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circular_4a90e2));
                    break;
                case "运动":
                    tvCircular.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circular_ffa821));
                    break;
                case "就餐":
                    tvCircular.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circular_ffc200));
                    break;
                case "学习":
                    tvCircular.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circular_0cbb94));
                    break;
                case "工作":
                    tvCircular.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circular_7ed321));
                    break;
                case "开会":
                    tvCircular.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circular_339c85));
                    break;
                default:
                    tvCircular.setBackground(ContextCompat.getDrawable(mContext,R.drawable.circular_d0d1d2));
                    break;
            }
        }


        @OnClick({R.id.rl_select, R.id.rl_no_select})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.rl_select:
                case R.id.rl_no_select:
                    if (onItemClickListener != null) {
                        onItemClickListener.OnClickItemListener(bean, position);
                    }
                    break;
            }
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
