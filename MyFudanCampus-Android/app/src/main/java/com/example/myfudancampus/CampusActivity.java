package com.example.myfudancampus;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.webkit.WebView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

public class CampusActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private BottomNavigationBar bottomNavigationBar;
    private List<Fragment> fragments;

    //app启动所进行的动作
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus);
        initView();

        //自定义标题栏
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);
    }

    //view视图初始化
    private void initView() {
        initBottomNavigationBar();
        initViewPager();

    }

    //底部导航栏
    private void initBottomNavigationBar() {
        //设置底部tab按钮格式
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .setActiveColor(R.color.dodgerblue)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .addItem(new BottomNavigationItem(R.drawable.ic_1, (getString(R.string.title_news))))
                .addItem(new BottomNavigationItem(R.drawable.ic_2, (getString(R.string.title_gpa))))
                .addItem(new BottomNavigationItem(R.drawable.ic_3, (getString(R.string.title_bbs))))
                .addItem(new BottomNavigationItem(R.drawable.ic_4, (getString(R.string.title_about))))
                .initialise();

        //设置tab按钮点击事件
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {

                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }

    //导航栏的页面切换
    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        //分别添加4个fragment
        fragments = new ArrayList<Fragment>();
        fragments.add(new NewsFragment());
        fragments.add(new GPAFragment());
        fragments.add(new BBSFragment());
        fragments.add(new AboutFragment());
        //设置viewPager跳转适配器
        viewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(this);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

        bottomNavigationBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }


    //设置webview返回
    @Override
    public void onBackPressed() {
        if (BBSFragment.webView != null) {
            if (BBSFragment.webView.canGoBack()) {
                BBSFragment.webView.goBack();
            } else {
                // do when mWebView cant go back anymore
            }
        }
    }
}


