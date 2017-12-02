package com.example.myfudancampus;

/**
 * Created by alex on 2017/12/1.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GPAFragment extends Fragment {

    List<NewsModel> resultList = new ArrayList<>();
    List<NewsModel> htmlList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View newsView = inflater.inflate(R.layout.fragment_news, container, false);
        RecyclerView newsRecyclerView = (RecyclerView) newsView.findViewById(R.id.news_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        newsRecyclerView.setAdapter(adapter);
        return newsView;

    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        private List<NewsModel> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView newsNameText;
            TextView newsDataText;
            View listView;

            public ViewHolder(View newsView) {
                super(newsView);
                newsNameText = (TextView) newsView.findViewById(R.id.news_Name);
                newsDataText = (TextView) newsView.findViewById(R.id.news_Data);
                listView = newsView;
            }
        }

        public NewsAdapter(List<NewsModel> newsList) {
            mNewsList = newsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            NewsModel news = mNewsList.get(position);
            if (position % 2 == 0) {
                holder.listView.setBackgroundColor(getResources().getColor(R.color.grayblue));
            }
            holder.newsNameText.setText(news.getName());
            holder.newsDataText.setText(news.getData());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }

    private List<NewsModel> getNews() {
        List<NewsModel> getList = new ArrayList<>();
        getList = getData();
        return getList;
    }

    private List<NewsModel> getData() {
        List<NewsModel> getList = new ArrayList<>();

        return getList;
    }
}