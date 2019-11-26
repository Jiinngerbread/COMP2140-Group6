package com.softeng_grup6.vainsfitness;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import com.softeng_grup6.vainsfitness.ui.main.ConsumptionList;
import com.softeng_grup6.vainsfitness.ui.main.Home_Profile_Fragment;
import com.softeng_grup6.vainsfitness.ui.main.ProgressReport;
import com.softeng_grup6.vainsfitness.ui.main.TabAdapter;

public class MainActivity extends AppCompatActivity {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new Home_Profile_Fragment(), "Home");
        adapter.addFragment(new ConsumptionList(), "Meals");
        adapter.addFragment(new ProgressReport(), "Report");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}