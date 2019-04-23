package com.business.electr.clothes.ui.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.business.electr.clothes.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by wangzhenhua on 2018/8/30.
 * 描述：类型筛选控件中列表的适配器
 */
public class TypeFilterAdapter extends RecyclerView.Adapter<TypeFilterAdapter.ViewHolder> {
    private Context mContext;
    private List<String> types;
    private int curPos;
    private OnItemClickListener onItemClickListener;

    public TypeFilterAdapter(Context context, List<String> types, int curPos) {
        this.mContext = context;
        this.types = types;
        this.curPos = curPos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_type_filter_recyclerview, parent, false);
        return new TypeFilterAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.typeTv.setText(types.get(position));
        if (position == curPos) {
            holder.typeTv.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        } else {
            holder.typeTv.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onItemClickListener) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_type_filter_rec_type)
        TextView typeTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }
}
