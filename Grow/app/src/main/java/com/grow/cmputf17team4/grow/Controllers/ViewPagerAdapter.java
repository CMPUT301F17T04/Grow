package com.grow.cmputf17team4.grow.Controllers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for the fragemnts in the MainActivity
 * @author Yizhou Zhao
 * @since 1.0
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    /**
     * Constructor of the ViewPagerAdapter
     * @param manager the argument of constructor of the FragmentPagerAdapter
     * @see FragmentPagerAdapter
     */
    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }
    /**
     * Get the Fragment object in mFragmentList given position
     * @param position the index of the Fragment object in mFragmentList
     * @return the Fragment object
     */
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    /**
     * Get the size of mFragmentList
     * @return the size of mFragmentList
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    /**
     * Add a Fragment to mFragmentList
     * @param fragment the Fragment object that will be added
     */
    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }


}