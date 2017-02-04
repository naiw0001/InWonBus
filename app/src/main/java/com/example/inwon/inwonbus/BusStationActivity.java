package com.example.inwon.inwonbus;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
    String station,station_num;
    BusStationArrive busStationArrive;
    LinearLayout stationLayout;
    TextView stationNm,stationId;
    ArrayList<String> adirectionArr,arrmsg1Arr,arrmsg2Arr,rtNmArr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busstation);
        stationLayout = (LinearLayout)findViewById(R.id.busstationarrive);
        adirectionArr = new ArrayList<>();
        arrmsg1Arr = new ArrayList<>();
        arrmsg2Arr = new ArrayList<>();
        rtNmArr = new ArrayList<>();
        busStationArrive = new BusStationArrive();
        Intent intent = getIntent();
        station = intent.getStringExtra("station");
        station_num = intent.getStringExtra("station_num");
        stationNm = (TextView)findViewById(R.id.stationNm);
        stationId = (TextView)findViewById(R.id.stationId);
        stationNm.setText(station);
        stationId.setText(station_num);

        staionarrive();
        for (int i = 0 ; i < rtNmArr.size();i++){
            makelayout(i);
        }
    }

    public void makelayout(int index){
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70, getResources().getDisplayMetrics()); // set dp
        int vheight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()); // set dp

        LinearLayout mainLayout = new LinearLayout(this);
        RelativeLayout busnumLayout = new RelativeLayout(this);
        LinearLayout busnumSubLayout = new LinearLayout(this);
        RelativeLayout timeLayout = new RelativeLayout(this);
        LinearLayout timeSubLayout = new LinearLayout(this);
        TextView busnumText = new TextView(this);
        TextView adirectionText = new TextView(this);
        TextView arrive1Text = new TextView(this);
        TextView arrive2Text = new TextView(this);

        View view = new View(this);
        LinearLayout.LayoutParams viewP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,vheight);
        view.setBackgroundColor(Color.parseColor("#666666"));
        view.setLayoutParams(viewP);

        LinearLayout.LayoutParams mainP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        LinearLayout.LayoutParams busnumP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height,1);
        RelativeLayout.LayoutParams busnumSubp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams timeP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height,1);
        RelativeLayout.LayoutParams timeSubP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams textP = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mainLayout.setOrientation(LinearLayout.HORIZONTAL);
        mainLayout.setLayoutParams(mainP);

        busnumLayout.setLayoutParams(busnumP);

        busnumSubLayout.setOrientation(LinearLayout.VERTICAL);
        busnumSubp.addRule(RelativeLayout.CENTER_VERTICAL);
        busnumSubp.leftMargin = 150;
        busnumSubLayout.setLayoutParams(busnumSubp);

        timeLayout.setLayoutParams(timeP);
        timeSubLayout.setOrientation(LinearLayout.VERTICAL);
        timeSubP.addRule(RelativeLayout.CENTER_VERTICAL);
        timeSubLayout.setLayoutParams(timeSubP);

        busnumText.setLayoutParams(textP);
        adirectionText.setLayoutParams(textP);
        arrive1Text.setLayoutParams(textP);
        arrive2Text.setLayoutParams(textP);

        busnumText.setText(rtNmArr.get(index).toString());
        adirectionText.setText(adirectionArr.get(index).toString()+" 방향");
        arrive1Text.setText(arrmsg1Arr.get(index).toString());
        arrive2Text.setText(arrmsg2Arr.get(index).toString());

        busnumText.setTextSize(20f);
        adirectionText.setTextSize(10f);

        busnumSubLayout.addView(busnumText);
        busnumSubLayout.addView(adirectionText);
        busnumLayout.addView(busnumSubLayout);

        timeSubLayout.addView(arrive1Text);
        timeSubLayout.addView(arrive2Text);
        timeLayout.addView(timeSubLayout);

        mainLayout.addView(busnumLayout);
        mainLayout.addView(timeLayout);

        stationLayout.addView(mainLayout);
        stationLayout.addView(view);
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

}
