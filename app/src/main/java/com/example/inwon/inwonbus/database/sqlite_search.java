package com.example.inwon.inwonbus.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by inwon on 2017-02-10.
 */

public class Sqlite_search extends SQLiteOpenHelper {

    public Sqlite_search(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE search_bus_list (busnum CHAR(10), busrouteid CHAR(10))");
        db.execSQL("CREATE TABLE search_station_list (station_name CHAR(30), station_id CHAR(6))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE search_bus_list");
        db.execSQL("DROP TABLE search_station_list");
    }

    public void insert_bus_list(String busnum, String busrouteid) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into search_bus_list values ('" + busnum + "' , '" + busrouteid + "');");
        db.close();
    }

    public void insert_station_list(String station_name, String station_id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into search_station_list values ('" + station_name + "' , '" + station_id + "');");
        db.close();
    }

    public String[] select_bus_list() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM search_bus_list", null);
        String[] list = new String[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()) {
            list[i] = cursor.getString(0) + "," + cursor.getString(1);
            i++;
        }
        db.close();
        return list;
    }

    public String[] select_station_list() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM search_station_list", null);
        String[] list = new String[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()) {
            list[i] = cursor.getString(0) + "," + cursor.getString(1);
            i++;
        }
        db.close();
        return list;
    }


}
