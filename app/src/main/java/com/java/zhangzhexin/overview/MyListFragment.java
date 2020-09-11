package com.java.zhangzhexin.overview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.java.zhangzhexin.BaseFragment;
import com.java.zhangzhexin.R;
import com.java.zhangzhexin.model.BaseCard;
import com.java.zhangzhexin.model.BaseManager;
import com.java.zhangzhexin.model.NewsCard;
import com.java.zhangzhexin.overview.newslist.NewsAdapter;
import com.java.zhangzhexin.overview.newslist.NewsListPresenter;
import com.java.zhangzhexin.overview.newslist.NewsListView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public abstract class MyListFragment<VH extends RecyclerView.ViewHolder,Adapter extends ListAdapter<VH,C>,C extends BaseCard,M extends BaseManager,V extends MyListView<C>, P extends MyListPresenter<M,C>> extends BaseFragment<MyListView<C>,MyListPresenter<M,C>> implements MyListView<C> {
    private RecyclerView recyclerView;
    private Adapter adapter;

    private LinearLayoutManager layoutManager;
    private SmartRefreshLayout refreshLayout;
    private View view = null;
    private boolean isFirstLoad = true;

    public MyListFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        type = getArguments().getString("type");
        keyword = getArguments().getString("keyword");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        System.out.println("ListFragment : "+type+" onCreateView");
        if(view == null)
        {
            isFirstLoad = true;
//            System.out.println("ListFragment: "+type+" view为空");
            view = inflater.inflate(R.layout.fragment_list, container, false);
            initView();
            initSet();
        }
        else{
            //            System.out.println("ListFragment: "+type+" view非空");
        }

        return view;
    }

    public void initView(){
        recyclerView = view.findViewById(R.id.recyclerView);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        adapter = createAdapter();
    }

    public abstract Adapter createAdapter();

    public void initSet(){
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //点击事件
        recyclerView.addOnItemTouchListener(new MyListListener(getContext(),recyclerView, (dataView, position) -> {
            myPresenter.openDetail(dataView,adapter.getData(position));
//            System.out.println("点击位置"+position);
        }));

        refreshLayout.setEnableAutoLoadMore(false);

        refreshLayout.setOnRefreshListener(refreshLayout -> {
//            System.out.println("检测到下拉刷新");
            myPresenter.refreshData(13);
            refreshLayout.finishRefresh(true);
        });

        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
//            System.out.println("检测到上拉获取更多 ");
            myPresenter.getMoreData(13);
            refreshLayout.finishLoadMore(200);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        System.out.println("ListFragment : "+type+" onResume");
        if(isFirstLoad|| type.equals("history")) { //浏览记录每次都要刷新
            myPresenter.refreshData(13);
            isFirstLoad = false;
        }
    }

    @Override
    public void start(Intent intent) {
        startActivity(intent);
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    public void resetList(List<C> data) {
        adapter.resetData(data);
    }

    @Override
    public void appendList(List<C> data) {
        adapter.appendData(data);
    }
}
