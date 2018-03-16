package com.lnsel.ecommerceapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.effect.Effect;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.adapter.CustomArrayAdapter;
import com.lnsel.ecommerceapp.adapter.CustomArrayAdapter2;
import com.lnsel.ecommerceapp.adapter.CustomColorAdapter;
import com.lnsel.ecommerceapp.adapter.CustomData;
import com.lnsel.ecommerceapp.adapter.CustomSizeAdapter;
import com.lnsel.ecommerceapp.adapter.ProductAdapter;
import com.lnsel.ecommerceapp.appconroller.AppController;
import com.lnsel.ecommerceapp.constant.Constant;
import com.lnsel.ecommerceapp.constant.Webservice;
import com.lnsel.ecommerceapp.database.DatabaseHandler;
import com.lnsel.ecommerceapp.imageslider.BaseSliderView;
import com.lnsel.ecommerceapp.imageslider.DescriptionAnimation;
import com.lnsel.ecommerceapp.imageslider.SliderLayout;
import com.lnsel.ecommerceapp.imageslider.TextSliderView;
import com.lnsel.ecommerceapp.imageslider.ViewPagerEx;
import com.lnsel.ecommerceapp.models.CartItemIO;
import com.lnsel.ecommerceapp.models.HorizontalListView;
import com.lnsel.ecommerceapp.models.WishItemIO;
import com.lnsel.ecommerceapp.other.ClickEffect;
import com.lnsel.ecommerceapp.other.CreateDialog;
import com.lnsel.ecommerceapp.other.Launcher;
import com.lnsel.ecommerceapp.other.Method;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ShopItemActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    Button btn_add_to_cart,btn_buy_now;
    TextView ProductName,ProductSalesPrice,ProductPrice,ProductCartDesc;
    ImageView product_image_view,header_back;
    ImageButton header_cart,header_wishlist;
    ImageButton btn_wish;
    TextView txt_cart_count,txt_wish_count;
    AnimationDrawable animation;
    private HorizontalListView hlvCustomList_size;
    private HorizontalListView hlvCustomList_color;
    private String[] mSimpleListValues = new String[] { "26", "28", "30",
            "32", "34", "36", "38", "40",
            "42", "44" };


    private CustomData[] mCustomData_size = new CustomData[] {
            new CustomData(Color.RED, "26"),
            new CustomData(Color.DKGRAY, "28"),
            new CustomData(Color.GREEN, "30"),
            new CustomData(Color.LTGRAY, "32"),
            new CustomData(Color.WHITE, "34"),
            new CustomData(Color.RED, "36"),
            new CustomData(Color.BLACK, "38"),
            new CustomData(Color.CYAN, "40"),
            new CustomData(Color.DKGRAY, "42"),
            new CustomData(Color.GREEN, "44"),
    };

    private CustomData[] mCustomData_color = new CustomData[] {
            new CustomData(Color.RED, "RED"),
            new CustomData(Color.DKGRAY, "DKGRAY"),
            new CustomData(Color.GREEN, "GREEN"),
            new CustomData(Color.LTGRAY, "LTGRAY"),
            new CustomData(Color.WHITE, "WHITE"),
    };

    private SliderLayout mDemoSlider;
    HashMap<String,String> url_maps;
    Boolean isSlider=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_layout);

        TextView header_title=(TextView)findViewById(R.id.header_title);

        txt_cart_count=(TextView)findViewById(R.id.txt_cart_count);
        Method.SetCartCount(ShopItemActivity.this,txt_cart_count);

        txt_wish_count=(TextView)findViewById(R.id.txt_wish_count);
        Method.SetWishCount(ShopItemActivity.this,txt_wish_count);

        header_cart=(ImageButton)findViewById(R.id.header_cart);
        header_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEffect.addClickEffect(header_cart);
                Launcher.CartActivity(ShopItemActivity.this);
            }
        });
        header_wishlist=(ImageButton)findViewById(R.id.header_wishlist);
        header_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEffect.addClickEffect(header_wishlist);
                Launcher.WishListActivity(ShopItemActivity.this);
            }
        });

        txt_wish_count.setVisibility(View.VISIBLE);
        txt_cart_count.setVisibility(View.VISIBLE);
        header_cart.setVisibility(View.VISIBLE);
        header_wishlist.setVisibility(View.VISIBLE);
       // header_title.setText("Address");
        ImageButton header_back=(ImageButton)findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.ProductActivity(ShopItemActivity.this);
            }
        });

        ProductName=(TextView)findViewById(R.id.ProductName);
        ProductSalesPrice=(TextView)findViewById(R.id.ProductSalesPrice);
        ProductPrice=(TextView)findViewById(R.id.ProductPrice);
        ProductCartDesc=(TextView)findViewById(R.id.ProductCartDesc);


        btn_add_to_cart=(Button)findViewById(R.id.btn_add_to_cart);
        btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHandler db = new DatabaseHandler(ShopItemActivity.this);
                Log.d("Insert: ", "Inserting ..");
                db.addCart(new CartItemIO(Integer.valueOf(Constant.ProductID),"Ravi", "9100000000"));
                ClickEffect.addClickEffect(btn_add_to_cart);
                Method.SetCartCount(ShopItemActivity.this,txt_cart_count);
            }
        });
        btn_buy_now=(Button)findViewById(R.id.btn_buy_now);
        btn_buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEffect.addClickEffect(btn_buy_now);
                Launcher.CheckoutActivity(ShopItemActivity.this);

            }
        });

        btn_wish=(ImageButton)findViewById(R.id.btn_wish);
        btn_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHandler db = new DatabaseHandler(ShopItemActivity.this);
                Log.d("Insert: ", "Inserting ..");
                db.addWish(new WishItemIO(Integer.valueOf(Constant.ProductID),"Ravi", "9100000000"));
                ClickEffect.addClickEffect(btn_wish);
                Method.SetWishCount(ShopItemActivity.this,txt_wish_count);

            }
        });

        ClickEffect.addClickEffect(btn_buy_now);
        ClickEffect.addClickEffect(btn_add_to_cart);

        product_image_view=(ImageView)findViewById(R.id.product_image_view) ;

        // Get references to UI widgets
        hlvCustomList_size = (HorizontalListView) findViewById(R.id.hlvCustomList_size);
        hlvCustomList_color = (HorizontalListView) findViewById(R.id.hlvCustomList_color);
        hlvCustomList_size.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Click",Toast.LENGTH_SHORT).show();
            }
        });

        hlvCustomList_color.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Click",Toast.LENGTH_SHORT).show();
            }
        });

        setupCustomSizeAdapter();
        setupCustomColorAdapter();


        // image slider..............

        mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        url_maps = new HashMap<String, String>();
        //url_maps.put("Hannibal","http://192.168.11.5/ecommerceapp/main-product/T-Shirts1.jpg");
        //url_maps.put("Big Bang Theory", "http://192.168.11.5/ecommerceapp/main-product/T-Shirts1b.jpg");
        //url_maps.put("House of Cards", "http://192.168.11.5/ecommerceapp/main-product/T-Shirts1c.jpg");
        //url_maps.put("Game of Thrones", "http://192.168.11.5/ecommerceapp/main-product/T-Shirts1d.jpg");

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        /*file_maps.put("Hannibal",R.drawable.hannibal);
        file_maps.put("Big Bang Theory",R.drawable.bigbang);
        file_maps.put("House of Cards",R.drawable.house);
        file_maps.put("Game of Thrones", R.drawable.game_of_thrones);*/
        file_maps.put("Hannibal",R.drawable.product1);
        file_maps.put("Big Bang Theory",R.drawable.product2);
        file_maps.put("House of Cards",R.drawable.product3);
        file_maps.put("Game of Thrones", R.drawable.product4);

        doProductDetails();

    }

    private void setupSimpleList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ShopItemActivity.this, android.R.layout.simple_list_item_2, android.R.id.text1, mSimpleListValues);
       // mHlvCustomList.setAdapter(adapter);
    }

    private void setupCustomSizeAdapter() {
        CustomSizeAdapter adapter2 = new CustomSizeAdapter(ShopItemActivity.this, mCustomData_size);
        hlvCustomList_size.setAdapter(adapter2);
    }

    private void setupCustomColorAdapter() {
        CustomColorAdapter adapter = new CustomColorAdapter(ShopItemActivity.this, mCustomData_color);
        hlvCustomList_color.setAdapter(adapter);
    }



    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}



    public void doProductDetails(){
        final ProgressDialog progressDialog=new ProgressDialog(ShopItemActivity.this,R.style.CustomDialog);
        progressDialog.setMessage("loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        String url;

        url = Webservice.PRODUCT_DETAILS;

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        JSONresponse(response);
                        if(progressDialog!=null)
                            progressDialog.dismiss();
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(progressDialog!=null)
                            progressDialog.dismiss();
                        System.out.println("Error=========="+error);
                        Toast.makeText(ShopItemActivity.this, "Have a Network Error Please check Internet Connection.", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("ProductID", Constant.ProductID);
                return params;
            }
        };
        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(postRequest);
    }

    public void JSONresponse(String responsedata){
        try {
            JSONObject parentObj = new JSONObject(responsedata);
            String status=parentObj.optString("status");
            if(status.equalsIgnoreCase("true")){
                url_maps.clear();
                JSONArray details=parentObj.getJSONArray("details");

                for(int i=0;i<details.length();i++){
                    HashMap<String,String> item=new HashMap<>();
                    JSONObject obj=details.getJSONObject(i);

                    ProductName.setText(obj.getString("ProductName"));
                    ProductSalesPrice.setText("Rs. "+obj.getString("ProductSalesPrice"));
                    ProductPrice.setText("Rs. "+obj.getString("ProductPrice"));
                    ProductCartDesc.setText("Cash on delivery available");//obj.getString("ProductCartDesc")

                    /*String image_url= Webservice.IMAGE_BASE_URL+obj.getString("ProductImage");
                    Picasso.with(ShopItemActivity.this)
                            .load(image_url)
                            .placeholder(R.drawable.game_of_thrones)
                            //.resize(90, 90)
                            //.centerCrop()
                            .into(product_image_view);*/

                }

                JSONArray images=parentObj.getJSONArray("images");
                for(int i=0;i<images.length();i++){
                    JSONObject obj=images.getJSONObject(i);
                    String imageName="Image "+obj.getString("imageID");
                    String imagePath=Webservice.IMAGE_BASE_URL+obj.getString("imagePath");
                    url_maps.put(imageName,imagePath);
                }

            }else {
                String error_msg=parentObj.getString("response");
                CreateDialog.showDialog(ShopItemActivity.this,error_msg);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setSlider();

    }

    public void setSlider(){

        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(5000);
        mDemoSlider.addOnPageChangeListener(this);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Launcher.ProductActivity(ShopItemActivity.this);
        }
        return true;
    }
}
