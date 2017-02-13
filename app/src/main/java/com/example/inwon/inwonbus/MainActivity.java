package com.example.inwon.inwonbus;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.inwon.inwonbus.database.Sqlite_search;


public class MainActivity extends AppCompatActivity{
    EditText input;
    LinearLayout main;

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