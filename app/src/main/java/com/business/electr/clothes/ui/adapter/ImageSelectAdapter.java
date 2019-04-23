package com.business.electr.clothes.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.utils.GlidUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by liuchang on 2018/6/11.
 */

public class ImageSelectAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> imgs;
    private OnImageClickListener listener;

    public OnImageClickListener getListener() {
        return listener;
    }

    public void setListener(OnImageClickListener listener) {
        this.listener = listener;
    }

    public ImageSelectAdapter(Context context, List<String> imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feedback_img, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ImageHolder) holder).bindView(imgs.get(position), position);
    }

    @Override
    public int getItemCount() {
        return imgs == null ? 0 : imgs.size();
    }

    private class ImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView ivPhoto;
        private final ImageView removeImg;
        private int position;

        public ImageHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            removeImg = itemView.findViewById(R.id.remove_iv);
            removeImg.setOnClickListener(this);
            ivPhoto.setOnClickListener(this);
        }

        private void bindView(String imgUrl, int position) {
            this.position = position;
            if (imgUrl == null || imgUrl.isEmpty()) {
                removeImg.setVisibility(View.GONE);
                GlidUtils.setGrid(context,R.drawable.tool_askleave_upload_icon_xhdpi,ivPhoto);
            } else {
                removeImg.setVisibility(View.VISIBLE);
                GlidUtils.setGrid(context,imgUrl,ivPhoto);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_photo:
                    if (listener != null) {
                        listener.onImageClick(position);
                    }
                    break;
                case R.id.remove_iv:
                    if (listener != null) {
                        listener.onRemoveImgClick(position);
                    }
                    break;
            }
        }
    }

    public interface OnImageClickListener {
        void onImageClick(int position);

        void onRemoveImgClick(int position);
    }
}
