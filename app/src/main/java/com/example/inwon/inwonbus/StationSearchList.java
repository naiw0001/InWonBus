package com.example.inwon.inwonbus;

import android.graphics.drawable.Drawable;

/**
 * Created by inwon on 2017-02-04.
 */

public class StationSearchList {

    private String station_name;
    private Drawable del_img;
    public void setStation_name(String name) {
        station_name = name;
    }

    public void setDel_img(Drawable img) {del_img = img;}

    public String getStation_name() {
        return this.station_name;
    }

    public Drawable getDel_img() {
        return this.del_img;
    }

}
