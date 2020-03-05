package com.kye.chap12;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kye.chap12.FragmentA;
import com.kye.chap12.FragmentB;
import com.kye.chap12.FragmentC;

import java.util.ArrayList;

public class MyAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<>();
    String[] titles = {"tab1","tab2","tab3","tab4"};

    public MyAdapter(FragmentManager fm) {
        super(fm);
        fragments.add(new FragmentA());
        fragments.add(new FragmentB());
        fragments.add(new FragmentC());
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
