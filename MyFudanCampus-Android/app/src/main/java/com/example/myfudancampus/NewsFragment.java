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
import android.os.AsyncTask;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Call;
import okhttp3.Callback;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;

public class NewsFragment extends Fragment {

    List<NewsModel> resultList = new ArrayList<>();
    List<NewsModel> htmlList = new ArrayList<>();
    NewsAdapter adapter;
    public static final String TAG = "AAAAAA";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View newsView = inflater.inflate(R.layout.fragment_news, container, false);
        RecyclerView newsRecyclerView = (RecyclerView) newsView.findViewById(R.id.news_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsRecyclerView.setLayoutManager(layoutManager);
        adapter = new NewsAdapter(resultList);
        newsRecyclerView.setAdapter(adapter);
        getNews();
        return newsView;

    }


    private void getNews() {
        AsyncTask<Void,Void,String> asyncTask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(final Void... voids) {
                String resultString = null;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://www.career.fudan.edu.cn/jsp/career_talk_list.jsp?count=50&list=true")
                        .build();
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    resultString = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return resultString;
            }

            @Override
            protected void onPostExecute(final String resultString) {
                super.onPostExecute(resultString);
                resultList.clear();
                resultList.addAll(getResult(resultString));
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }

    private List<NewsModel> getResult(final String response) {
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
                        news.setName("  "+matched1.get(i).substring(10,matched1.get(i).length()-2));
                        news.setData(matched2.get(i).substring(4,matched2.get(i).length()-2)+" "+matched4.get(i).substring(4,matched4.get(i).length()-2)+" ");
                        news.setLocation("  "+matched3.get(i).substring(10,matched3.get(i).length()-2));
                        htmlList.add(news);
                    }
        return htmlList;
    }
}
