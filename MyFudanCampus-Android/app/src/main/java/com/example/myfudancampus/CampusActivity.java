package com.example.myfudancampus;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;


public class CampusActivity extends AppCompatActivity {

    private TextView txt_main;
    private BottomNavigationBar bottom_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus);
        initView();
        initEvent();
    }

    private void initEvent() {
        bottom_bar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        txt_main.setText(getString(R.string.title_news));
                        break;
                    case 1:
                        txt_main.setText(getString(R.string.title_gpa));
                        break;
                    case 2:
                        txt_main.setText(getString(R.string.title_bbs));
                        break;
                    case 3:
                        txt_main.setText(getString(R.string.title_about));
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

    private void initView() {
        txt_main = (TextView) findViewById(R.id.txt_main);
        bottom_bar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottom_bar
                .setActiveColor(R.color.skyblue)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, "Home"))
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, "Books"))
                .addItem(new BottomNavigationItem(R.drawable.ic_notifications_black_24dp, "Music"))
                .addItem(new BottomNavigationItem(R.drawable.ic_notifications_black_24dp, "Movies & TV"))
                .initialise();
        txt_main.setText(getString(R.string.title_news));

    }

}

