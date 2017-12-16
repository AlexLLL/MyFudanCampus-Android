package com.example.myfudancampus;

/**
 * Created by alex on 2017/12/1.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BusFragment extends Fragment {


    List<BusModel> resultList = new ArrayList<>();
    BusAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View busView = inflater.inflate(R.layout.fragment_bus, container, false);
        RecyclerView newsRecyclerView = (RecyclerView) busView.findViewById(R.id.bus_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsRecyclerView.setLayoutManager(layoutManager);
        getBus();
        adapter = new BusAdapter(resultList);
        newsRecyclerView.setAdapter(adapter);
        return busView;
    }

    private void getBus() {
        try {
            JSONObject obj = new JSONObject(loadJsonFromAsset());
            JSONArray myJsonArray = obj.getJSONArray("bus");

            for (int i = 0; i < myJsonArray.length(); i++) {
                JSONObject myJsonObject = myJsonArray.getJSONObject(i);
                BusModel model = new BusModel();
                model.setTime1(myJsonObject.getString("time1"));
                model.setTo(myJsonObject.getString("to"));
                model.setTime2(myJsonObject.getString("time2"));
                resultList.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String loadJsonFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("bus.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}