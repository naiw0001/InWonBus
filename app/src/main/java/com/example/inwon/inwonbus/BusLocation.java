package com.example.inwon.inwonbus;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by inwon on 2017-02-02.
 */

public class BusLocation extends AsyncTask<String, String, String> {

    URL url;
    String uri = "http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid";
    String key = "zadOGy8xBhJVBYYhLSPbfGoQHfJ1mAFAOQADCvNFSWWshGWcmf1St10W17yKjJtVEOR4hpjtrYZdBk32bLFO6w%3D%3D";
    String xmlURL = uri + "?serviceKey=" + key;
    boolean flag = false;
    String tagname = "", stopFlag = "", plainNo = "", islastyn = "", nextStTm = "", sectOrd = "", isrunyn = "";
    //정류소 도착여부 | 차번호 | 막차여부 | 다음정류소 도착시간 | 다음 정류장
    ArrayList<String> stopFlagArr = new ArrayList<String>();
    ArrayList<String> plainNoArr = new ArrayList<String>();
    ArrayList<String> islastArr = new ArrayList<String>();
    ArrayList<String> nextStTmArr = new ArrayList<String>();
    ArrayList<String> sectOrdArr = new ArrayList<String>();
    ArrayList<String> isrunynArr = new ArrayList<String>();

    @Override
    protected String doInBackground(String... params) {
        String busrouteid = params[0];
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            url = new URL(xmlURL + "&busRouteId=" + busrouteid);
            InputStream in = url.openStream();
            xpp.setInput(in, "UTF-8");
            boolean isInItemTag = false;
            int event_type = xpp.getEventType();
            while (event_type != XmlPullParser.END_DOCUMENT) {
                if (event_type == XmlPullParser.START_TAG) {
                    tagname = xpp.getName();
                    if (tagname.equals("itemList")) {
                        isInItemTag = true;
                    }
                } else if (event_type == XmlPullParser.TEXT) {
                    if (tagname.equals("islastyn") && isInItemTag) {
                        islastyn += xpp.getText();
                    } else if (tagname.equals("isrunyn") && isInItemTag) {
                        isrunyn += xpp.getText();
                    } else if (tagname.equals("nextStTm") && isInItemTag) {
                        nextStTm += xpp.getText();
                    } else if (tagname.equals("plainNo") && isInItemTag) {
                        plainNo += xpp.getText();
                    } else if (tagname.equals("sectOrd") && isInItemTag) {
                        sectOrd += xpp.getText();
                    } else if (tagname.equals("stopFlag") && isInItemTag) {
                        stopFlag += xpp.getText();
                    }
                } else if (event_type == XmlPullParser.END_TAG) {
                    tagname = xpp.getName();
                    if (tagname.equals("itemList")) {
                        islastArr.add(islastyn);
                        isrunynArr.add(isrunyn);
                        nextStTmArr.add(nextStTm);
                        plainNoArr.add(plainNo);
                        sectOrdArr.add(sectOrd);
                        stopFlagArr.add(stopFlag);

                        islastyn = "";
                        isrunyn = "";
                        nextStTm = "";
                        plainNo = "";
                        sectOrd = "";
                        stopFlag = "";
                        isInItemTag = false;
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
