package com.lnsel.ecommerceapp.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.adapter.CustomExpandableListAdapter;
import com.lnsel.ecommerceapp.adapter.FilterDialogAdapter;
import com.lnsel.ecommerceapp.appconroller.AppController;
import com.lnsel.ecommerceapp.constant.Constant;
import com.lnsel.ecommerceapp.constant.Webservice;
import com.lnsel.ecommerceapp.models.FilterItem;
import com.lnsel.ecommerceapp.models.Item;
import com.lnsel.ecommerceapp.other.CreateDialog;
import com.lnsel.ecommerceapp.other.Launcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterActivity extends AppCompatActivity {

    SeekBar seek_bar_price;
    TextView tv_min_price,tv_max_price,tv_brand_name;
    ArrayList<FilterItem> rowItem;
    FilterDialogAdapter adapter;
    ListView listView1;
    List<FilterItem> list;
    LinearLayout ll_category;

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        TextView header_title=(TextView)findViewById(R.id.header_title);
        header_title.setText("Filter");
        ImageButton header_back=(ImageButton)findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.ProductActivity(FilterActivity.this);
            }
        });

        tv_brand_name=(TextView)findViewById(R.id.tv_brand_name);
        tv_min_price =(TextView)findViewById(R.id.tv_min_price);
        seek_bar_price=(SeekBar) findViewById(R.id.seek_bar_price);
        seek_bar_price.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO Auto-generated method stub
                tv_min_price.setText("Min Price : "+progress);
            }
        });


        ll_category=(LinearLayout)findViewById(R.id.ll_category);
        ll_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableListView.setVisibility(View.VISIBLE);
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
                        expandableListTitle.get(i)+" Size : "+ expandableListDetail.get(
                                expandableListTitle.get(i)).size()
                        , Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
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


        LinearLayout ll_brand = (LinearLayout) findViewById(R.id.ll_brand);
        // add listener to button
        ll_brand.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // Create custom dialog object
                final Dialog dialog = new Dialog(FilterActivity.this);
                // Include dialog.xml file
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.filter_dialog);
                // Set dialog title
                dialog.setTitle("Select Brand");
                // set values for custom dialog components - text, image and button

                dialog.show();

                list = new ArrayList<FilterItem>();


                listView1 = (ListView) dialog.findViewById(R.id.listView1);
                listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View v, int i, long l) {
                        //TextView label = (TextView) v.getTag(R.id.textView1);
                       // CheckBox checkbox = (CheckBox) v.getTag(R.id.checkBox1);
                       // Toast.makeText(v.getContext(), label.getText().toString()+" "+isCheckedOrNot(checkbox), Toast.LENGTH_LONG).show();
                    }
                });
                FilterDialogAdapter adapter = new FilterDialogAdapter(FilterActivity.this,getModel());
                listView1.setAdapter(adapter);

                Button declineButton = (Button) dialog.findViewById(R.id.declineButton);
                // if decline button is clicked, close the custom dialog
                declineButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });

                Button btn_submit = (Button) dialog.findViewById(R.id.btn_submit);
                // if decline button is clicked, close the custom dialog
                btn_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        String brand_name="";
                        for (int i=0;i<list.size();i++){
                            if(list.get(i).getSelected()){
                                System.out.println(list.get(i).getItems());
                                if(brand_name.equalsIgnoreCase("")) {
                                    brand_name = list.get(i).getItems();
                                }else {
                                    brand_name = brand_name+","+list.get(i).getItems();
                                }
                            }
                        }
                        tv_brand_name.setText(brand_name);
                        dialog.dismiss();
                    }
                });




            }

        });

        doCategoryLavelList();
    }


    public void doCategoryLavelList(){
        final ProgressDialog progressDialog=new ProgressDialog(FilterActivity.this,R.style.CustomDialog);
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
                        Toast.makeText(FilterActivity.this, "Have a Network Error Please check Internet Connection.", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("SubCategoryID", Constant.SubCategoryID);
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
                CreateDialog.showDialog(FilterActivity.this,error_msg);
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


    private List<FilterItem> getModel() {
        list.clear();
        list.add(new FilterItem("Linux"));
        list.add(new FilterItem("Windows7"));
        list.add(new FilterItem("Suse"));
        list.add(new FilterItem("Eclipse"));
        list.add(new FilterItem("Ubuntu"));
        list.add(new FilterItem("Solaris"));
        list.add(new FilterItem("Android"));
        list.add(new FilterItem("iPhone"));
        list.add(new FilterItem("Java"));
        list.add(new FilterItem(".Net"));
        list.add(new FilterItem("PHP"));

        list.add(new FilterItem("Linux"));
        list.add(new FilterItem("Windows7"));
        list.add(new FilterItem("Suse"));
        list.add(new FilterItem("Eclipse"));
        list.add(new FilterItem("Ubuntu"));
        list.add(new FilterItem("Solaris"));
        list.add(new FilterItem("Android"));
        list.add(new FilterItem("iPhone"));
        list.add(new FilterItem("Java"));
        list.add(new FilterItem(".Net"));
        list.add(new FilterItem("PHP"));
        return list;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Launcher.ProductActivity(FilterActivity.this);
        }
        return true;
    }
}
