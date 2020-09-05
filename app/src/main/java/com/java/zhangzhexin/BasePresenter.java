package com.java.zhangzhexin;

abstract public class BasePresenter<V extends BaseView> {
    protected V myView;

    public void attach(V view) {
        myView = view;
    }

    public void detach() {
        myView = null;
    }

}
