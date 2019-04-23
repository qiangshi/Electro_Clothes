package com.business.electr.clothes.ui.activity.mine;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;

import com.business.electr.clothes.R;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.mvp.presenter.mine.FeedBackPresenter;
import com.business.electr.clothes.mvp.view.mine.FeedBackView;
import com.business.electr.clothes.router.RouterCons;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.ui.adapter.ImageSelectAdapter;
import com.business.electr.clothes.utils.SelectImageUtils;
import com.sankuai.waimai.router.annotation.RouterUri;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by zenghaiqiang on 2019/01/24.
 * 描述：问题反馈
 */
@RouterUri(path = {RouterCons.CREATE_QUESTION_FEEDBACK})
public class FeedBackActivity extends BaseActivity<FeedBackPresenter> implements FeedBackView,ImageSelectAdapter.OnImageClickListener {

    @BindView(R.id.text_feedback)
    EditText textFeedback;//反馈的信息
    @BindView(R.id.rv_imgs)
    RecyclerView rvImgs;
    @BindView(R.id.contact_email)
    EditText contactEmail;//联系方式
    public static final int IMG_SIZE = 3;//最多可传的图片数量

    private List<String> imgUrls;
    private ImageSelectAdapter adapter;



    @Override
    protected int getLayoutId() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected FeedBackPresenter getPresenter() {
        return new FeedBackPresenter(this);
    }

    @Override
    protected void initDataAndEvent() {
        initRightTitle(getResources().getString(R.string.face_back), getResources().getString(R.string.commit));
        rvImgs.setLayoutManager(new GridLayoutManager(this, IMG_SIZE));
        imgUrls = new ArrayList<>();
        // 默认的一张空白占位图
        imgUrls.add("");
        adapter = new ImageSelectAdapter(this, imgUrls);
        rvImgs.setAdapter(adapter);
        adapter.setListener(this);
    }


    @OnClick(R.id.tv_right_btn)
    public void onViewClicked() {//提交
        MultipartBody.Part pic1 = null;
        MultipartBody.Part pic2 = null;
        MultipartBody.Part pic3 = null;
        for (int i = 0; i < imgUrls.size() ; i++) {
            String imgUrl = imgUrls.get(i);
            if(!TextUtils.isEmpty(imgUrl)){
                File file = new File(imgUrl);
                RequestBody fileRQ = RequestBody.create(MediaType.parse("image/*"), file);
                if(i == 0){
                    pic1 = MultipartBody.Part.createFormData("pic1", file.getName(), fileRQ);
                }else if(i == 1){
                    pic2 = MultipartBody.Part.createFormData("pic2", file.getName(), fileRQ);
                }else if(i == 2){
                    pic3 = MultipartBody.Part.createFormData("pic3", file.getName(), fileRQ);
                }
            }
        }
        MultipartBody.Part userId = MultipartBody.Part.createFormData("user_id", String.valueOf(DataCacheManager.getUserInfo().getUserId()));
        MultipartBody.Part token = MultipartBody.Part.createFormData("token", DataCacheManager.getToken());
        MultipartBody.Part content = MultipartBody.Part.createFormData("content", textFeedback.getText().toString());
        MultipartBody.Part contact = MultipartBody.Part.createFormData("contact", contactEmail.getText().toString());
        mPresenter.requestFeedBack(userId,token,content,pic1,pic2,pic3,contact);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(this, requestCode, resultCode, data);
    }
 

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPresenter.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void feedbackSuccess() {

    }

    @Override
    public void selectPhoto(int position) {
        SelectImageUtils.selectPhoto(this, getString(R.string.takephoto), false, true, 1);
    }

    @Override
    public void onUploadSuccess(String imgUrl,int position) {
        if (imgUrls.size() < position) {// 添加图片
            imgUrls.add(imgUrl);
        } else if (imgUrls.size() >= position) {// 替换图片
            imgUrls.set(position, imgUrl);
        }
        if (imgUrls.size() < IMG_SIZE) {
            if (!imgUrls.get(imgUrls.size() - 1).equals("")) {// 如果最后一张已经是空白图的话不操作，否则添加一张空白图
                imgUrls.add("");
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onImageClick(int position) {
        mPresenter.autoObtainStoragePermission(this, position);
    }

    @Override
    public void onRemoveImgClick(int position) {
        // 删除图片
        imgUrls.remove(position);
        if (imgUrls.size() < IMG_SIZE) {
            // 如果最后一张已经是空白图的话不操作，否则添加一张空白图
            if (!imgUrls.get(imgUrls.size() - 1).equals("")) {
                imgUrls.add("");
            }
        }
        adapter.notifyDataSetChanged();
    }
}
