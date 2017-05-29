package com.appswager.rangoliapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FragmentAdapterClass extends FragmentStatePagerAdapter {

    int TabCount;

    public FragmentAdapterClass(FragmentManager fragmentManager, int CountTabs) {

        super(fragmentManager);

        this.TabCount = CountTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FragmentOne tab1 = new FragmentOne();
                return tab1;

            case 1:
                FragmentTwo tab2 = new FragmentTwo();
                return tab2;

            case 2:
                FragmentThree tab3 = new FragmentThree();
                return tab3;

            case 3:
                FragmentFour tab4 = new FragmentFour();
                return tab4;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TabCount;
    }
}