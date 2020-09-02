package com.java.zhangzhexin;

abstract public class BasePresenter<V extends BaseView> {
    private V myView;

    public void attach(V view) {
        myView = view;
    }

    public void detach() {
        myView = null;
    }

    public V getView(){
        return myView;
    }

}
