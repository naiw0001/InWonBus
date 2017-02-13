package com.example.inwon.inwonbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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

public class Main_busFragment extends Fragment {

    private int bus_list;
    private String[] list, text, id, start_s, end_s;
    Sqlite_search search;
    private ListView listview;
    private BussearchAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_busfragmet, null);
        search = new Sqlite_search(getActivity(), "search_list.db", null, 1);
        bus_list = search.select_bus_list().length;
        listview = (ListView) view.findViewById(R.id.buslist);
        adapter = new BussearchAdapter();
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(click);
        if(bus_list != 0 ) {
            makelayout(bus_list);
        }
        return view;
    }

    private void makelayout(int size) { // list layout

        list = search.select_bus_list();
        text = new String[size];
        id = new String[size];
        start_s = new String[size];
        end_s = new String[size];
        maketext();
        for(int i=0; i<size ;i++){
            adapter.additme(text[i],ContextCompat.getDrawable(getActivity(),R.drawable.delete_img));
        }
        adapter.notifyDataSetChanged();
    }

    private void maketext() {
        for (int i = 0; i < list.length; i++) {
            int temp = list[i].indexOf(",");
            int temp2 = list[i].indexOf("|");
            int temp3 = list[i].indexOf("/");
            text[i] = list[i].substring(0, temp);
            id[i] = list[i].substring(temp + 1, temp2);
            start_s[i] = list[i].substring(temp2 + 1, temp3);
            end_s[i] = list[i].substring(temp3 + 1, list[i].length());
        }
    }
//
//    View.OnClickListener textclick = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(getActivity(), BusActivity.class);
//            intent.putExtra("num", text[v.getId()].toString());
//            intent.putExtra("routeid", id[v.getId()].toString());
//            intent.putExtra("start", start_s[v.getId()].toString());
//            intent.putExtra("end", end_s[v.getId()].toString());
//            startActivity(intent);
//        }
//    };
//
//    View.OnClickListener imgclick = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            String bus_id = id[v.getId()].toString();
//            search.delete_bus_list(bus_id);
//
//        }
//    };
  AdapterView.OnItemClickListener click = new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long iid) {
        Intent intent = new Intent(getActivity(), BusActivity.class);
            intent.putExtra("num", text[position].toString());
            intent.putExtra("routeid", id[position].toString());
            intent.putExtra("start", start_s[position].toString());
            intent.putExtra("end", end_s[position].toString());
            startActivity(intent);
    }
};

}
