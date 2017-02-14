package com.example.inwon.inwonbus;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.inwon.inwonbus.database.Sqlite_search;

/**
 * Created by inwon on 2017-02-07.
 */

public class Main_stationFragment extends Fragment {
    private int station_list;
    private String[] list, text, id;
    private Sqlite_search search;
    private ListView listview;
    private StationsearchAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_stationfragment, null);
        search = new Sqlite_search(getActivity(), "search_list.db", null, 1);
        station_list = search.select_station_list().length;
        listview = (ListView)view.findViewById(R.id.stationlist) ;
        adapter = new StationsearchAdapter();
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(click);
        if(station_list != 0 ) {
            makelayout(station_list);
        }
        return view;
    }

    private void makelayout(int size) {

        text = new String[size];
        id = new String[size];
        list = new String[size];
        list = search.select_station_list();
        maketext();
        for (int i = 0; i < size; i++) {
            adapter.additme(text[i], ContextCompat.getDrawable(getActivity(),R.drawable.delete_img));
        }
        adapter.notifyDataSetChanged();
    }

    private void maketext() {
        for (int i = 0; i < list.length; i++) {
            int temp = list[i].indexOf(",");
            text[i] = list[i].substring(0, temp);
            id[i] = list[i].substring(temp + 1, list[i].length());
        }
    }
AdapterView.OnItemClickListener click = new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long iid) {
        Intent intent = new Intent(getActivity(), BusStationActivity.class);
        intent.putExtra("station",text[position].toString());
        intent.putExtra("station_num",id[position].toString());
        startActivity(intent);
    }
};

}

