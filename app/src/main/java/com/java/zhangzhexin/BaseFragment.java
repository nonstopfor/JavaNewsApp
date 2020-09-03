package com.java.zhangzhexin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends Fragment implements BaseView {
    protected P myPresenter;
    protected String type;
    protected String keyword;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("enter basefragment constructor");
        super.onCreate(savedInstanceState);
        if (myPresenter == null) {
            myPresenter = createPresenter();
        }
        if (myPresenter == null) {
            throw new NullPointerException("presenter can't be null!");
        }
        myPresenter.attach((V) this);
        System.out.println("finish contructing BaseFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myPresenter != null) {
            myPresenter.detach();
        }
    }

    public abstract P createPresenter();

}
