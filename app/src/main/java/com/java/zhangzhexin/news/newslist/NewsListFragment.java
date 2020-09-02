package com.java.zhangzhexin.news.newslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.java.zhangzhexin.R;

public class NewsListFragment extends Fragment {
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private LinearLayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout; //下拉刷新


    public NewsListFragment(){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NewsAdapter();
        adapter.setData(new String[]{"news1", "news2", "news3","news4","news1", "news2", "news3","news4","news1", "news2", "news3","news4","news1", "news2", "news3","news4"});
        layoutManager = new LinearLayoutManager(getContext());
    }

    public static NewsListFragment newInstance() {
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newslist,container,false);

        //设置newslist
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //点击事件
        recyclerView.addOnItemTouchListener(new NewsListener(getContext(),recyclerView, new NewsListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //TODO:点击事件的处理
                TextView title = view.findViewById(R.id.news_title);
                title.setTextColor(getResources().getColor(R.color.colorClickedNews));
                //title.setTextColor(getResources().getColor(R.color.colorTabSelected));//TODO:灰色不太明显
                System.out.println("点击位置"+position);
            }
        }));

        //上拉获取更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //TODO:秦岳组还重写了onScroll 可能是为了处理在滑动过程中到达底部的情况?
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastItemPosition = layoutManager.findLastVisibleItemPosition();
                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastItemPosition == layoutManager.getItemCount()-1){

                    System.out.println("arrive last item!");
                    //TODO:获取更多新闻->显示
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
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //System.out.println("catch refresh!");
                swipeRefreshLayout.setRefreshing(true);
                adapter.setData(new String[]{"news4", "news5", "news6","news7","news4", "news5", "news6","news7","news4", "news5", "news6","news7","news4", "news5", "news6","news7"});
                //TODO:改为统一的接口setNews： 得到新数据->adapter.setData
                swipeRefreshLayout.setRefreshing(false); //需要手动关闭动画
            }
        });
        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }
}
