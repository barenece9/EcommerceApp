package com.lnsel.ecommerceapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lnsel.ecommerceapp.MainActivity;
import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.other.Launcher;

public class SplashActivity extends Activity {

    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Launcher.HomeActivity(SplashActivity.this);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
