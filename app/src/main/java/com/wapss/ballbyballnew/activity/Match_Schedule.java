package com.wapss.ballbyballnew.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.wapss.ballbyballnew.R;
import com.wapss.ballbyballnew.adapter.MyPagerAdapter;
import com.wapss.ballbyballnew.fragment.TodayFragment;
import com.wapss.ballbyballnew.fragment.TomorrowFragment;
import com.wapss.ballbyballnew.fragment.UpcomingFragment;

public class Match_Schedule extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_schedule);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager1);

        // Set up the ViewPager with the sections adapter
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());

        // Add Fragments and Titles
        adapter.addFragment(new TodayFragment(), "Today");
        adapter.addFragment(new TomorrowFragment(), "Tomorrow");
        adapter.addFragment(new UpcomingFragment(), "Upcoming");

        // Set adapter to ViewPager
        viewPager.setAdapter(adapter);

        // Link ViewPager and TabLayout
        tabLayout.setupWithViewPager(viewPager);
    }
}