package com.example.mvvmapplication.mychat;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.mvvmapplication.R;
import com.example.mvvmapplication.adapter.FragmentAdapter;
import com.example.mvvmapplication.base.BaseActivity;
import com.lzy.widget.AlphaIndicator;

import java.util.ArrayList;
import java.util.List;

public class MyChatActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chat);

        InitView();
    }

    private void InitView() {

        //add the fragments you want to display in a List
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(MyWordFragment.newInstance("微信","1"));
        fragmentList.add(MyWordFragment.newInstance("通讯录","2"));
        fragmentList.add(MyWordFragment.newInstance("发现","3"));
        fragmentList.add(MyWordFragment.newInstance("我","4"));

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        FragmentAdapter mFragmentadapter =
                new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        //给ViewPager设置适配器
        viewPager.setAdapter(mFragmentadapter);

        AlphaIndicator alphaIndicator = (AlphaIndicator) findViewById(R.id.alphaIndicator);
        alphaIndicator.setViewPager(viewPager);
    }
}
