package com.example.inwon.inwonbus;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inwon.inwonbus.database.Sqlite_search;

import java.util.ArrayList;

/**
 * Created by inwon on 2017-02-04.
 */

public class BussearchAdapter extends BaseAdapter {
    private ArrayList<BusSearchList> listViewItemList = new ArrayList<BusSearchList>();

    public BussearchAdapter() {
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
        final String[] id = search.select_bus_id();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.bussearchlist, parent, false);
        }
        TextView nameText = (TextView) convertView.findViewById(R.id.bussearch);
        ImageView del_img = (ImageView) convertView.findViewById(R.id.del_img_bus);

        final BusSearchList listViewItem = listViewItemList.get(position);

        nameText.setText(listViewItem.getBus_name());
        del_img.setImageDrawable(listViewItem.getDel_img());
        del_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.delete_bus_list(id[pos]);
                ((SearchActivity)context).listupdate();
            }
        });
        return convertView;
    }

    public void additme(String name, Drawable img) {
        BusSearchList item = new BusSearchList();
        item.setBus_name(name);
        item.setDel_img(img);
        listViewItemList.add(item);
    }

}
