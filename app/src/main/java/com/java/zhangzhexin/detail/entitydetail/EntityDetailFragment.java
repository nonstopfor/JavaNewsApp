package com.java.zhangzhexin.detail.entitydetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.java.zhangzhexin.BaseFragment;
import com.java.zhangzhexin.R;
import com.java.zhangzhexin.model.EntityCard;
import com.java.zhangzhexin.model.SingleNews;

public class EntityDetailFragment extends BaseFragment<EntityDetailView, EntityDetailPresenter> implements EntityDetailView {
    private TextView label;
    private TextView description;
    private ImageView image;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("ListFragment : "+type+" onCreateView");
        if(view == null)
        {
            System.out.println("ListFragment: "+type+" view为空");
            view = inflater.inflate(R.layout.fragment_newsdetail, container, false);
            initView();
        }
        return view;
    }

    public static EntityDetailFragment newInstance(){
        return new EntityDetailFragment();
    }

    public void initView(){
        label = view.findViewById(R.id.label);
        description = view.findViewById(R.id.description);
        image = view.findViewById(R.id.image);
    }

    @Override
    public EntityDetailPresenter createPresenter() {
        return new EntityDetailPresenter();
    }

    @Override
    public void start(Intent intent) {
        startActivity(intent);
    }

    @Override
    public void onResume() {
        System.out.println("实体详情页onResume");
        super.onResume();
    }

    public void setId(int id){
        myPresenter.setEntity(id);
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    public void setView(EntityCard entity) {
        Glide.with(this).load(entity.imgUrl).into(image);
        label.setText(entity.label);
        description.setText(entity.description);
    }
}
