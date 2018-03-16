package com.lnsel.ecommerceapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.adapter.CustomExpandableListAdapter;
import com.lnsel.ecommerceapp.adapter.MainFragmentAdapter;
import com.lnsel.ecommerceapp.adapter.ProductAdapter;
import com.lnsel.ecommerceapp.appconroller.AppController;
import com.lnsel.ecommerceapp.constant.Constant;
import com.lnsel.ecommerceapp.constant.Webservice;
import com.lnsel.ecommerceapp.database.DatabaseHandler;
import com.lnsel.ecommerceapp.models.Item;
import com.lnsel.ecommerceapp.models.WishItemIO;
import com.lnsel.ecommerceapp.other.ClickEffect;
import com.lnsel.ecommerceapp.other.CreateDialog;
import com.lnsel.ecommerceapp.other.Internet;
import com.lnsel.ecommerceapp.other.Launcher;
import com.lnsel.ecommerceapp.other.Method;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductActivity extends AppCompatActivity {

    GridView grid;
    ArrayList<HashMap<String,String>> list=new ArrayList<>();
    ProductAdapter adapter;
    ImageView header_back;

    ImageButton header_cart,header_wishlist;
    TextView txt_cart_count,txt_wish_count;

    Button btn_filter;
    Button btn_toggle_on,btn_toggle_off;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        TextView header_title=(TextView)findViewById(R.id.header_title);
        header_title.setText("Product Category");

        txt_cart_count=(TextView)findViewById(R.id.txt_cart_count);
        Method.SetCartCount(ProductActivity.this,txt_cart_count);

        txt_wish_count=(TextView)findViewById(R.id.txt_wish_count);
        Method.SetWishCount(ProductActivity.this,txt_wish_count);

        header_cart=(ImageButton)findViewById(R.id.header_cart);
        header_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEffect.addClickEffect(header_cart);
                Launcher.CartActivity(ProductActivity.this);
            }
        });
        header_wishlist=(ImageButton)findViewById(R.id.header_wishlist);
        header_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEffect.addClickEffect(header_wishlist);
                Launcher.WishListActivity(ProductActivity.this);
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
                Launcher.HomeActivity(ProductActivity.this);
            }
        });

        grid=(GridView)findViewById(R.id.grid);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Launcher.ShopItemActivity(ProductActivity.this);
            }
        });

        btn_toggle_on=(Button)findViewById(R.id.btn_toggle_on);
        btn_toggle_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_toggle_on.setVisibility(View.GONE);
                btn_toggle_off.setVisibility(View.VISIBLE);
                expandableListView.setVisibility(View.VISIBLE);
            }
        });
        btn_toggle_off=(Button)findViewById(R.id.btn_toggle_off);
        btn_toggle_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_toggle_on.setVisibility(View.VISIBLE);
                btn_toggle_off.setVisibility(View.GONE);
                expandableListView.setVisibility(View.GONE);
            }
        });
        btn_filter=(Button)findViewById(R.id.btn_filter);
        btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.FilterActivity(ProductActivity.this);
            }
        });


        // expendable list view
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT);
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT);

            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(i)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(i)).get(
                                i), Toast.LENGTH_SHORT
                ).show();

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                btn_toggle_on.setVisibility(View.VISIBLE);
                btn_toggle_off.setVisibility(View.GONE);
                expandableListView.setVisibility(View.GONE);

                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                );
                return false;
            }
        });


        if(Internet.haveNetworkConnection(ProductActivity.this)) {
            doSubCategoryList(Constant.SubCategoryID);
        }else {
            Toast.makeText(getApplicationContext(),"CHECK YOUR INTERNET CONNECTION",Toast.LENGTH_LONG).show();
        }

    }


    public void doSubCategoryList(final String SubCategoryID){
        final ProgressDialog progressDialog=new ProgressDialog(ProductActivity.this,R.style.CustomDialog);
        progressDialog.setMessage("loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        String url;

        url = Webservice.PRODUCT_BY_SUB_CATEGORY;

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
                        Toast.makeText(ProductActivity.this, "Have a Network Error Please check Internet Connection.", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("SubCategoryID",SubCategoryID);
                return params;
            }
        };
        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(postRequest);
    }

    public void JSONresponse(String responsedata){

        list.clear();
        try {
            JSONObject parentObj = new JSONObject(responsedata);
            String status=parentObj.optString("status");
            if(status.equalsIgnoreCase("true")){
                JSONArray result=parentObj.getJSONArray("result");
                for(int i=0;i<result.length();i++){
                    HashMap<String,String> item=new HashMap<>();
                    JSONObject obj=result.getJSONObject(i);
                    item.put("ProductID",obj.getString("ProductID"));
                    item.put("ProductSKU",obj.getString("ProductSKU"));
                    item.put("ProductName",obj.getString("ProductName"));

                    item.put("ProductPrice",obj.getString("ProductPrice"));
                    item.put("ProductSalesPrice",obj.getString("ProductSalesPrice"));

                    item.put("ProductWeight",obj.getString("ProductWeight"));
                    item.put("ProductCartDesc",obj.getString("ProductCartDesc"));

                    item.put("ProductShortDesc",obj.getString("ProductShortDesc"));
                    item.put("ProductLongDesc",obj.getString("ProductLongDesc"));
                    item.put("ProductImage",obj.getString("ProductImage"));


                    list.add(item);
                }

            }else {
                String error_msg=parentObj.getString("response");
                CreateDialog.showDialog(ProductActivity.this,error_msg);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        adapter = new ProductAdapter(ProductActivity.this, list);
        grid.setAdapter(adapter);
        doCategoryLavelList();
    }




    public void doCategoryLavelList(){
        final ProgressDialog progressDialog=new ProgressDialog(ProductActivity.this,R.style.CustomDialog);
        progressDialog.setMessage("loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        String url;

        url = Webservice.CATEGORY_LAVLE;

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        CategoryLavelResponse(response);
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
                        Toast.makeText(ProductActivity.this, "Have a Network Error Please check Internet Connection.", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("SubCategoryID",Constant.SubCategoryID);
                return params;
            }
        };
        // Adding request to volley request queue
        AppController.getInstance().addToRequestQueue(postRequest);
    }

    public void CategoryLavelResponse(String responsedata){
        try {
            JSONObject parentObj = new JSONObject(responsedata);
            String status=parentObj.optString("status");

            if(status.equalsIgnoreCase("true")){
                JSONArray result=parentObj.getJSONArray("result");
                expandableListDetail = new HashMap<String, List<String>>();
                ArrayList<Item> arrayList;
                for(int i=0;i<result.length();i++){
                    HashMap<String,String> item=new HashMap<>();
                    List<String> sub_item = new ArrayList<String>();
                    JSONObject obj=result.getJSONObject(i);
                    //item.put("SubCategory_Label_2_ID",obj.getString("SubCategory_Label_2_ID"));
                    //item.put("SubCategoryLabel_2_Name",obj.getString("SubCategoryLabel_2_Name"));
                    JSONArray SubCategory=obj.getJSONArray("SubCategory");
                    for (int j=0;j<SubCategory.length();j++){
                        JSONObject innerobj=SubCategory.getJSONObject(j);
                        //item.put("SubCategory_Label_3_ID",innerobj.getString("SubCategory_Label_3_ID"));
                        //item.put("SubCategory_Label2_ID",innerobj.getString("SubCategory_Label2_ID"));
                        //item.put("SubCategory_Label3_Name",innerobj.getString("SubCategory_Label3_Name"));
                        sub_item.add(innerobj.getString("SubCategory_Label3_Name"));
                    }
                    expandableListDetail.put(obj.getString("SubCategoryLabel_2_Name"), sub_item);
                }

            }else {
                String error_msg=parentObj.getString("response");
                CreateDialog.showDialog(ProductActivity.this,error_msg);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       // expandableListDetail = getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
    }


    public void addWishList(int postion){
        DatabaseHandler db = new DatabaseHandler(ProductActivity.this);
        Log.d("Insert: ", "Inserting ..");
        db.addWish(new WishItemIO(Integer.valueOf(list.get(postion).get("ProductID")),"Ravi", "9100000000"));
        Method.SetWishCount(ProductActivity.this,txt_wish_count);
    }







    public  HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();
        List<String> cricket = new ArrayList<String>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("England");
        cricket.add("South Africa");
        List<String> football = new ArrayList<String>();
        football.add("Brazil");
        football.add("Spain");
        football.add("Germany");
        football.add("Netherlands");
        football.add("Italy");
        List<String> basketball = new ArrayList<String>();
      /*  basketball.add("United States");
        basketball.add("Spain");
        basketball.add("Argentina");
        basketball.add("France");
        basketball.add("Russia");*/
        expandableListDetail.put("CRICKET TEAMS", cricket);
        expandableListDetail.put("FOOTBALL TEAMS", football);
        expandableListDetail.put("BASKETBALL TEAMS", basketball);
        return expandableListDetail;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Launcher.HomeActivity(ProductActivity.this);
        }
        return true;
    }
}
