package com.lnsel.ecommerceapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.adapter.CheckoutItemAdapter;
import com.lnsel.ecommerceapp.adapter.WishlistAdapter;
import com.lnsel.ecommerceapp.other.ClickEffect;
import com.lnsel.ecommerceapp.other.Launcher;
import com.lnsel.ecommerceapp.other.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("ALL")
public class CheckoutActivity extends AppCompatActivity {

    final int TOP_ID = 3;
    final int BOTTOM_ID = 4;

    ImageButton header_cart,header_wishlist;
    TextView txt_cart_count,txt_wish_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        TextView header_title=(TextView)findViewById(R.id.header_title);
        header_title.setText("Checkout");

        txt_cart_count=(TextView)findViewById(R.id.txt_cart_count);
        Method.SetCartCount(CheckoutActivity.this,txt_cart_count);

        txt_wish_count=(TextView)findViewById(R.id.txt_wish_count);
        Method.SetWishCount(CheckoutActivity.this,txt_wish_count);

        header_cart=(ImageButton)findViewById(R.id.header_cart);
        header_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEffect.addClickEffect(header_cart);
                Launcher.CartActivity(CheckoutActivity.this);
            }
        });
        header_wishlist=(ImageButton)findViewById(R.id.header_wishlist);
        header_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEffect.addClickEffect(header_wishlist);
                Launcher.WishListActivity(CheckoutActivity.this);
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
                Launcher.ShopItemActivity(CheckoutActivity.this);
            }
        });

        ImageButton img_btn_edit_address=(ImageButton)findViewById(R.id.img_btn_edit_address);
        img_btn_edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.DeliveryAddress(CheckoutActivity.this);
            }
        });


        LinearLayout ll_parent_programmatically = (LinearLayout) findViewById(R.id.ll_parent_programmatically);
        for(int i=0;i<=2;i++) {

            LinearLayout ll_parent_child = new LinearLayout(this);
            ll_parent_child.setId(i);
            ll_parent_child.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams ll_parms_child = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ll_parms_child.gravity = Gravity.CENTER_VERTICAL;
            ll_parms_child.setMargins(0, 5, 0, 0);
            ll_parent_child.setLayoutParams(ll_parms_child);


            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setBackgroundResource(R.drawable.women_model);
            LinearLayout.LayoutParams image_parms = new LinearLayout.LayoutParams(150, 150);
            image_parms.gravity = Gravity.CENTER_VERTICAL;
            imageView.setLayoutParams(image_parms);


            LinearLayout ll_parent = new LinearLayout(this);
            ll_parent.setId(i);
            ll_parent.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams ll_parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 190);
            ll_parms.gravity = Gravity.CENTER_VERTICAL;
            ll_parent.setLayoutParams(ll_parms);


            TextView tv_product_name = new TextView(this);
            tv_product_name.setText("Benetton Beige Casuals Slim Fit Shirt");
            tv_product_name.setTextColor(Color.parseColor("#795548"));
            tv_product_name.setTypeface(Typeface.DEFAULT_BOLD);
            tv_product_name.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
            ll_parent.addView(tv_product_name);


            TextView tv_quantity = new TextView(this);
            tv_quantity.setText("Quantity : 2");
            tv_quantity.setTextColor(Color.parseColor("#898C88"));
            tv_quantity.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
            ll_parent.addView(tv_quantity);


            TextView tv_charge = new TextView(this);
            tv_charge.setText("Charge : Rs. 292");
            tv_charge.setTextColor(Color.parseColor("#898C88"));
            tv_charge.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
            ll_parent.addView(tv_charge);


            TextView tv_price = new TextView(this);
            tv_price.setText("Unit Price : Rs. 1,000");
            tv_price.setTextColor(Color.parseColor("#898C88"));
            tv_price.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
            ll_parent.addView(tv_price);

            TextView tv_payable_amount = new TextView(this);
            tv_payable_amount.setText("Payable Amount : Rs. 1,292");
            tv_payable_amount.setTextColor(Color.parseColor("#f85534"));
            tv_payable_amount.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
            ll_parent.addView(tv_payable_amount);



            ll_parent_child.addView(imageView);
            ll_parent_child.addView(ll_parent);

            ll_parent_programmatically.addView(ll_parent_child);
            //ll_parent_programmatically.addView(imageView);
           // ll_parent_programmatically.addView(ll_parent);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Launcher.ShopItemActivity(CheckoutActivity.this);
        }
        return true;
    }
}
