package com.lnsel.ecommerceapp.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.activity.ProductActivity;
import com.lnsel.ecommerceapp.activity.ShopItemActivity;
import com.lnsel.ecommerceapp.adapter.ItemClickListener;
import com.lnsel.ecommerceapp.adapter.MainFragmentAdapter;
import com.lnsel.ecommerceapp.adapter.Section;
import com.lnsel.ecommerceapp.adapter.SectionedExpandableLayoutHelper;
import com.lnsel.ecommerceapp.adapter.ShopAdapter;
import com.lnsel.ecommerceapp.appconroller.AppController;
import com.lnsel.ecommerceapp.constant.Constant;
import com.lnsel.ecommerceapp.constant.Webservice;
import com.lnsel.ecommerceapp.models.Item;
import com.lnsel.ecommerceapp.other.CreateDialog;
import com.lnsel.ecommerceapp.other.Launcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainFragment extends Fragment  {
    public static final String ARG_PAGE = "ARG_PAGE";
    private static final String TAG_COURSES = "allcourses";
    private static final String TAG_FEES = "course_fees";
    private static final String TAG_NAME = "course_name";
    private static final String TAG_DURATION = "course_duration";
    private static final String TAG_DESCRIPTION = "course_description";

    boolean _areLecturesLoaded = false;

    GridView grid;
    ArrayList<HashMap<String,String>> list;
    MainFragmentAdapter adapter;
    private static View rootView;
    public static MainFragment newInstance(int pageNo) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNo);
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        try {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
        } catch (InflateException e) {

        }


        list=new ArrayList<>();
        grid=(GridView)rootView.findViewById(R.id.grid);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
               // Toast.makeText(getActivity(), "You Clicked at " +list.get(position).get(""), Toast.LENGTH_SHORT).show();
                Constant.SubCategoryID=list.get(position).get("SubCategoryID");
                Launcher.ProductActivity(getActivity());

            }
        });

        doSubCategoryList("1");

        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && !_areLecturesLoaded ) {
            loadLectures();
            _areLecturesLoaded = false;
        }
    }
    public void loadLectures(){

    }


    public void doSubCategoryList(final String CategoryID){
        final ProgressDialog progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        String url;

        url = Webservice.PRODUCT_SUB_CATEGORY_URL;

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
                        Toast.makeText(getActivity(), "Have a Network Error Please check Internet Connection.", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("CategoryID",CategoryID);
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
                    item.put("SubCategoryID",obj.getString("SubCategoryID"));
                    item.put("CategoryID",obj.getString("CategoryID"));
                    item.put("SubCategoryName",obj.getString("SubCategoryName"));
                    item.put("image",obj.getString("image"));
                    list.add(item);
                }

            }else {
                String error_msg=parentObj.getString("response");
                CreateDialog.showDialog(getActivity(),error_msg);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        adapter = new MainFragmentAdapter(getActivity(), list);
        grid.setAdapter(adapter);
    }

}
