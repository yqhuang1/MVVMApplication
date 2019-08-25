package com.example.mvvmapplication.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Coolger on 2017/4/14.
 * 注释：SuspensionBar-master
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {

        return mFragments.size();
    }

}
