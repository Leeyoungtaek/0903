package com.naxesa.a0903.MainFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

/**
 * Created by Lee young teak on 2016-10-02.
 */

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private ArrayList<String> tabTitles;
    private ArrayList<Fragment> fragments;
    private Context context;

    public  FragmentPagerAdapter(FragmentManager fm, Context context, ArrayList<String> tabTitles, ArrayList<Fragment> fragments) {
        super(fm);
        this.context = context;
        this.tabTitles = tabTitles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return tabTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
