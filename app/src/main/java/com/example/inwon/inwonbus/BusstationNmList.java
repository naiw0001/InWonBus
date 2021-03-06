package com.example.inwon.inwonbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.inwon.inwonbus.database.Sqlite_search;

import java.util.ArrayList;


/**
 * Created by inwon on 2017-02-04.
 */

public class BusstationNmList extends AppCompatActivity {
    private ListView stationlist;
    private ListViewAdapter adapter;
    private BusstationNm busstationNm;
    private ArrayList<String> stationname, stationid;
    private String s_name;
    private Sqlite_search search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busstationname);
        search = new Sqlite_search(getApplicationContext(), "search_list.db", null, 1);
        Intent intent = getIntent();
        s_name = intent.getStringExtra("name");
        stationlist = (ListView) findViewById(R.id.list);
        stationname = new ArrayList<>();
        stationid = new ArrayList<>();
        adapter = new ListViewAdapter();
        stationlist.setAdapter(adapter);
        busstationNm = new BusstationNm();
        busstationnm();
        adapteradd();
        stationlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent(BusstationNmList.this, BusStationActivity.class);
                intent1.putExtra("station", stationname.get(position).toString());
                intent1.putExtra("station_num", stationid.get(position).toString());
                search.insert_station_list(stationname.get(position).toString(), stationid.get(position).toString());
                startActivity(intent1);
            }
        });
    }

    public void busstationnm() {
        busstationNm.execute(s_name);
        while (true) {
            try {
                Thread.sleep(100);
                if (busstationNm.flag == true) {
                    stationname = busstationNm.stNmArr;
                    stationid = busstationNm.arsIdArr;
                    break;
                }
            } catch (Exception e) {
            }
        }
    }

    public void adapteradd() {
        for (int i = 0; i < stationname.size(); i++) {
            String name = stationname.get(i).toString();
            String id = stationid.get(i).toString();
            adapter.additme(name, id);
            adapter.notifyDataSetChanged();
        }
    }

}
