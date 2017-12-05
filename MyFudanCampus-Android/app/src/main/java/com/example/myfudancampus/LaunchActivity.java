package com.example.myfudancampus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by alex on 2017/12/6.
 */

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launch);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 1000);
    }

    class splashhandler implements Runnable {

        public void run() {
            startActivity(new Intent(getApplication(), CampusActivity.class));
            LaunchActivity.this.finish();
        }

    }
}