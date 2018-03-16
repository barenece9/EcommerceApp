package com.lnsel.ecommerceapp.other;

import android.content.Context;
import android.content.Intent;

import com.lnsel.ecommerceapp.activity.AddressActivity;
import com.lnsel.ecommerceapp.activity.CartActivity;
import com.lnsel.ecommerceapp.activity.CheckoutActivity;
import com.lnsel.ecommerceapp.activity.DeliveryAddress;
import com.lnsel.ecommerceapp.activity.FilterActivity;
import com.lnsel.ecommerceapp.activity.ForgotPasswordActivity;
import com.lnsel.ecommerceapp.activity.HomeScreenActivity;
import com.lnsel.ecommerceapp.activity.LoginActivity;
import com.lnsel.ecommerceapp.activity.MyOrderActivity;
import com.lnsel.ecommerceapp.activity.OTPVerificationActivity;
import com.lnsel.ecommerceapp.activity.ProductActivity;
import com.lnsel.ecommerceapp.activity.RegistationActivity;
import com.lnsel.ecommerceapp.activity.ResetPasswordActivity;
import com.lnsel.ecommerceapp.activity.ShopItemActivity;
import com.lnsel.ecommerceapp.activity.WishlistActivity;

/**
 * Created by db on 6/27/2017.
 */
public class Launcher {

    public static void HomeActivity(Context context){
        Intent intent = new Intent(context, HomeScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void OrderActivity(Context context){
        Intent intent = new Intent(context, MyOrderActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void CartActivity(Context context){
        Intent intent = new Intent(context, CartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void WishListActivity(Context context){
        Intent intent = new Intent(context, WishlistActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void ProductActivity(Context context){
        Intent intent = new Intent(context, ProductActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void ShopItemActivity(Context context){
        Intent intent = new Intent(context, ShopItemActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void FilterActivity(Context context){
        Intent intent = new Intent(context, FilterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void CheckoutActivity(Context context){
        Intent intent = new Intent(context, CheckoutActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void AddressActivity(Context context){
        Intent intent = new Intent(context, AddressActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void DeliveryAddress(Context context){
        Intent intent = new Intent(context, DeliveryAddress.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void RegistationActivity(Context context){
        Intent intent = new Intent(context, RegistationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void LoginActivity(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void ForgotPasswordActivity(Context context){
        Intent intent = new Intent(context, ForgotPasswordActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void ResetPasswordActivity(Context context){
        Intent intent = new Intent(context, ResetPasswordActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void OTPVerificationActivity(Context context){
        Intent intent = new Intent(context, OTPVerificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }



}
