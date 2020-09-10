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
    //private SwipeRefreshLayout swipeRefreshLayout; //下拉刷新
    private SmartRefreshLayout refreshLayout;
    private View view = null;
    private boolean isFirstLoad = true;

    public MyListFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        type = getArguments().getString("type");
        keyword = getArguments().getString("keyword");
        System.out.println("NewsListFragment : "+type+" onCreate!");
        //System.out.println("newslistfragment arguments = "+getArguments());
        super.onCreate(savedInstanceState);
        //System.out.println("newslistfragment type = "+type+", keyword = "+keyword);
        //FIXME:转移 visible时create
//        try {
//            myPresenter.refreshNews(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //adapter.setData(new String[]{"news1", "news2", "news3","news4","news1", "news2", "news3","news4","news1", "news2", "news3","news4","news1", "news2", "news3","news4","news1","news2","news3","news4"});

    }

//    public static MyListFragment newInstance(String type, String keyword) {
//        //System.out.println("newInstance NewsListFragment, type = "+type+" keyword = "+keyword);
//        Bundle args = new Bundle();
//        MyListFragment fragment = new MyListFragment();
//        args.putString("type",type);
//        args.putString("keyword",keyword);
//        fragment.setArguments(args);
//        return fragment;
//    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("ListFragment : "+type+" onCreateView");
        if(view == null)
        {
            isFirstLoad = true;
            System.out.println("ListFragment: "+type+" view为空");
            view = inflater.inflate(R.layout.fragment_list, container, false);
            initView();
            initSet();
        }
        else
            System.out.println("ListFragment: "+type+" view非空");

        return view;
    }

    public void initView(){
        recyclerView = view.findViewById(R.id.recyclerView);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        //swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
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
            System.out.println("点击位置"+position);
        }));

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                System.out.println("检测到下拉刷新");
                myPresenter.refreshData(20);
                refreshLayout.finishRefresh(true);
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                System.out.println("检测到上拉获取更多 ");
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                refreshLayout.finishLoadMore(500);
                myPresenter.getMoreData(20);
            }
        });
        //TODO:改监听器
        //上拉获取更多
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                int lastItemPosition = layoutManager.findLastVisibleItemPosition();
//                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastItemPosition == layoutManager.getItemCount()-1){
//                    System.out.println("arrive last item!");
//                    myPresenter.getMoreData(20);
//                }
//                //当前在滚动的recyclerView,  当前滚动状态
//                /*
//                newState有三种值
//                SCROLL_STATE_IDLE = 0； 静止没有滚动
//                SCROLL_STATE_DRAGGING = 1; 外部拖拽(用户手指)
//                SCROLL_STATE_SETTLING = 2; 自动滚动
//                */
//            }
//        });


//        //下拉刷新
//        swipeRefreshLayout.setOnRefreshListener(() -> {
//            System.out.println("refresh!");
//            swipeRefreshLayout.setRefreshing(true);
//            myPresenter.refreshData(20);
//            swipeRefreshLayout.setRefreshing(false); //FIXME:需要手动关闭动画
//        });

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("ListFragment : "+type+" onResume");
        //不用isFirstLoad 因为fragment会被destroyView  只要view非空 && 不是历史 就不刷新
      if(isFirstLoad|| type.equals("history")) { //浏览记录每次都要刷新
            myPresenter.refreshData(20);
            isFirstLoad = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("ListFragment: "+type+" onDestroyView");
        //isFirstLoad = true; //重置
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
