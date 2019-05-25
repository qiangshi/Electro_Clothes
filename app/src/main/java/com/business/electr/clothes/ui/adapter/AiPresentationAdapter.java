package com.business.electr.clothes.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.AiPresentationBean;
import com.business.electr.clothes.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: AiPresentationAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/19 14:22
 */
public class AiPresentationAdapter extends RecyclerView.Adapter<AiPresentationAdapter.ViewHolder> {


    private List<AiPresentationBean> list = new ArrayList<>();
    private Context mContext;

    public AiPresentationAdapter( Context mContext,List<AiPresentationBean> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ai_presentation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AiPresentationBean bean = list.get(position);
        if(bean != null){
            holder.tvTitle.setText(bean.getTitle());
            holder.tvRecordTime.setText(bean.getRecordTime());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,R.string.development_ing,Toast.LENGTH_SHORT).show();
                }
            });
        }

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
            ButterKnife.bind(this, itemView);
        }
    }
}
