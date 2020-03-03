package com.kye.viewpager_fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> mData;

    public MyAdapter(FragmentManager fm) {
        super(fm);
        mData = new ArrayList<Fragment>();
        mData.add(new FragmentA());
        mData.add(new FragmentB());
        mData.add(new FragmentC());
    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }
}
