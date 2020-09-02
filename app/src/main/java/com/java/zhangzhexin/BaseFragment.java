package com.java.zhangzhexin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends Fragment implements BaseView {
    private P myPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (myPresenter == null) {
            myPresenter = createPresenter();
        }
        if (myPresenter == null) {
            throw new NullPointerException("presenter can't be null!");
        }
        myPresenter.attach((V) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myPresenter != null) {
            myPresenter.detach();
        }
    }

    public abstract P createPresenter();

    public P getPresenter(){
        return myPresenter;
    }
}
