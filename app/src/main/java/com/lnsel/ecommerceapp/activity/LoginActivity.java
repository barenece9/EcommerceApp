package com.lnsel.ecommerceapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.adapter.CustomExpandableListAdapter;
import com.lnsel.ecommerceapp.appconroller.AppController;
import com.lnsel.ecommerceapp.constant.Constant;
import com.lnsel.ecommerceapp.constant.Webservice;
import com.lnsel.ecommerceapp.models.Item;
import com.lnsel.ecommerceapp.other.CreateDialog;
import com.lnsel.ecommerceapp.other.InternetConnectivity;
import com.lnsel.ecommerceapp.other.Launcher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by db on 5/2/2017.
 */
public class LoginActivity extends AppCompatActivity{

    TextView user_registetion,txt_forgot_password;
    EditText etn_mobile,etn_password;
    Button btn_login;

    String mobile="",password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        TextView header_title=(TextView)findViewById(R.id.header_title);
        header_title.setText("Login");
        ImageButton header_back=(ImageButton)findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.HomeActivity(LoginActivity.this);
            }
        });

        user_registetion=(TextView)findViewById(R.id.user_registetion);
        user_registetion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.RegistationActivity(LoginActivity.this);
            }
        });
        txt_forgot_password=(TextView)findViewById(R.id.txt_forgot_password);
        txt_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.ForgotPasswordActivity(LoginActivity.this);
            }
        });

        etn_mobile=(EditText)findViewById(R.id.etn_mobile);
        etn_password=(EditText)findViewById(R.id.etn_password);

        btn_login=(Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobile=etn_mobile.getText().toString();
                password=etn_password.getText().toString();
                if(mobile.equalsIgnoreCase("")){
                    CreateDialog.showDialog(LoginActivity.this,"Mobile Number can not be blank");
                }else if(password.equalsIgnoreCase("")){
                    CreateDialog.showDialog(LoginActivity.this,"Password can not be blank");
                }else if(!InternetConnectivity.isConnectedFast(LoginActivity.this)){
                    CreateDialog.showDialog(LoginActivity.this,"Check your internet connection");
                }else {
                    doLogin();
                }
            }
        });
    }

    public void doLogin(){
        final ProgressDialog progressDialog=new ProgressDialog(LoginActivity.this,R.style.CustomDialog);
        progressDialog.setMessage("loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        String url;

        url = Webservice.LOG_IN_URL;

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        LoginResponse(response);
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
                        Toast.makeText(LoginActivity.this, "Have a Network Error\nPlease check Internet Connection.", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("mobile", mobile);
                params.put("password", password);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(postRequest);
    }

    public void LoginResponse(String responsedata){
        try {
            JSONObject parentObj = new JSONObject(responsedata);
            String status=parentObj.optString("status");

            if(status.equalsIgnoreCase("true")){

            }else {
                String error_msg=parentObj.getString("response");
                CreateDialog.showDialog(LoginActivity.this,error_msg);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Launcher.HomeActivity(LoginActivity.this);
        }
        return true;
    }
}
