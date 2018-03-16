package com.lnsel.ecommerceapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.adapter.CartAdapter;
import com.lnsel.ecommerceapp.adapter.WishlistAdapter;
import com.lnsel.ecommerceapp.other.ClickEffect;
import com.lnsel.ecommerceapp.other.Launcher;
import com.lnsel.ecommerceapp.other.Method;

import java.util.ArrayList;
import java.util.HashMap;

public class WishlistActivity extends AppCompatActivity {

    ListView wish_list_view;
    ArrayList<HashMap<String,String>> list;
    ImageButton header_cart,header_wishlist;
    TextView txt_cart_count,txt_wish_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        TextView header_title=(TextView)findViewById(R.id.header_title);
        header_title.setText("My Wishlist");

        txt_cart_count=(TextView)findViewById(R.id.txt_cart_count);
        Method.SetCartCount(WishlistActivity.this,txt_cart_count);

        txt_wish_count=(TextView)findViewById(R.id.txt_wish_count);
        Method.SetWishCount(WishlistActivity.this,txt_wish_count);

        header_cart=(ImageButton)findViewById(R.id.header_cart);
        header_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEffect.addClickEffect(header_cart);
                Launcher.CartActivity(WishlistActivity.this);
            }
        });
        header_wishlist=(ImageButton)findViewById(R.id.header_wishlist);

        txt_wish_count.setVisibility(View.VISIBLE);
        txt_cart_count.setVisibility(View.VISIBLE);
        header_cart.setVisibility(View.VISIBLE);
        header_wishlist.setVisibility(View.VISIBLE);

        ImageButton header_back=(ImageButton)findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.HomeActivity(WishlistActivity.this);
            }
        });

        wish_list_view=(ListView)findViewById(R.id.wish_list_view);
        list=new ArrayList<>();
        wish_list_view.setAdapter(new WishlistAdapter(WishlistActivity.this,list));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Launcher.HomeActivity(WishlistActivity.this);
        }
        return true;
    }
}
