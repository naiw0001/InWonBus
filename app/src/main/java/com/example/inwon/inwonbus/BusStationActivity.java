package com.example.inwon.inwonbus;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by inwon on 2017-02-04.
 */

public class BusStationActivity extends AppCompatActivity {
    private String station, station_num;
    private BusStationArrive busStationArrive;
    private LinearLayout stationLayout;
    private TextView stationNm, stationId;
    private ArrayList<String> adirectionArr, arrmsg1Arr, arrmsg2Arr, rtNmArr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busstation);
        stationLayout = (LinearLayout) findViewById(R.id.busstationarrive);
        adirectionArr = new ArrayList<>();
        arrmsg1Arr = new ArrayList<>();
        arrmsg2Arr = new ArrayList<>();
        rtNmArr = new ArrayList<>();
        busStationArrive = new BusStationArrive();
        Intent intent = getIntent();
        station = intent.getStringExtra("station");
        station_num = intent.getStringExtra("station_num");
        stationNm = (TextView) findViewById(R.id.stationNm);
        stationId = (TextView) findViewById(R.id.stationId);
        stationNm.setText(station);
        stationId.setText(station_num);
        staionarrive();
        makelayout(rtNmArr.size());
        test();
    }
            //set layout
    public void makelayout(int size) {

        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics()); // set dp
        int vheight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()); // set dp

        LinearLayout[] mainLayout = new LinearLayout[size];
        RelativeLayout[] busnumLayout = new RelativeLayout[size];
        LinearLayout[] busnumSubLayout = new LinearLayout[size];
        RelativeLayout[] timeLayout = new RelativeLayout[size];
        LinearLayout[] timeSubLayout = new LinearLayout[size];
        TextView[] busnumText = new TextView[size];
        TextView[] adirectionText = new TextView[size];
        TextView[] arrive1Text = new TextView[size];
        TextView[] arrive2Text = new TextView[size];
        View[] view = new View[size];

        LinearLayout.LayoutParams viewP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, vheight);
        LinearLayout.LayoutParams mainP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        LinearLayout.LayoutParams busnumP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height, 1);
        RelativeLayout.LayoutParams busnumSubp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams timeP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height, 1);
        RelativeLayout.LayoutParams timeSubP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams textP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        busnumSubp.addRule(RelativeLayout.CENTER_VERTICAL);
        busnumSubp.leftMargin = 150;
        timeSubP.addRule(RelativeLayout.CENTER_VERTICAL);

        for (int i = 0; i < size; i++) {
            busnumLayout[i] = new RelativeLayout(this);
            busnumSubLayout[i] = new LinearLayout(this);
            timeLayout[i] = new RelativeLayout(this);
            timeSubLayout[i] = new LinearLayout(this);
            busnumText[i] = new TextView(this);
            adirectionText[i] = new TextView(this);
            arrive1Text[i] = new TextView(this);
            arrive2Text[i] = new TextView(this);
            mainLayout[i] = new LinearLayout(this);
            view[i] = new View(this);
            view[i].setBackgroundColor(Color.parseColor("#666666"));
            view[i].setLayoutParams(viewP);
            mainLayout[i].setOrientation(LinearLayout.HORIZONTAL);
            mainLayout[i].setLayoutParams(mainP);

            busnumLayout[i].setLayoutParams(busnumP);
            busnumSubLayout[i].setOrientation(LinearLayout.VERTICAL);
            busnumSubLayout[i].setLayoutParams(busnumSubp);

            timeLayout[i].setLayoutParams(timeP);
            timeSubLayout[i].setOrientation(LinearLayout.VERTICAL);
            timeSubLayout[i].setLayoutParams(timeSubP);

            busnumText[i].setLayoutParams(textP);
            adirectionText[i].setLayoutParams(textP);
            arrive1Text[i].setLayoutParams(textP);
            arrive2Text[i].setLayoutParams(textP);

            busnumText[i].setText(rtNmArr.get(i).toString());
            adirectionText[i].setText(adirectionArr.get(i).toString() + " 방향");
            arrive1Text[i].setText(arrmsg1Arr.get(i).toString());
            arrive2Text[i].setText(arrmsg2Arr.get(i).toString());

            busnumText[i].setTextSize(20f);
            adirectionText[i].setTextSize(10f);

            busnumSubLayout[i].addView(busnumText[i]);
            busnumSubLayout[i].addView(adirectionText[i]);
            busnumLayout[i].addView(busnumSubLayout[i]);

            timeSubLayout[i].addView(arrive1Text[i]);
            timeSubLayout[i].addView(arrive2Text[i]);
            timeLayout[i].addView(timeSubLayout[i]);

            mainLayout[i].addView(busnumLayout[i]);
            mainLayout[i].addView(timeLayout[i]);

            stationLayout.addView(mainLayout[i]);
            stationLayout.addView(view[i]);
        }
    }


    public void staionarrive() {
        busStationArrive.execute(station_num);
        while (true) {
            try {
                Thread.sleep(100);
                if (busStationArrive.flag == true) {
                    adirectionArr = busStationArrive.adirectionArr;
                    arrmsg1Arr = busStationArrive.arrmsg1Arr;
                    arrmsg2Arr = busStationArrive.arrmsg2Arr;
                    rtNmArr = busStationArrive.rtNmArr;
                    break;
                }
            } catch (Exception e) {

            }
        }
    }

    // test
    public void test() {
        int[] temp = new int[arrmsg1Arr.size()];
        int[] temp2 = new int[arrmsg1Arr.size()];
        int[] min = new int[arrmsg1Arr.size()];
        int[] sec = new int[arrmsg1Arr.size()];
        for (int i = 0; i < arrmsg1Arr.size(); i++) {
            if (arrmsg1Arr.get(i).contains("분")) {
                temp[i] = arrmsg1Arr.get(i).indexOf("분");
                temp2[i] = arrmsg1Arr.get(i).indexOf("초");
                min[i] = Integer.parseInt(arrmsg1Arr.get(i).substring(0, temp[i]));
                sec[i] = Integer.parseInt(arrmsg1Arr.get(i).substring(temp[i] + 1, temp2[i]));
            }
        }
    }

}