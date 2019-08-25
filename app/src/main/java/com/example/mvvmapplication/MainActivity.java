package com.example.mvvmapplication;

import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.blog.www.guideview.Component;
import com.blog.www.guideview.Guide;
import com.blog.www.guideview.GuideBuilder;
import com.example.mvvmapplication.base.BaseActivity;
import com.example.mvvmapplication.component.LottieComponent;
import com.example.mvvmapplication.component.MutiComponent;
import com.example.mvvmapplication.component.SimpleComponent;
import com.example.mvvmapplication.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding mainBinding;
    private MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        new VideoUtils().RXjavaSHow();
//        new HttpUtils().getRetrofit(this);
//        new HttpUtils().requestList(this);
//        new HttpUtils().getVideoListCall(this, "3");
//        new HttpUtils().getVideoListObservable1(this, "3");
//        new HttpUtils().getVideoListObservable2(this, "3");

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        mainBinding.setMainViewModel(mainViewModel);

        mainBinding.myInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "show", Toast.LENGTH_SHORT).show();
            }
        });
        mainBinding.myInfoBtn.post(new Runnable() {
            @Override
            public void run() {
                showGuideView();
            }
        });
    }

    @SuppressLint("ResourceType")
    public void showGuideView() {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(mainBinding.myInfoBtn)
                .setAlpha(150)
                .setFullingColorId(R.color.color_yellow)
                .setHighTargetCorner(20)
                .setHighTargetPadding(10);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                showGuideView2();
            }
        });

        //添加引导层的样式
        builder.addComponent(new SimpleComponent());
        Guide guide = builder.createGuide();
        guide.show(this);
    }

    public void showGuideView2() {
        final GuideBuilder builder1 = new GuideBuilder();
        builder1.setTargetView(mainBinding.myShowBtn)
                .setAlpha(150)
                .setHighTargetGraphStyle(Component.CIRCLE);//设置为圆形区域
        builder1.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                showGuideView3();
            }
        });

        builder1.addComponent(new MutiComponent());
        Guide guide = builder1.createGuide();
        guide.show(this);
    }

    @SuppressLint("ResourceType")
    public void showGuideView3() {
        final GuideBuilder builder1 = new GuideBuilder();
        builder1.setTargetView(mainBinding.myListBtn)
                .setAlpha(150)
                .setHighTargetCorner(20)
                .setHighTargetPadding(10)
                .setExitAnimationId(android.R.anim.fade_out);
        builder1.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
            }
        });

        builder1.addComponent(new LottieComponent());
        Guide guide = builder1.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(this);
    }

}
