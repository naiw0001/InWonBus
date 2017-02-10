package com.example.inwon.inwonbus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.inwon.inwonbus.database.Sqlite_search;

/**
 * Created by inwon on 2017-02-07.
 */

public class Main_stationFragment extends Fragment {
    private int station_list;
    private String[] list,text,id;
    LinearLayout[] mainl;
    LinearLayout main;
    Sqlite_search search;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_stationfragment,null);
        main = (LinearLayout)view.findViewById(R.id.station_list_layout);
        search = new Sqlite_search(getActivity(),"search_list.db",null,1);
        station_list = search.select_station_list().length;
        makelayout(station_list);
        return view;
    }
    private void makelayout(int size){
        mainl = new LinearLayout[size];
        TextView[] textv = new TextView[size];
        ImageView[] imgv = new ImageView[size];
        text = new String[size];
        id = new String[size];
        list = new String[size];
        LinearLayout.LayoutParams mainp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams textp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,6);
        LinearLayout.LayoutParams imgp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
        list = search.select_bus_list();
        maketext();
        for(int i = 0;i<size;i++){
            textv[i] = new TextView(getActivity());
            imgv[i] = new ImageView(getActivity());
            mainl[i] = new LinearLayout(getActivity());
            textv[i].setLayoutParams(textp);
            textv[i].setText(text[i]);
            textv[i].setTextSize(20f);
            textv[i].setGravity(Gravity.CENTER);
            imgv[i].setLayoutParams(imgp);
            imgv[i].setImageResource(R.drawable.delete_img);
            mainl[i].setOrientation(LinearLayout.HORIZONTAL);
            mainl[i].setLayoutParams(mainp);
            mainl[i].addView(textv[i]);
            mainl[i].addView(imgv[i]);
            main.addView(mainl[i]);
        }
    }

    private void maketext(){
        for(int i = 0 ;i<list.length;i++) {
            int temp = list[i].indexOf(",");
            text[i] = list[i].substring(0,temp);
            id[i] = list[i].substring(temp+1,list[i].length());
        }
    }

}

