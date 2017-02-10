package com.example.inwon.inwonbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.inwon.inwonbus.database.Sqlite_search;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by inwon on 2017-01-25.
 */

public class BusList extends AppCompatActivity{
    ListView buslist;
    ArrayAdapter adapter;
    Intent intent;
    String busnum;

    ArrayList<String> busNumarray = new ArrayList<String>();
    ArrayList<String> busRouteIdarray = new ArrayList<String>();
    ArrayList<String> startstationarray = new ArrayList<String>();
    ArrayList<String> endstationarray = new ArrayList<String>();

    BusNumSearch busNumSearch;

    Sqlite_search search;

    public void busnum(String num) {
        busNumSearch.execute(num);
        while (true) {
            try {
                Thread.sleep(100);
                if (busNumSearch.flag == true) {
                    busNumarray = busNumSearch.busNumarr;
                    busRouteIdarray = busNumSearch.busRouteIdarr;
                    startstationarray = busNumSearch.startstationarr;
                    endstationarray = busNumSearch.endstationarr;
                    break;
                }
            } catch (Exception e) {
            }
        }

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buslist);
        search = new Sqlite_search(getApplicationContext(),"search_list.db",null,1);
        buslist = (ListView)findViewById(R.id.buslist);
        intent = getIntent();
        busnum = intent.getStringExtra("bus");
        busNumSearch = new BusNumSearch();
        busnum(busnum);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,busNumarray);
        buslist.setAdapter(adapter);

        buslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BusList.this,BusActivity.class);
                intent.putExtra("num",busNumarray.get(position).toString());
                intent.putExtra("routeid",busRouteIdarray.get(position).toString());
                intent.putExtra("start",startstationarray.get(position).toString());
                intent.putExtra("end",endstationarray.get(position).toString());
                search.insert_bus_list(busNumarray.get(position).toString(),busRouteIdarray.get(position).toString(),
                        startstationarray.get(position).toString(),endstationarray.get(position).toString());
                startActivity(intent);
            }
        });
    }
}
