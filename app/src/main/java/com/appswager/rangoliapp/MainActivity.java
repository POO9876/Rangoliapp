package com.appswager.rangoliapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar ;
    TabLayout tabLayout ;
    ViewPager viewPager ;
    FragmentAdapterClass fragmentAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StartAppSDK.init(this, "209839874", false);
        StartAppAd.disableSplash();
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout1);
        viewPager = (ViewPager) findViewById(R.id.pager1);

        setSupportActionBar(toolbar);

        tabLayout.addTab(tabLayout.newTab().setText("Contest"));
        tabLayout.addTab(tabLayout.newTab().setText("Sample"));
        tabLayout.addTab(tabLayout.newTab().setText("Instruction"));
        tabLayout.addTab(tabLayout.newTab().setText("Winner "));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        fragmentAdapter = new FragmentAdapterClass(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(fragmentAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab LayoutTab) {

                viewPager.setCurrentItem(LayoutTab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab LayoutTab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab LayoutTab) {

            }
        });


    }
}
