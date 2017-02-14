package com.example.inwon.inwonbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.inwon.inwonbus.database.Sqlite_search;

/**
 * Created by inwon on 2017-02-07.
 */

public class SearchActivity extends AppCompatActivity {
    private TabLayout tablayout;
    private ViewPager viewpager;
    private ActionBar actionBar;
    private int paramsheight;
    private Sqlite_search search;
    private TabPagerAdapter pagerAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        viewpager.setAdapter( new TabPagerAdapter(getSupportFragmentManager(), tablayout.getTabCount()));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true); // 커스텀 허용 O
        actionBar.setDisplayShowTitleEnabled(false); // 타이틀 표시 X
        paramsheight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 45, getResources().getDisplayMetrics()); // set dp
        search = new Sqlite_search(getApplicationContext(),"search_list.db",null,1);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        tablayout.addTab(tablayout.newTab().setText("버스"));
        tablayout.addTab(tablayout.newTab().setText("정류장"));
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewpager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tablayout.getTabCount());
        viewpager.setAdapter(pagerAdapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
                int position = tab.getPosition();
                if (position == 0) { // bus tab
                    // CUSTOM ActionBar
                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    View customactionbar = inflater.inflate(R.layout.actionbar_custom_bus_search, null);
                    ActionBar.LayoutParams params = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    actionBar.setCustomView(customactionbar, params);
                    Toolbar parent = (Toolbar) customactionbar.getParent(); // 양쪽 여백 없애기
                    parent.setContentInsetsAbsolute(0, 0);
                    final EditText input_bus = (EditText) customactionbar.findViewById(R.id.edit_bus);
                    Button bus_ok = (Button) customactionbar.findViewById(R.id.busClick);
                    bus_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String bus = input_bus.getText().toString();
                            Intent intent_bus = new Intent(SearchActivity.this, BusList.class);
                            intent_bus.putExtra("bus", bus);
                            startActivity(intent_bus);
                        }
                    });
                } else if (position == 1) { // station tab
                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    View customactionbar = inflater.inflate(R.layout.actionbar_custom_station_search, null);
                    ActionBar.LayoutParams params = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    actionBar.setCustomView(customactionbar, params);
                    Toolbar parent = (Toolbar) customactionbar.getParent();
                    parent.setContentInsetsAbsolute(0,0);
                    final EditText input_station = (EditText) customactionbar.findViewById(R.id.edit_station);
                    Button station_ok = (Button) customactionbar.findViewById(R.id.stationClick);
                    station_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String station = input_station.getText().toString();
                            Intent intent_st = new Intent(SearchActivity.this, BusstationNmList.class);
                            intent_st.putExtra("name", station);
                            startActivity(intent_st);
                        }
                    });
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

public void listupdate(){
    viewpager.setAdapter( new TabPagerAdapter(getSupportFragmentManager(), tablayout.getTabCount()));
}

}
