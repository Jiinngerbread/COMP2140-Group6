package com.softeng_grup6.vainsfitness;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.softeng_grup6.vainsfitness.ui.main.SectionsPagerAdapter;
import com.softeng_grup6.vainsfitness.ui.main.TabAdapter;
import com.softeng_grup6.vainsfitness.ui.main.tab1Fragment;
import com.softeng_grup6.vainsfitness.ui.main.tab2Fragment;

public class MainActivity extends AppCompatActivity {

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new tab1Fragment(), "Tab 1");
        adapter.addFragment(new tab2Fragment(), "Tab 2");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}