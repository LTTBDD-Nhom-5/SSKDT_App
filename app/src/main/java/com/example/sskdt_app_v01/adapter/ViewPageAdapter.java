package com.example.sskdt_app_v01.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPageAdapter extends FragmentStateAdapter {
    private ArrayList<Fragment> arrayList = new ArrayList<>();

    public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    public void addFragment(Fragment fragment) {
        arrayList.add(fragment);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // return your fragment that corresponds to this 'position'
        return arrayList.get(position);
    }
}
