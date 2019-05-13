package com.business.electr.clothes.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.business.electr.clothes.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: 横向滚动的recycler适配器
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/12 16:17
 */
public class MultipleAdapter extends RecyclerView.Adapter<MultipleAdapter.ViewHolder> {

    private Context mContext;

    private List<String> list;
    private int selectPos = -1;

    public MultipleAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_multiple_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position == selectPos){
            holder.tvTime.setTextColor(ContextCompat.getColor(mContext,R.color.color_353535));
        }else {
            holder.tvTime.setTextColor(ContextCompat.getColor(mContext,R.color.color_8c919b));
        }
        holder.tvTime.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    setSelectPos(position);
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setSelectPos(int pos){
        selectPos = pos;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_time)
        TextView tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    private OnMultipleClickListener listener;

    public void setListener(OnMultipleClickListener listener) {
        this.listener = listener;
    }

    public interface OnMultipleClickListener{
        void onItemClick(int pos);
    }
}
