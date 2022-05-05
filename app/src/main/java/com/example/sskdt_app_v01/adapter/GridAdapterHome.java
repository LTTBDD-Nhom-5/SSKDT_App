package com.example.sskdt_app_v01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.sskdt_app_v01.R;
import com.example.sskdt_app_v01.item.ItemHome;

import java.util.List;
import java.util.zip.Inflater;

public class GridAdapterHome extends BaseAdapter {
    private List<ItemHome> itemHomes;
    private Context context;

    public GridAdapterHome(List<ItemHome> itemHomes, Context context) {
        this.itemHomes = itemHomes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemHomes.size();
    }

    @Override
    public Object getItem(int i) {
        return itemHomes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_gridview_home, null);

        ImageView icon = (ImageView) view.findViewById(R.id.item_home_image);
        TextView title = view.findViewById(R.id.item_home_tilte);

        icon.setImageResource(itemHomes.get(i).getImg());
        title.setText(itemHomes.get(i).getTitle());

        return view;
    }
}
