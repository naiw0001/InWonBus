package com.example.inwon.inwonbus;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by inwon on 2017-02-04.
 */

public class BusStationArrive extends AsyncTask<String, String, String> {
    URL url;
    String uri = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByUid";
    String key = "zadOGy8xBhJVBYYhLSPbfGoQHfJ1mAFAOQADCvNFSWWshGWcmf1St10W17yKjJtVEOR4hpjtrYZdBk32bLFO6w%3D%3D";
    String xmlURL = uri + "?serviceKey=" + key;
    String tagname = "", adirection = "", arrmsg1 = "", arrmsg2 = "", rtNm = "";
    ArrayList<String> adirectionArr = new ArrayList<>();
    ArrayList<String> arrmsg1Arr = new ArrayList<>();
    ArrayList<String> arrmsg2Arr = new ArrayList<>();
    ArrayList<String> rtNmArr = new ArrayList<>();
    boolean flag = false;

    @Override
    protected String doInBackground(String... params) {
        String stationNo = params[0];
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            url = new URL(xmlURL + "&arsId=" + stationNo);
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
                    if (tagname.equals("adirection") && isinItemTag) {
                        adirection += xpp.getText();
                    } else if (tagname.equals("arrmsg1") && isinItemTag) {
                        arrmsg1 += xpp.getText();
                    } else if (tagname.equals("arrmsg2") && isinItemTag) {
                        arrmsg2 += xpp.getText();
                    } else if (tagname.equals("rtNm") && isinItemTag) {
                        rtNm += xpp.getText();
                    }
                } else if (event_type == XmlPullParser.END_TAG) {
                    tagname = xpp.getName();
                    if (tagname.equals("itemList")) {
                        adirectionArr.add(adirection);
                        arrmsg1Arr.add(arrmsg1);
                        arrmsg2Arr.add(arrmsg2);
                        rtNmArr.add(rtNm);
                        adirection = "";
                        arrmsg1 = "";
                        arrmsg2 = "";
                        rtNm = "";
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
