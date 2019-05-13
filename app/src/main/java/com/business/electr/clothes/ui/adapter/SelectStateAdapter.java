package com.business.electr.clothes.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.electr.clothes.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/13 9:53
 */
public class SelectStateAdapter extends RecyclerView.Adapter<SelectStateAdapter.ViewHolder> {

    private Context context;
    private List<String> list = new ArrayList<>();
    private int curPos = -1;
    private String customText = "自定义";

    public SelectStateAdapter(Context context,int curPos) {
        this.context = context;
        this.curPos = curPos;
        list = Arrays.asList(context.getResources().getStringArray(R.array.select_state_list));
    }

    public void setCustomText(String customText) {
        this.customText = customText;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_select_state, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_state)
        TextView tvState;
        @BindView(R.id.view)
        View view;
        @BindView(R.id.img_select)
        ImageView imgSelect;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(int pos) {
            tvState.setText(list.get(pos));
            if(curPos == pos){
                imgSelect.setVisibility(View.VISIBLE);
            }else imgSelect.setVisibility(View.GONE);
            switch (pos){
                case 0:
                    view.setBackground(ContextCompat.getDrawable(context,R.drawable.circular_4a90e2));
                    break;
                case 1:
                    view.setBackground(ContextCompat.getDrawable(context,R.drawable.circular_ffa821));
                    break;
                case 2:
                    view.setBackground(ContextCompat.getDrawable(context,R.drawable.circular_ffc200));
                    break;
                case 3:
                    view.setBackground(ContextCompat.getDrawable(context,R.drawable.circular_0cbb94));
                    break;
                case 4:
                    view.setBackground(ContextCompat.getDrawable(context,R.drawable.circular_7ed321));
                    break;
                case 5:
                    view.setBackground(ContextCompat.getDrawable(context,R.drawable.circular_339c85));
                    break;
                case 6:
                    tvState.setText(customText);
                    view.setBackground(ContextCompat.getDrawable(context,R.drawable.circular_d0d1d2));
                    break;
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    curPos = pos;
                    notifyDataSetChanged();
                    if(listener != null){
                        listener.onClickListener(pos);
                    }

                }
            });

        }
    }

    private OnCustomListener listener;

    public void setListener(OnCustomListener listener) {
        this.listener = listener;
    }

    public interface OnCustomListener{
        void onClickListener(int pos);
    }

}
