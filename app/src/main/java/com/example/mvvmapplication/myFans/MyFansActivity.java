package com.example.mvvmapplication.myFans;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvmapplication.BR;
import com.example.mvvmapplication.R;
import com.example.mvvmapplication.adapter.CommonAdapter;
import com.example.mvvmapplication.base.BaseActivity;
import com.example.mvvmapplication.databinding.ActivityMyFansBinding;
import com.example.mvvmapplication.utils.VerticalDecoration;

public class MyFansActivity extends BaseActivity {

    private MyFansViewModel myFansViewModel;
    private ActivityMyFansBinding myFansBinding;
    private CommonAdapter<OneViewModel> oneCommonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myFansBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_fans);
        myFansViewModel = new MyFansViewModel(this);
        myFansBinding.setMyFansViewModel(myFansViewModel);

        myFansBinding.myFansRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        myFansBinding.myFansRecyclerView.addItemDecoration(new VerticalDecoration(this));

        oneCommonAdapter = new CommonAdapter<>(R.layout.list_item_one, BR.oneViewModel);
        myFansBinding.myFansRecyclerView.setAdapter(oneCommonAdapter);

        myFansViewModel.loadFans();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myFansViewModel.clear();
    }
}
