package com.example.inwon.inwonbus;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by inwon on 2017-02-04.
 */

public class BusSearchList {

    private String bus_name;
    private Drawable del_img;
    public void setBus_name(String name) {
        bus_name = name;
    }

    public void setDel_img(Drawable img) {del_img = img;}

    public String getBus_name() {
        return this.bus_name;
    }

    public Drawable getDel_img() {
        return this.del_img;
    }

}
