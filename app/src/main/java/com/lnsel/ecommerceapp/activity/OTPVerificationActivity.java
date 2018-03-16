package com.lnsel.ecommerceapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.other.Launcher;

public class OTPVerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);

        TextView header_title=(TextView)findViewById(R.id.header_title);
        header_title.setText("OTP Verification");
        ImageButton header_back=(ImageButton)findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.HomeActivity(OTPVerificationActivity.this);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Launcher.HomeActivity(OTPVerificationActivity.this);
        }
        return true;
    }
}
