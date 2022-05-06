package com.example.sskdt_app_v01;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.sskdt_app_v01.adapter.ViewPageAdapter;
import com.example.sskdt_app_v01.fragment.CaNhanFragment;
import com.example.sskdt_app_v01.fragment.DatLichFragment;
import com.example.sskdt_app_v01.fragment.ThongBaoFragment;
import com.example.sskdt_app_v01.fragment.TrangChuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mapping();
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this);
        viewPageAdapter.addFragment(new TrangChuFragment());
        viewPageAdapter.addFragment(new DatLichFragment());
        viewPageAdapter.addFragment(new ThongBaoFragment());
        viewPageAdapter.addFragment(new CaNhanFragment());
        viewPager.setAdapter(viewPageAdapter);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.TrangChu).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.DatLich).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.ThongBao).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.CaNhan).setChecked(true);
                        break;
                }
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id  = menuItem.getItemId();
                switch (id) {
                    case R.id.TrangChu:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.DatLich:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.ThongBao:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.CaNhan:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });


    }

    private void mapping() {
        viewPager = findViewById(R.id.viewPagerHomeScreen);
        bottomNavigationView = findViewById(R.id.bottom_nagivation);
        bottomNavigationView.setBackground(null);
    }
}