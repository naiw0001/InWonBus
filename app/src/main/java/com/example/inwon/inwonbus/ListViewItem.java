package com.example.inwon.inwonbus;

/**
 * Created by inwon on 2017-02-04.
 */

public class ListViewItem {

    private String station_name;
    private String station_id;


    public void setStation_name(String name){
        station_name = name;
    }
    public void setStation_id(String id){
        station_id = id;
    }
    public String getStation_name(){
        return this.station_name;
    }
    public String getStation_id(){
        return this.station_id;
    }

}
