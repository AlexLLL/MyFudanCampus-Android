package com.example.myfudancampus;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alex on 2017/12/3.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsModel> mNewsList;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView newsNameText;
        TextView newsDataText;
        TextView newsLoactionText;
        View listView;
        public ViewHolder(View newsView) {
            super(newsView);
            newsNameText = (TextView) newsView.findViewById(R.id.news_Name);
            newsDataText = (TextView) newsView.findViewById(R.id.news_Data);
            newsLoactionText = (TextView) newsView.findViewById(R.id.news_Location);
            listView = newsView;
        }
    }
    public NewsAdapter(List<NewsModel> newsList) {
        mNewsList = newsList;
    }
    @Override
    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    public void setData(List<NewsModel> viewData) {
        mNewsList.clear();
        mNewsList.addAll(viewData);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NewsModel news = mNewsList.get(position);
        holder.setIsRecyclable(true);
        if(position % 2 == 0){
            holder.listView.setBackgroundColor(0x80E0EEEE);
        } else {
            holder.listView.setBackgroundColor(0XFFFFFF);
        }
        holder.newsNameText.setText(news.getName());
        holder.newsDataText.setText(news.getData());
        holder.newsLoactionText.setText(news.getLocation());
    }
    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

}
