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

public class NewsFragment extends Fragment {

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
        public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            return holder;
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            NewsModel news = mNewsList.get(position);
            if(position % 2 == 0){
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
        getList = getHtml();
        return getList;
    }

    private List<NewsModel> getHtml() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://www.career.fudan.edu.cn/jsp/career_talk_list.jsp?count=50&list=true")
                            .build();
                    Response response = client.newCall(request).execute();
                    String resultString = response.body().string();
                    htmlList = getResult(resultString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return htmlList;
    }
    private List<NewsModel> getResult(final String response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Pattern pattern1 = Pattern.compile("(1\" title=\")(.*?)(\">)");
                Pattern pattern2 = Pattern.compile("(m3\">)(.*?)(</)");
                Pattern pattern3 = Pattern.compile("(5\" title=\")(.*?)(\">)");
                Pattern pattern4 = Pattern.compile("(m4\">)(.*?)(</)");
                Matcher matcher1 = pattern1.matcher(response);
                Matcher matcher2 = pattern2.matcher(response);
                Matcher matcher3 = pattern3.matcher(response);
                Matcher matcher4 = pattern4.matcher(response);
                ArrayList<String> matched1 = new ArrayList<String>();
                ArrayList<String> matched2 = new ArrayList<String>();
                ArrayList<String> matched3 = new ArrayList<String>();
                ArrayList<String> matched4 = new ArrayList<String>();
                while(matcher1.find() && matcher2.find() && matcher3.find() && matcher4.find()){
                    matched1.add(matcher1.group());
                    matched2.add(matcher2.group());
                    matched3.add(matcher3.group());
                    matched4.add(matcher4.group());
                }
                for(int i = 0;i < matched1.size(); i ++){
                    NewsModel news = new NewsModel();
                    Log.v("abc", matched1.get(i));
                    news.setName("  "+matched1.get(i).substring(10,matched1.get(i).length()-2));
                    news.setData("  "+matched2.get(i).substring(4,matched2.get(i).length()-2)+" "+matched4.get(i).substring(4,matched4.get(i).length()-2)+" "+matched3.get(i).substring(10,matched3.get(i).length()-2));
                    resultList.add(news);
                }
            }
        });
        return resultList;
    }
}
