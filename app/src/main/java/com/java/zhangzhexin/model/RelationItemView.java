package com.java.zhangzhexin.model;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

public class RelationItemView extends QMUICommonListItemView {

//    private ImageView mForwardView;
//    private ImageView mBackwardView;

    public RelationItemView(Context context, String relation, boolean forward, String label, int height) {
        super(context);
        setOrientation(HORIZONTAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));

        if (forward) {
            relation += "\t=>";
        } else {
            relation += "\t<=";
        }

        setText(relation);
        setDetailText(label);
        setAccessoryType(ACCESSORY_TYPE_NONE);
        setTipPosition(TIP_POSITION_LEFT);

//        addTip(forward);
    }

//    void addTip(boolean forward){
//
//    }
}
