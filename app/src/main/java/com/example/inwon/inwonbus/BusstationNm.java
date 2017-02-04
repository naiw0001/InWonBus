package com.example.inwon.inwonbus;

import android.os.AsyncTask;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by inwon on 2017-02-04.
 */

public class BusstationNm extends AsyncTask<String, String, String> {
    URL url;
    String uri = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByName";//stSrch
    String key = "zadOGy8xBhJVBYYhLSPbfGoQHfJ1mAFAOQADCvNFSWWshGWcmf1St10W17yKjJtVEOR4hpjtrYZdBk32bLFO6w%3D%3D";
    String xmlURL = uri + "?serviceKey=" + key;
    String tagname = "", arsId = "", stNm = "";
    boolean flag = false;

    @Override
    protected String doInBackground(String... params) {
        return null;
    }
}
