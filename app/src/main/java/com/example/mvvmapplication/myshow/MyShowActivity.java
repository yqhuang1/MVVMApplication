package com.example.mvvmapplication.myshow;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.mvvmapplication.R;
import com.example.mvvmapplication.base.BaseActivity;
import com.example.mvvmapplication.databinding.ActivityMyShowBinding;
import com.example.mvvmapplication.base.ListFragment;

import java.util.ArrayList;
import java.util.List;

import eu.long1.spacetablayout.SpaceTabLayout;

public class MyShowActivity extends BaseActivity {

    private MyShowViewModel myShowViewModel;
    private ActivityMyShowBinding myShowBinding;

    public SpaceTabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myShowBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_show);
        myShowViewModel = new MyShowViewModel(this);
        myShowBinding.setMyShowViewModel(myShowViewModel);

        InitView(savedInstanceState);

    }

    private void InitView(Bundle savedInstanceState) {
        //add the fragments you want to display in a List
        //fragment个数 必须与 tab个数 一致
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ListFragment("首页"));
        fragmentList.add(new ListFragment("比赛"));
        fragmentList.add(new ListFragment("社区"));
        fragmentList.add(new ListFragment("识货"));
        fragmentList.add(new ListFragment("更多"));

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (SpaceTabLayout) findViewById(R.id.spaceTabLayout);

        //we need the savedInstanceState to get the position
        tabLayout.initialize(viewPager, getSupportFragmentManager(), fragmentList, savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        tabLayout.saveState(outState);
        super.onSaveInstanceState(outState);
    }
}
