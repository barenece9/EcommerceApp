package com.lnsel.ecommerceapp.other;

import android.content.Context;
import android.widget.TextView;

import com.lnsel.ecommerceapp.database.DatabaseHandler;

/**
 * Created by db on 6/27/2017.
 */
public class Method {

    public static void SetCartCount(Context context,TextView txt_cart_count){
        DatabaseHandler db = new DatabaseHandler(context);
        txt_cart_count.setText(String.valueOf(db.getCartCount()));
    }

    public static void SetWishCount(Context context,TextView txt_wish_count){
        DatabaseHandler db = new DatabaseHandler(context);
        txt_wish_count.setText(String.valueOf(db.getWishCount()));
    }
}
