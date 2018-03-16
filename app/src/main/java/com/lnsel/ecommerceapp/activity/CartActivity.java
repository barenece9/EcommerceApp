package com.lnsel.ecommerceapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.adapter.CartAdapter;
import com.lnsel.ecommerceapp.database.DatabaseHandler;
import com.lnsel.ecommerceapp.models.CartItemIO;
import com.lnsel.ecommerceapp.other.ClickEffect;
import com.lnsel.ecommerceapp.other.Launcher;
import com.lnsel.ecommerceapp.other.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    ListView cart_list_view;
    ArrayList< HashMap<String,String>> list;
    Button btn_continue;

    ImageButton header_cart,header_wishlist;
    TextView txt_cart_count,txt_wish_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        TextView header_title=(TextView)findViewById(R.id.header_title);
        header_title.setText("Shopping Cart");

        txt_cart_count=(TextView)findViewById(R.id.txt_cart_count);
        Method.SetCartCount(CartActivity.this,txt_cart_count);

        txt_wish_count=(TextView)findViewById(R.id.txt_wish_count);
        Method.SetWishCount(CartActivity.this,txt_wish_count);

        header_cart=(ImageButton)findViewById(R.id.header_cart);
        header_wishlist=(ImageButton)findViewById(R.id.header_wishlist);
        header_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEffect.addClickEffect(header_wishlist);
                Launcher.WishListActivity(CartActivity.this);
            }
        });

        txt_wish_count.setVisibility(View.VISIBLE);
        txt_cart_count.setVisibility(View.VISIBLE);
        header_cart.setVisibility(View.VISIBLE);
        header_wishlist.setVisibility(View.VISIBLE);

        ImageButton header_back=(ImageButton)findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.HomeActivity(CartActivity.this);
            }
        });

        cart_list_view=(ListView)findViewById(R.id.cart_list_view);
        list=new ArrayList<>();
        cart_list_view.setAdapter(new CartAdapter(CartActivity.this,list));

        btn_continue=(Button)findViewById(R.id.btn_continue);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.CheckoutActivity(CartActivity.this);
            }
        });


        // Reading all contacts
        DatabaseHandler db = new DatabaseHandler(CartActivity.this);
        Log.d("Reading: ", "Reading all contacts..");
        List<CartItemIO> contacts = db.getAllCart();

        for (CartItemIO cn : contacts) {
            String log = "Id: "+cn.getID()+", Product Id: "+cn.getProductID()+ ", Name: " + cn.getName() + ", Phone: " + cn.getUnitPrice();
            // Writing Contacts to log
            Log.d("Name: ", log);

        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Launcher.HomeActivity(CartActivity.this);
        }
        return true;
    }
}
