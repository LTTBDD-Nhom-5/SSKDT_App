package com.example.sskdt_app_v01.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.sskdt_app_v01.R;
import com.example.sskdt_app_v01.item.ItemUser;

import java.util.List;

public class ListAdapterUser extends BaseAdapter {
    private List<ItemUser> itemUsers;
    private Context context;

    public ListAdapterUser(List<ItemUser> itemUsers, Context context) {
        this.itemUsers = itemUsers;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return itemUsers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_listview_user, null);

        ImageView icon = (ImageView) view.findViewById(R.id.item_user_image);
        TextView title = view.findViewById(R.id.item_user_name);

        icon.setImageResource(itemUsers.get(i).getImg());
        title.setText(itemUsers.get(i).getName());

        return view;
    }
}
