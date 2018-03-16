package com.lnsel.ecommerceapp.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.lnsel.ecommerceapp.appconroller.AppController;
import com.lnsel.ecommerceapp.constant.Webservice;
import com.lnsel.ecommerceapp.other.CreateDialog;
import com.lnsel.ecommerceapp.other.InternetConnectivity;
import com.lnsel.ecommerceapp.other.Launcher;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistationActivity extends AppCompatActivity {

    TextView user_login;
    EditText etn_mobile,etn_name,etn_email,etn_password;
    Button btn_sign_up;

    String mobile="",password="",name="",email="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);

        TextView header_title=(TextView)findViewById(R.id.header_title);
        header_title.setText("Registration");
        ImageButton header_back=(ImageButton)findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.HomeActivity(RegistationActivity.this);
            }
        });

        etn_mobile=(EditText)findViewById(R.id.etn_mobile);
        etn_name=(EditText)findViewById(R.id.etn_name);
        etn_email=(EditText)findViewById(R.id.etn_email);
        etn_password=(EditText)findViewById(R.id.etn_password);


        user_login=(TextView)findViewById(R.id.user_login);
        user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.LoginActivity(RegistationActivity.this);
            }
        });

        btn_sign_up=(Button)findViewById(R.id.btn_sign_up);
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobile=etn_mobile.getText().toString();
                name=etn_name.getText().toString();
                email=etn_email.getText().toString();
                password=etn_password.getText().toString();
                if(mobile.equalsIgnoreCase("")){
                    CreateDialog.showDialog(RegistationActivity.this,"Mobile Number can not be blank");
                }else if(name.equalsIgnoreCase("")){
                    CreateDialog.showDialog(RegistationActivity.this,"Name can not be blank");
                }else if(email.equalsIgnoreCase("")){
                    CreateDialog.showDialog(RegistationActivity.this,"Email can not be blank");
                }else if(password.equalsIgnoreCase("")){
                    CreateDialog.showDialog(RegistationActivity.this,"Password can not be blank");
                }else if(!InternetConnectivity.isConnectedFast(RegistationActivity.this)){
                    CreateDialog.showDialog(RegistationActivity.this,"Check your internet connection");
                }else {
                    //doRegistration();
                    Launcher.OTPVerificationActivity(RegistationActivity.this);
                }
            }
        });
    }

    public void doRegistration(){
        final ProgressDialog progressDialog=new ProgressDialog(RegistationActivity.this,R.style.CustomDialog);
        progressDialog.setMessage("loading...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        String url;

        url = Webservice.REGISTRATION_URL;

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        RegistrationResponse(response);
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
                        Toast.makeText(RegistationActivity.this, "Have a Network Error\nPlease check Internet Connection.", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("mobile", mobile);
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(postRequest);
    }

    public void RegistrationResponse(String responsedata){
        try {
            JSONObject parentObj = new JSONObject(responsedata);
            String status=parentObj.optString("status");

            if(status.equalsIgnoreCase("true")){

            }else {
                String error_msg=parentObj.getString("response");
                CreateDialog.showDialog(RegistationActivity.this,error_msg);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Launcher.HomeActivity(RegistationActivity.this);
        }
        return true;
    }
}
