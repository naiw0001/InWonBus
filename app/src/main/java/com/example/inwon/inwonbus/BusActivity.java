package com.example.inwon.inwonbus;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by inwon on 2017-01-25.
 */

public class BusActivity extends AppCompatActivity {

    private Intent intent;
    private String num, routeid, start, end;
    private TextView busnum, busstation;
    ArrayList<String> busstationarray, busstationNoarray;
    ArrayList<String> stopFlagArr, plainNoArr, islastArr, nextStTmArr, sectOrdArr, isrunynArr;
    BusStation busStationAv;
    LinearLayout busstation_layout;
    BusLocation busLocation;
    ImageView busimg[];
    TextView busNo[];
    int bus, sectNo;
    int layoutid;
    int stationid;
    LinearLayout mainlayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        intent = getIntent();
        num = intent.getStringExtra("num");
        routeid = intent.getStringExtra("routeid");
        start = intent.getStringExtra("start");
        end = intent.getStringExtra("end");
        bus = 0; // busimg index
        sectNo = 0; // sect index
        busstationarray = new ArrayList<String>();
        busstationNoarray = new ArrayList<String>();

        stopFlagArr = new ArrayList<String>(); // 정류소 도착 0 = n ,1 = y
        plainNoArr = new ArrayList<String>();
        islastArr = new ArrayList<String>(); // 막차 0 = n ,1 = y
        nextStTmArr = new ArrayList<String>(); // 다음정류장 도착시간 (초)
        sectOrdArr = new ArrayList<String>(); // 정류소순번
        isrunynArr = new ArrayList<String>(); // 운행여부
        layoutid = 0;
        busStationAv = new BusStation();
        busLocation = new BusLocation();
        busroutestation();
//        station = (ListView)findViewById(R.id.stationList);
        busnum = (TextView) findViewById(R.id.busNum);
        busstation = (TextView) findViewById(R.id.station);
        busstation_layout = (LinearLayout) findViewById(R.id.busstation);

        busnum.setText(num);
        busstation.setText(start + "↔" + end);

//         stationNo();
//         list();

        buslocation();
        busimg = new ImageView[plainNoArr.size()];
        busNo = new TextView[plainNoArr.size()];
        busimg = new ImageView[plainNoArr.size()];
        busNo = new TextView[plainNoArr.size()];

        for (int i = 0; i < plainNoArr.size(); i++) {
            busimg[i] = new ImageView(this);
            busNo[i] = new TextView(this);
            busimg[i].setImageResource(R.drawable.bus);
            busimg[i].bringToFront();
            busimg[i].setId(i);
            busNo[i].setText(plainNoArr.get(i).toString());
            Log.d("iiiiiiiiiiiiiiii", String.valueOf(busimg[i].getId()));
        }

        buslayout(busstationarray.size());

    }

    public void buslocation() {
        busLocation.execute(routeid);
        while (true) {
            try {
                Thread.sleep(100);
                if (busLocation.flag == true) {
                    islastArr = busLocation.islastArr;
                    isrunynArr = busLocation.isrunynArr;
                    nextStTmArr = busLocation.nextStTmArr;
                    plainNoArr = busLocation.plainNoArr;
                    sectOrdArr = busLocation.sectOrdArr;
                    stopFlagArr = busLocation.stopFlagArr;
                    break;
                }
            } catch (Exception e) {

            }
        }
    }

    public void buslayout(int size) {
        for (int i = 0; i < size; i++) {
            makebuslayout(i);
        }
    }

    View.OnClickListener mainclick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String station = busstationarray.get(v.getId());
            String station_num = busstationNoarray.get(v.getId());
            Toast.makeText(getApplicationContext(), station, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(BusActivity.this, BusStationActivity.class);
            intent.putExtra("station", station);
            intent.putExtra("station_num", station_num);
            startActivity(intent);
        }
    };

    public void makebuslayout(int index) {
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics()); // set dp
        int vheight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 23, getResources().getDisplayMetrics()); // set line dp
        int vwidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics()); // set line dp
        mainlayout = new LinearLayout(this);
        RelativeLayout imglayout = new RelativeLayout(this);
        RelativeLayout textlayout = new RelativeLayout(this);
        LinearLayout textLinear = new LinearLayout(this);
        TextView bus_station_name = new TextView(this);
        TextView bus_id = new TextView(this);
        bus_station_name.setId(stationid);
        stationid++;
        ImageView down = new ImageView(this);
        View line = new View(this);
        View line2 = new View(this);

        LinearLayout.LayoutParams mainp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        LinearLayout.LayoutParams imgp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height, 5);
        LinearLayout.LayoutParams textp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height, 3);
        RelativeLayout.LayoutParams busp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams text = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams downimg = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams busimgp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams busNop = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams linep = new RelativeLayout.LayoutParams(vwidth, vheight);
        RelativeLayout.LayoutParams linep2 = new RelativeLayout.LayoutParams(vwidth, vheight);

        busp.addRule(RelativeLayout.CENTER_VERTICAL);
        mainlayout.setOrientation(LinearLayout.HORIZONTAL);
        mainlayout.setLayoutParams(mainp);
        mainlayout.setOnClickListener(mainclick);
        mainlayout.setId(layoutid);
        layoutid++;
        imglayout.setLayoutParams(imgp);
        textlayout.setLayoutParams(textp);
        textLinear.setOrientation(LinearLayout.VERTICAL);
        textLinear.setLayoutParams(busp);
        busimgp.rightMargin = 40;
        busimgp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        if (sectOrdArr.size() != 0) {
            if (String.valueOf(index + 1).equals(sectOrdArr.get(sectNo).toString()) && stopFlagArr.get(sectNo).toString().equals("1")) {
                busimgp.addRule(RelativeLayout.CENTER_VERTICAL);
                busNop.addRule(RelativeLayout.CENTER_VERTICAL);
                busimg[bus].setLayoutParams(busimgp);
                busNo[bus].setLayoutParams(busNop);
                imglayout.addView(busimg[bus]);
                imglayout.addView(busNo[bus]);
                bus++;
                sectNo++;

            } else if (String.valueOf(index).equals(sectOrdArr.get(sectNo).toString()) && stopFlagArr.get(sectNo).toString().equals("0")) {
                busimg[bus].setLayoutParams(busimgp);
                busNo[bus].setLayoutParams(busNop);
                imglayout.addView(busimg[bus]);
                imglayout.addView(busNo[bus]);
                bus++;
                sectNo++;
            }
        }

        if (sectNo == sectOrdArr.size()) {
            sectNo = 0;
        }

        int id = 0;
        line.setId(id);
        id++; //0
        down.setId(id);
        id++; //1
        line2.setId(id);  // 2

        linep.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        linep.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        linep.rightMargin = 60;
        line.setLayoutParams(linep);
        line.setBackgroundColor(Color.BLUE);

        down.setImageResource(R.drawable.down_circle);
        downimg.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        downimg.addRule(RelativeLayout.CENTER_VERTICAL);
        downimg.rightMargin = 30;
        down.setLayoutParams(downimg);

        line2.setBackgroundColor(Color.RED);
        linep2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        linep2.addRule(RelativeLayout.BELOW, 1);
        linep2.rightMargin = 60;
        line2.setLayoutParams(linep2);

        bus_station_name.setLayoutParams(text);
        bus_id.setLayoutParams(text);
        bus_station_name.setText(busstationarray.get(index).toString());
        bus_id.setText(busstationNoarray.get(index).toString());

        imglayout.addView(line);
        imglayout.addView(down);
        imglayout.addView(line2);
        textLinear.addView(bus_station_name);
        textLinear.addView(bus_id);
        textlayout.addView(textLinear);
        mainlayout.addView(imglayout);
        mainlayout.addView(textlayout);
        busstation_layout.addView(mainlayout);

    }

    public void busroutestation() {
        busStationAv.execute(routeid);
        while (true) {
            try {
                Thread.sleep(100);
                if (busStationAv.flag == true) {
                    busstationarray = busStationAv.busstationarr;
                    busstationNoarray = busStationAv.busstationNoarr;
                    break;
                }
            } catch (Exception e) {

            }
        }

    }

}
