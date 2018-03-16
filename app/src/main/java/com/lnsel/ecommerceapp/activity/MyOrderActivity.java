package com.lnsel.ecommerceapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.adapter.MyOrderAdapter;
import com.lnsel.ecommerceapp.adapter.WishlistAdapter;
import com.lnsel.ecommerceapp.other.Launcher;

import java.util.ArrayList;
import java.util.HashMap;

public class MyOrderActivity extends AppCompatActivity {

    ListView wish_list_view;
    ArrayList<HashMap<String,String>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        TextView header_title=(TextView)findViewById(R.id.header_title);
        header_title.setText("My Order");
        ImageButton header_back=(ImageButton)findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.HomeActivity(MyOrderActivity.this);
            }
        });

        wish_list_view=(ListView)findViewById(R.id.wish_list_view);
        list=new ArrayList<>();
        wish_list_view.setAdapter(new MyOrderAdapter(MyOrderActivity.this,list));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Launcher.HomeActivity(MyOrderActivity.this);
        }
        return true;
    }
}
