package com.java.zhangzhexin.detail.scholardetail;

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
import com.java.zhangzhexin.model.Relation;
import com.java.zhangzhexin.model.ScholarCard;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScholarDetailFragment extends BaseFragment<ScholarDetailView, ScholarDetailPresenter> implements ScholarDetailView {
    private TextView label;
    private TextView description;
    private ImageView image;

    @BindView(R.id.groupListView)
    QMUIGroupListView groupListView;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("学者页 : " + type + " onCreateView");
//        if (view == null) {
//            System.out.println("ListFragment: " + type + " view为空");
//            view = inflater.inflate(R.layout.entity_detail, container, false);
//            ButterKnife.bind(this, view);
//            initView();
//        }
        view = inflater.inflate(R.layout.entity_detail, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public static ScholarDetailFragment newInstance() {
        return new ScholarDetailFragment();
    }

    public void initView() {
        label = view.findViewById(R.id.label);
        description = view.findViewById(R.id.description);
        image = view.findViewById(R.id.image);
    }

    @Override
    public ScholarDetailPresenter createPresenter() {
        return new ScholarDetailPresenter();
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

    public void setId(int id) {
        myPresenter.setEntity(id);
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    public void setView(ScholarCard scholar) {
        System.out.println("imgUrl = " + scholar.avatar);
        Glide.with(this).load(scholar.avatar).into(image);
        //label.setText(scholar.label);
        //description.setText(scholar.description);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        //Clear previous Sections
        while (groupListView.getSectionCount() > 0) {
            System.out.println(groupListView.getSectionCount());
//            if (mGroupListView.getSection(0) != null) {
//                System.out.println("not null");
//            }
            groupListView.getSection(groupListView.getSectionCount()-1)
                    .removeFrom(groupListView);
        }

//        if (scholar.properties.size() > 0) {
//            QMUIGroupListView.Section propertySection = QMUIGroupListView.newSection(getMyContext()).setTitle("属性");
//            for (String key : scholar.properties.keySet()) {
//                String val = scholar.properties.get(key);
//                QMUICommonListItemView itemWithDetailBelow = groupListView.createItemView(null,
//                        key,
//                        val,
//                        QMUICommonListItemView.VERTICAL,
//                        QMUICommonListItemView.ACCESSORY_TYPE_NONE,
//                        ViewGroup.LayoutParams.WRAP_CONTENT);
////            int paddingVer = QMUIDisplayHelper.dp2px(getMyContext(), 12);
////            itemWithDetailBelow.setPadding(itemWithDetailBelow.getPaddingLeft(), paddingVer,
////                    itemWithDetailBelow.getPaddingRight(), paddingVer);
//                propertySection.addItemView(itemWithDetailBelow, onClickListener);
////            System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
//            }
//            propertySection.addTo(groupListView);
//        }
//
//        if (scholar.relationList.size() > 0) {
//            QMUIGroupListView.Section relationSection = QMUIGroupListView.newSection(getMyContext()).setTitle("关系");
//            for (Relation r : scholar.relationList) {
////            r.display();
//                String key = r.relation;
//                if (r.forward) {
//                    key += "    =>";
//                } else {
//                    key += "    <=";
//                }
//                String val = r.label;
//                QMUICommonListItemView itemWithDetailBelow = groupListView.createItemView(null,
//                        key,
//                        val,
//                        QMUICommonListItemView.HORIZONTAL,
//                        QMUICommonListItemView.ACCESSORY_TYPE_NONE,
//                        ViewGroup.LayoutParams.WRAP_CONTENT);
////            int paddingVer = QMUIDisplayHelper.dp2px(getMyContext(), 12);
////            itemWithDetailBelow.setPadding(itemWithDetailBelow.getPaddingLeft(), paddingVer,
////                    itemWithDetailBelow.getPaddingRight(), paddingVer);
//                itemWithDetailBelow.setMinHeight(100);
//                relationSection.addItemView(itemWithDetailBelow, onClickListener);
//            }
//            relationSection.addTo(groupListView);
//        }

//        while (groupListView.getSectionCount() > 0) {
//            System.out.println(groupListView.getSectionCount());
//            if (groupListView.getSection(0) != null) {
//                System.out.println("not null");
//            }
//            groupListView.getSection(0)
//                    .removeFrom(groupListView);
//        }
    }
}
