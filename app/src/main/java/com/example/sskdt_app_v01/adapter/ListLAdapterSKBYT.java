package com.example.sskdt_app_v01.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sskdt_app_v01.R;
import com.example.sskdt_app_v01.item.ItemLSKBYT;
import com.example.sskdt_app_v01.item.ItemUser;

import java.util.List;

public class ListLAdapterSKBYT extends BaseAdapter {
    private List<ItemLSKBYT> itemLSKBYTs;
    private Context context;

    public ListLAdapterSKBYT(List<ItemLSKBYT> itemLSKBYTs, Context context) {
        this.itemLSKBYTs = itemLSKBYTs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemLSKBYTs.size();
    }

    @Override
    public Object getItem(int i) {
        return itemLSKBYTs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_list_lskbyt, null);


        TextView date = view.findViewById(R.id.item_lskbyt_date);
        TextView month = view.findViewById(R.id.item_lskbyt_month);
        TextView year = view.findViewById(R.id.item_lskbyt_year);
        TextView name = view.findViewById(R.id.item_lskbyt_name);
        TextView hour = view.findViewById(R.id.item_lskbyt_hour);
        TextView minus = view.findViewById(R.id.item_lskbyt_minus);
        TextView second = view.findViewById(R.id.item_lskbyt_second);


        name.setText(itemLSKBYTs.get(i).getName());
        date.setText(formatNumber(itemLSKBYTs.get(i).getDate().getDay()));
        month.setText(formatNumber(itemLSKBYTs.get(i).getDate().getMonth()));
        year.setText(formatYear(itemLSKBYTs.get(i).getDate().getYear()));
        hour.setText(formatNumber(itemLSKBYTs.get(i).getDate().getHours()));
        minus.setText(formatNumber(itemLSKBYTs.get(i).getDate().getMinutes()));
        second.setText(formatNumber(itemLSKBYTs.get(i).getDate().getSeconds()));
        return view;
    }

    private String formatYear(int year) {
        String s = year+"";
        return "20"+ s.substring(1);
    }

    private String formatNumber(int num) {
        if (num < 10)
            return "0"+num;
        return num + "";
    }

}
