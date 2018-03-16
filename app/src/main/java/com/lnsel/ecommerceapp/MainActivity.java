package com.lnsel.ecommerceapp;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lnsel.ecommerceapp.activity.CartActivity;
import com.lnsel.ecommerceapp.activity.WishlistActivity;
import com.lnsel.ecommerceapp.constant.Constant;
import com.lnsel.ecommerceapp.fragment.AccountFragment;
import com.lnsel.ecommerceapp.fragment.CategoriesFragment;
import com.lnsel.ecommerceapp.fragment.CategoryFragment;
import com.lnsel.ecommerceapp.fragment.HomeFragment;
import com.lnsel.ecommerceapp.fragment.DailyFragment;
import com.lnsel.ecommerceapp.fragment.LivingFragment;
import com.lnsel.ecommerceapp.fragment.ElectronicsFragment;
import com.lnsel.ecommerceapp.fragment.MainFragment;
import com.lnsel.ecommerceapp.fragment.MoreFragment;
import com.lnsel.ecommerceapp.fragment.ShopFragment;
import com.lnsel.ecommerceapp.fragment.FashionFragment;
import com.lnsel.ecommerceapp.navigation.FragmentDrawer;
import com.lnsel.ecommerceapp.other.ClickEffect;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    TextView tv_title;
    LinearLayout ll_home,ll_shop,ll_account,ll_more;
    String title="";
    ImageButton toolbar_cart,toolbar_wishlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        tv_title=(TextView)findViewById(R.id.tv_title);
        toolbar_cart=(ImageButton)findViewById(R.id.toolbar_cart);
        toolbar_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEffect.addClickEffect(toolbar_cart);
                startActivity(new Intent(MainActivity.this, CartActivity.class));
            }
        });
        toolbar_wishlist=(ImageButton)findViewById(R.id.toolbar_wishlist);
        toolbar_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEffect.addClickEffect(toolbar_wishlist);
                startActivity(new Intent(MainActivity.this, WishlistActivity.class));
            }
        });

        ll_home=(LinearLayout)findViewById(R.id.ll_home);
        ll_shop=(LinearLayout)findViewById(R.id.ll_shop);
        ll_account=(LinearLayout)findViewById(R.id.ll_account);
        ll_more=(LinearLayout)findViewById(R.id.ll_more);

        ll_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_home.setBackgroundColor(Color.parseColor("#f1441a"));
                ll_shop.setBackgroundColor(Color.TRANSPARENT);
                ll_account.setBackgroundColor(Color.TRANSPARENT);
                ll_more.setBackgroundColor(Color.TRANSPARENT);
                displayView(4);
            }
        });
        ll_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_home.setBackgroundColor(Color.TRANSPARENT);
                ll_shop.setBackgroundColor(Color.parseColor("#f1441a"));
                ll_account.setBackgroundColor(Color.TRANSPARENT);
                ll_more.setBackgroundColor(Color.TRANSPARENT);
                displayView(5);
            }
        });
        ll_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_home.setBackgroundColor(Color.TRANSPARENT);
                ll_shop.setBackgroundColor(Color.TRANSPARENT);
                ll_account.setBackgroundColor(Color.parseColor("#f1441a"));
                ll_more.setBackgroundColor(Color.TRANSPARENT);
                displayView(6);
            }
        });
        ll_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_home.setBackgroundColor(Color.TRANSPARENT);
                ll_shop.setBackgroundColor(Color.TRANSPARENT);
                ll_account.setBackgroundColor(Color.TRANSPARENT);
                ll_more.setBackgroundColor(Color.parseColor("#f1441a"));
                displayView(8);
            }
        });

        TextView nav_home=(TextView) findViewById(R.id.nav_home);
        TextView nav_shop=(TextView) findViewById(R.id.nav_shop);
        TextView nav_account=(TextView) findViewById(R.id.nav_account);
        TextView nav_more=(TextView) findViewById(R.id.nav_more);
        nav_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(4);
                drawerFragment.close();
            }
        });
        nav_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(5);
                drawerFragment.close();
            }
        });
        nav_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(6);
                drawerFragment.close();
            }
        });
        nav_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(7);
                drawerFragment.close();
            }
        });

        // display the first navigation drawer view on app launch
        displayView(4);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "Setting", Toast.LENGTH_SHORT).show();
            return true;
        }

        if(id == R.id.action_search){
            Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }



    private void displayView(int position) {
        Fragment fragment = null;
        title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new MainFragment();
                title = getString(R.string.title_fashion);
                Constant.CategoryID="1";
                break;
            case 1:
                fragment = new MainFragment();
                title = getString(R.string.title_electronics);
                Constant.CategoryID="2";
                break;
            case 2:
                fragment = new MainFragment();
                title = getString(R.string.title_living);
                Constant.CategoryID="3";
                break;
            case 3:
                fragment = new MainFragment();
                title = getString(R.string.title_daily_needs);
                Constant.CategoryID="4";
                break;

            case 4:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 5:
                fragment = new ShopFragment();
                title = getString(R.string.title_shop);
                break;
            case 6:
                fragment = new AccountFragment();
                title = getString(R.string.title_account);
                break;
            case 7:
                fragment = new MainFragment();
                title = getString(R.string.title_more);
                Constant.CategoryID="5";
                break;

            case 8:
                fragment = new CategoryFragment();
                title = "Category";//getString(R.string.title_more);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            // set the toolbar title
            getSupportActionBar().setTitle(title);
            tv_title.setText(title);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(title.equalsIgnoreCase("Home")){
                finish();
            }else {
                displayView(4);
                drawerFragment.close();
            }
        }
        return true;
    }



    public void Close() {
       FragmentDrawer drawer=new FragmentDrawer();
        drawer.close();
    }
}