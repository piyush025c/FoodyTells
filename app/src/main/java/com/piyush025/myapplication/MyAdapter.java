package com.piyush025.myapplication;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {

    Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                CustomMeals tab1 = new CustomMeals();
                return tab1;
            case 1:
                ComboMeals tab2= new ComboMeals();
                return tab2;
            default:
                return null;
        }
    }


    @Override
    public int getCount()
    {
        return totalTabs;
    }
}
