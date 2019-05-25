package com.business.electr.clothes.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.HistoryBean;
import com.business.electr.clothes.router.RouterCons;
import com.sankuai.waimai.router.common.DefaultUriRequest;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: Second60Adapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/25 19:27
 */
public class Second60Adapter extends RecyclerView.Adapter<Second60Adapter.ViewHolder> {


    private Context context;
    private List<HistoryBean> list = new ArrayList<>();

    public Second60Adapter(Context context) {
        this.context = context;
        list.add(new HistoryBean("05月20日报告", "", "平衡", false));
        list.add(new HistoryBean("05月18日报告", "", "平衡", false));
        list.add(new HistoryBean("05月16日报告", "", "轻度焦虑", false));
        list.add(new HistoryBean("05月12日报告", "", "平衡", false));
        list.add(new HistoryBean("05月10日报告", "", "轻度紧张", false));
        list.add(new HistoryBean("05月08日报告", "", "平衡", false));
        list.add(new HistoryBean("05月07日报告", "", "平衡", false));
        list.add(new HistoryBean("05月06日报告", "", "平衡", false));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_second_60, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(list.get(position).getTimeDes());
        holder.tvRecordTime.setText(list.get(position).getState());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DefaultUriRequest(context, RouterCons.CREATE_MEASURE_RESULT)
                        .start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_record_time)
        TextView tvRecordTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
