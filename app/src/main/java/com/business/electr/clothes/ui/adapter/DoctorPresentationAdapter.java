package com.business.electr.clothes.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.DoctorPresentationBean;
import com.business.electr.clothes.utils.GlidUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName: DoctorPresentationAdapter
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/19 15:46
 */
public class DoctorPresentationAdapter extends RecyclerView.Adapter<DoctorPresentationAdapter.ViewHolder> {

    private List<DoctorPresentationBean> list = new ArrayList<>();
    private Context mContext;

    public DoctorPresentationAdapter(Context mContext, List<DoctorPresentationBean> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_doctor_presentation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DoctorPresentationBean bean = list.get(position);
        if (bean != null) {
            holder.bindView(bean,position);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_head)
        ImageView imgHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_doctor_position)
        TextView tvDoctorPosition;
        @BindView(R.id.tv_content)
        TextView tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindView(DoctorPresentationBean bean,int position) {
//            GlidUtils.setCircleGrid(mContext,bean.getHeardImagUrl(),imgHead);
            tvName.setText(bean.getNickName());
            tvTime.setText(bean.getCommtentTime());
            tvDoctorPosition.setText(bean.getPosition());
            tvContent.setText(bean.getContent());
        }
    }
}
