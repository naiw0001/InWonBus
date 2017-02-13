package com.example.inwon.inwonbus;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inwon.inwonbus.database.Sqlite_search;

import java.util.ArrayList;

/**
 * Created by inwon on 2017-02-04.
 */

public class StationsearchAdapter extends BaseAdapter {
    private ArrayList<StationSearchList> listViewItemList = new ArrayList<StationSearchList>();

    public StationsearchAdapter() {
    }

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
        final Sqlite_search search = new Sqlite_search(context,"search_list.db",null,1);
        final String[] id = search.select_station_id();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.stationsearchlist, parent, false);
        }
        TextView nameText = (TextView) convertView.findViewById(R.id.stationsearch);
        ImageView del_img = (ImageView) convertView.findViewById(R.id.del_img_station);

        final StationSearchList listViewItem = listViewItemList.get(position);

        nameText.setText(listViewItem.getStation_name());
        del_img.setImageDrawable(listViewItem.getDel_img());
        del_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.delete_station_list(id[pos]);
                ((SearchActivity)context).listupdate();
            }
        });
        return convertView;
    }

    public void additme(String name, Drawable img) {
        StationSearchList item = new StationSearchList();
        item.setStation_name(name);
        item.setDel_img(img);
        listViewItemList.add(item);
    }

}
