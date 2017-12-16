package com.example.myfudancampus;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by alex on 2017/12/16.
 */

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.ViewHolder> {
    private List<BusModel> mBusList;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView busTime1Text;
        TextView busToText;
        TextView busTime2Text;
        View listView;
        public ViewHolder(View view) {
            super(view);
            busTime1Text = (TextView) view.findViewById(R.id.bus_Time1);
            busToText = (TextView) view.findViewById(R.id.bus_To);
            busTime2Text = (TextView) view.findViewById(R.id.bus_Time2);
            listView = view;
        }
    }
    public BusAdapter(List<BusModel> busList) {
        mBusList = busList;
    }
    @Override
    public BusAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bus_item, parent, false);
        final BusAdapter.ViewHolder holder = new BusAdapter.ViewHolder(view);
        return holder;
    }

    public void setData(List<BusModel> viewData) {
        mBusList.clear();
        mBusList.addAll(viewData);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BusAdapter.ViewHolder holder, int position) {
        BusModel news = mBusList.get(position);
        holder.setIsRecyclable(true);
        if(position % 2 == 0){
            holder.listView.setBackgroundColor(0x0c1e90ff);
        } else {
            holder.listView.setBackgroundColor(0XFFFFFF);
        }
        holder.busTime1Text.setText(news.getTime1());
        holder.busToText.setText(news.getTo());
        holder.busTime2Text.setText(news.getTime2());
    }
    @Override
    public int getItemCount() {
        return mBusList.size();
    }

}
