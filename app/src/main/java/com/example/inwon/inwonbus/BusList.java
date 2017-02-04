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

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by inwon on 2017-01-25.
 */

public class BusList extends AppCompatActivity{
    ListView buslist;
    ArrayList<String> busnumlist,busrouteid,startstation,endstation;
    ArrayAdapter adapter;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buslist);
//        busnumlist = new ArrayList<String>();
        buslist = (ListView)findViewById(R.id.buslist);
        intent = getIntent();
        busnumlist = intent.getStringArrayListExtra("busnum");
        busrouteid = intent.getStringArrayListExtra("busRouteId");
        startstation = intent.getStringArrayListExtra("startstation");
        endstation = intent.getStringArrayListExtra("endstation");
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,busnumlist);
        buslist.setAdapter(adapter);


        buslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BusList.this,BusActivity.class);
                intent.putExtra("num",busnumlist.get(position));
                intent.putExtra("routeid",busrouteid.get(position));
                intent.putExtra("start",startstation.get(position));
                intent.putExtra("end",endstation.get(position));
                startActivity(intent);
            }
        });
    }
}
