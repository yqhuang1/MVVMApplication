package com.example.mvvmapplication.mylist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mvvmapplication.BR;
import com.example.mvvmapplication.R;
import com.example.mvvmapplication.adapter.CommonAdapter;
import com.example.mvvmapplication.base.BaseActivity;
import com.example.mvvmapplication.databinding.ActivityMyListBinding;
import com.example.mvvmapplication.utils.VerticalDecoration;

public class MyListActivity extends BaseActivity {
    private MyListViewModel myListViewModel;
    private ActivityMyListBinding myListBinding;
    private CommonAdapter<MeViewModel> meCommonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myListBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_list);
        myListViewModel = new MyListViewModel(this);
        myListBinding.setMyListViewModel(myListViewModel);

//        根据layout中的组件ID取组件
        myListBinding.myListRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        myListBinding.myListRecyclerView.addItemDecoration(new VerticalDecoration(this));


        meCommonAdapter = new CommonAdapter<>(R.layout.list_item_me, BR.meViewModel);
        myListBinding.myListRecyclerView.setAdapter(meCommonAdapter);

        myListViewModel.loadMeList();

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
