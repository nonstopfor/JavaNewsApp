package com.java.zhangzhexin;

import com.java.zhangzhexin.model.DataManager;
import com.java.zhangzhexin.model.UrlManager;

abstract public class BasePresenter<V extends BaseView> {
    protected V myView;

    public void attach(V view) {
        myView = view;
    }

    public void detach() {
        myView = null;
    }

}
