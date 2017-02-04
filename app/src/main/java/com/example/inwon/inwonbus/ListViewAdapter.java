package com.example.inwon.inwonbus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by inwon on 2017-02-04.
 */

public class ListViewAdapter extends BaseAdapter{
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    public ListViewAdapter(){}
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listitme,parent,false);
        }
        TextView nameText = (TextView)convertView.findViewById(R.id.station_name);
        TextView idText = (TextView)convertView.findViewById(R.id.station_id);

        ListViewItem listViewItem = listViewItemList.get(position);

        nameText.setText(listViewItem.getStation_name());
        idText.setText(listViewItem.getStation_id());

        return convertView;
    }
    public void additme(String name,String id){
        ListViewItem item = new ListViewItem();
        item.setStation_name(name);
        item.setStation_id(id);
        listViewItemList.add(item);
    }

}
