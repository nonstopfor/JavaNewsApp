package com.java.zhangzhexin.overview.newslist;

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
import com.java.zhangzhexin.model.NewsCard;

import java.util.List;

public class NewsListFragment extends BaseFragment<NewsListView,NewsListPresenter> implements NewsListView {
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private LinearLayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout; //下拉刷新
    private View view = null;
    private boolean isFirstLoad = true;

    public NewsListFragment(){}

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

    public static NewsListFragment newInstance(String type, String keyword) {
        //System.out.println("newInstance NewsListFragment, type = "+type+" keyword = "+keyword);
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        args.putString("type",type);
        args.putString("keyword",keyword);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("NewsListFragment : "+type+" onCreateView");
        if(view == null)
        {
            System.out.println("NewsListFragment: "+type+" view为空");
            view = inflater.inflate(R.layout.fragment_newslist, container, false);
            initView();
            initSet();
        }
        else
            System.out.println("NewsListFragmetn: "+type+" view非空");

        return view;
    }

    public void initView(){
        recyclerView = view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
    }

    public void initSet(){
        adapter = new NewsAdapter(getContext(),type);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        //点击事件
        recyclerView.addOnItemTouchListener(new NewsListener(getContext(),recyclerView, (news, position) -> {
            TextView title = news.findViewById(R.id.news_title);
            if(!type.equals("history"))
                title.setTextColor(getResources().getColor(R.color.colorReadNews)); //改变颜色
            myPresenter.openNewsDetail(adapter.getNews(position));
            System.out.println("点击位置"+position);
        }));

        //上拉获取更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastItemPosition = layoutManager.findLastVisibleItemPosition();
                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastItemPosition == layoutManager.getItemCount()-1){
                    System.out.println("arrive last item!");
                    myPresenter.getMoreNews(20);
                }
                //当前在滚动的recyclerView,  当前滚动状态
                /*
                newState有三种值
                SCROLL_STATE_IDLE = 0； 静止没有滚动
                SCROLL_STATE_DRAGGING = 1; 外部拖拽(用户手指)
                SCROLL_STATE_SETTLING = 2; 自动滚动
                */
            }
        });


        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener(() -> {
            System.out.println("refresh!");
            swipeRefreshLayout.setRefreshing(true);
            myPresenter.refreshNews(20);
            swipeRefreshLayout.setRefreshing(false); //FIXME:需要手动关闭动画
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("NewsListFragment : "+type+" onResume");
        if(isFirstLoad || type.equals("history")) { //浏览记录每次都要刷新
            myPresenter.refreshNews(20);
            isFirstLoad = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("NewsListFragment: "+type+" onDestroyView");
        isFirstLoad = true; //重置
    }

    @Override
    public void resetNewsList(List<NewsCard> data) {
        adapter.resetData(data);
    }

    @Override
    public void appendNewsList(List<NewsCard> data) {
        adapter.appendData(data);
    }

    @Override
    public NewsListPresenter createPresenter() {
        //System.out.println("in createPresenter, type = "+type+", keyword = "+keyword);
        return new NewsListPresenter(type,keyword);
    }

    @Override
    public void start(Intent intent) {

        startActivity(intent);
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }
}
