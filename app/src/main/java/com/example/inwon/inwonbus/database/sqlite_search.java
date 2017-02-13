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
        db.execSQL("CREATE TABLE search_bus_list (busnum CHAR(10), busrouteid CHAR(10), busstart TEXT, busend TEXT)");
        db.execSQL("CREATE TABLE search_station_list (station_name CHAR(30), station_id CHAR(6))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE search_bus_list");
        db.execSQL("DROP TABLE search_station_list");
    }

    public void insert_bus_list(String busnum, String busrouteid,String busstart, String busend) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("insert into search_bus_list values ('" + busnum + "' , '" + busrouteid + "', '" + busstart + "', '" + busend + "');");
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
            list[i] = cursor.getString(0) + "," + cursor.getString(1) +"|"+cursor.getString(2)+"/"+cursor.getString(3);
            i++;
        }
        db.close();
        return list;
    }

    public String[] select_bus_id() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT busrouteid FROM search_bus_list", null);
        String[] list = new String[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()) {
            list[i] = cursor.getString(0);
            i++;
        }
        db.close();
        return list;
    }

    public String[] select_station_id(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT station_id FROM search_station_list",null);
        String[] list = new String[cursor.getCount()];
        int i = 0;
        while(cursor.moveToNext()){
            list[i] = cursor.getString(0);
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
    public void delete_bus_list(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM search_bus_list where busrouteid='"+id+"'");
        db.close();
    }

    public void delete_station_list(String id){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM search_station_list where station_id='"+id+"'");
        db.close();
    }


}
