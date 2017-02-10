package com.example.inwon.inwonbus;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.inwon.inwonbus.database.Sqlite_search;

import java.util.ArrayList;
import java.util.Vector;


public class MainActivity extends AppCompatActivity{
    EditText input;
    LinearLayout main;
    Sqlite_search search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(MainActivity.this,BusSplash.class));
        input = (EditText) findViewById(R.id.main_edit);
        main = (LinearLayout)findViewById(R.id.main_search_layout);
    }
    //onClick
    public void mainedit(View v){
        startActivity(new Intent(MainActivity.this,SearchActivity.class));
    }



}