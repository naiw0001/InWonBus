package com.example.inwon.inwonbus;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {

    private String url_busNum = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList";
    private String key = "zadOGy8xBhJVBYYhLSPbfGoQHfJ1mAFAOQADCvNFSWWshGWcmf1St10W17yKjJtVEOR4hpjtrYZdBk32bLFO6w%3D%3D";
    private String xmlURL = url_busNum + "?serviceKey=" + key;
    private String BusRouteID, BusNumber;
    private EditText u_Input,st_input;
    private Button send,st_send;
    ArrayList<String> busNumarray = new ArrayList<String>();
    ArrayList<String> busRouteIdarray = new ArrayList<String>();
    ArrayList<String> startstationarray = new ArrayList<String>();
    ArrayList<String> endstationarray = new ArrayList<String>();
    BusNumSearch busNumSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        u_Input = (EditText) findViewById(R.id.edit);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = u_Input.getText().toString();
                busNumSearch = new BusNumSearch();
                busNumSearch.execute(temp);
                busnum();
            }
        });
        st_input = (EditText)findViewById(R.id.edit_station);
        st_send = (Button)findViewById(R.id.send_station);
        st_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = st_input.getText().toString();
            }
        });

    }
public void busnum(){
    while(true) {
        try {
            Thread.sleep(100);
            if (busNumSearch.flag == true) {
                busNumarray = busNumSearch.busNumarr;
                busRouteIdarray = busNumSearch.busRouteIdarr;
                startstationarray = busNumSearch.startstationarr;
                endstationarray = busNumSearch.endstationarr;
                break;
            }
        }catch (Exception e){}
    }
    Intent intent = new Intent(MainActivity.this,BusList.class);
    intent.putStringArrayListExtra("busnum",busNumarray);
    intent.putStringArrayListExtra("busRouteId",busRouteIdarray);
    intent.putStringArrayListExtra("startstation",startstationarray);
    intent.putStringArrayListExtra("endstation",endstationarray);
    startActivity(intent);

}


}
