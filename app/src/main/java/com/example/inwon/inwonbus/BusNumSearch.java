package com.example.inwon.inwonbus;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by inwon on 2017-01-25.
 */

public class BusNumSearch extends AsyncTask<String,String,String> {


    URL url;
    ArrayList<String> busNumarr = new ArrayList<String>();
    ArrayList<String> busRouteIdarr = new ArrayList<String>();
    ArrayList<String> startstationarr = new ArrayList<String>();
    ArrayList<String> endstationarr = new ArrayList<String>();
    String uri = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList";
    String key = "zadOGy8xBhJVBYYhLSPbfGoQHfJ1mAFAOQADCvNFSWWshGWcmf1St10W17yKjJtVEOR4hpjtrYZdBk32bLFO6w%3D%3D";
    String xmlURL = uri + "?serviceKey=" + key;
    String tagname="",busNum="",busRouteId="",startStation="",endStation="";
    boolean flag = false;

    @Override
    protected String doInBackground(String... params) {
        String input_busnum = params[0];
        try{ // xmlparsing
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            url = new URL(xmlURL+"&strSrch="+input_busnum);
            InputStream in = url.openStream();
            xpp.setInput(in,"UTF-8");

            boolean isInItemTag = false; // itemtag의 유무 체크
            int event_type = xpp.getEventType();

            while(event_type != XmlPullParser.END_DOCUMENT){
                if(event_type == XmlPullParser.START_TAG){
                    tagname = xpp.getName();
                    if(tagname.equals("itemList")){
                        isInItemTag = true;
                    }
                }else if(event_type==XmlPullParser.TEXT){
                    if(tagname.equals("busRouteNm")&&isInItemTag){
                        busNum += xpp.getText();
                    }else if(tagname.equals("busRouteId")&&isInItemTag){
                        busRouteId += xpp.getText();
                    }else if(tagname.equals("stStationNm")&&isInItemTag){
                        startStation += xpp.getText();
                    }else if (tagname.equals("edStationNm")&&isInItemTag){
                        endStation += xpp.getText();
                    }
                }else if(event_type == XmlPullParser.END_TAG){
                    tagname = xpp.getName();

                    if(tagname.equals("itemList")){
                        busNumarr.add(busNum);
                        busRouteIdarr.add(busRouteId);
                        startstationarr.add(startStation);
                        endstationarr.add(endStation);
                        busNum = "";
                        busRouteId = "";
                        startStation ="";
                        endStation = "";
                        isInItemTag = false;
                    }
                }

                event_type = xpp.next();
            }
                flag = true; // 데이터 있을 시 true
        }catch (Exception e){e.printStackTrace();}

        return null;
    }


}
