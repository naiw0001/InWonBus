package com.example.inwon.inwonbus;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
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
    ArrayList<String> arsIdArr = new ArrayList<>();
    ArrayList<String> stNmArr = new ArrayList<>();
    boolean flag = false;

    @Override
    protected String doInBackground(String... params) {
        String name = params[0];
        String station_name;
        try {
            station_name = URLEncoder.encode(name, "UTF-8");
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            url = new URL(xmlURL + "&stSrch=" + station_name);
            InputStream in = url.openStream();
            xpp.setInput(in, "UTF-8");

            boolean isinItemTag = false;
            int event_type = xpp.getEventType();

            while (event_type != XmlPullParser.END_DOCUMENT) {
                if (event_type == XmlPullParser.START_TAG) {
                    tagname = xpp.getName();
                    if (tagname.equals("itemList")) {
                        isinItemTag = true;
                    }
                } else if (event_type == XmlPullParser.TEXT) {
                    if (tagname.equals("arsId") && isinItemTag) {
                        arsId += xpp.getText();
                    } else if (tagname.equals("stNm") && isinItemTag) {
                        stNm += xpp.getText();
                    }
                } else if (event_type == XmlPullParser.END_TAG) {
                    tagname = xpp.getName();
                    if (tagname.equals("itemList")) {
                        arsIdArr.add(arsId);
                        stNmArr.add(stNm);
                        arsId = "";
                        stNm = "";
                        isinItemTag = false;
                    }
                }
                event_type = xpp.next();
            }
            flag = true;
        } catch (Exception e) {
        }
        return null;
    }
}
