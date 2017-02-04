package com.example.inwon.inwonbus;

import android.os.AsyncTask;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by inwon on 2017-01-27.
 */

public class BusStation extends AsyncTask<String,String,String>{
    URL url;
    String uri = "http://ws.bus.go.kr/api/rest/busRouteInfo/getStaionByRoute";
    String key = "zadOGy8xBhJVBYYhLSPbfGoQHfJ1mAFAOQADCvNFSWWshGWcmf1St10W17yKjJtVEOR4hpjtrYZdBk32bLFO6w%3D%3D";
    String xmlURL = uri + "?serviceKey=" + key;
    boolean flag = false;
    String tagname="",busstation="",busstationNo="",stationNo="";
    ArrayList<String> busstationarr = new ArrayList<String>();
    ArrayList<String> busstationNoarr = new ArrayList<String>();
    @Override
    protected String doInBackground(String... params) {
        String busrouteid = params[0];
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            url = new URL(xmlURL + "&busRouteId="+busrouteid);
            Log.d("xxxxxxxxxxxxx",String.valueOf(url));
            InputStream in = url.openStream();
            xpp.setInput(in,"UTF-8");
            boolean isInItemTag = false;
            int event_type = xpp.getEventType();
            while(event_type != XmlPullParser.END_DOCUMENT){
                if(event_type==XmlPullParser.START_TAG){
                    tagname = xpp.getName();
                    if(tagname.equals("itemList")){
                        isInItemTag=true;
                    }
                }else if(event_type==XmlPullParser.TEXT){
                   if(tagname.equals("stationNm")&&isInItemTag) {
                       busstation += xpp.getText();
                    }else if(tagname.equals("stationNo")&&isInItemTag){
                        busstationNo += xpp.getText();
                    }
                }else if(event_type==XmlPullParser.END_TAG){
                    tagname = xpp.getName();

                    if(tagname.equals("itemList")){
                        busstationarr.add(busstation);
                        busstationNoarr.add(busstationNo);

                        busstation="";
                        busstationNo="";
                        isInItemTag=false;
                    }
                }
                event_type = xpp.next();
            }
            flag = true;
        }catch (Exception e){}


        return null;
    }
}
