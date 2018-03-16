package com.lnsel.ecommerceapp.activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.other.Launcher;

public class AddressActivity extends AppCompatActivity {

    private EditText input_pincode, input_name, input_address,input_landmark,
            input_city,input_state,input_mobile;
    private TextInputLayout input_layout_pincode, input_layout_name, input_layout_address,input_layout_landmark,
            input_layout_city,input_layout_state,input_layout_mobile;
    private Button btn_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        TextView header_title=(TextView)findViewById(R.id.header_title);
        header_title.setText("Address");
        ImageButton header_back=(ImageButton)findViewById(R.id.header_back);
        header_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.HomeActivity(AddressActivity.this);
            }
        });



        input_layout_pincode = (TextInputLayout) findViewById(R.id.input_layout_pincode);
        input_layout_name = (TextInputLayout) findViewById(R.id.input_layout_name);
        input_layout_address = (TextInputLayout) findViewById(R.id.input_layout_address);

        input_layout_landmark = (TextInputLayout) findViewById(R.id.input_layout_landmark);
        input_layout_city = (TextInputLayout) findViewById(R.id.input_layout_city);
        input_layout_state = (TextInputLayout) findViewById(R.id.input_layout_state);
        input_layout_mobile = (TextInputLayout) findViewById(R.id.input_layout_mobile);


        input_pincode = (EditText) findViewById(R.id.input_pincode);
        input_name = (EditText) findViewById(R.id.input_name);
        input_address = (EditText) findViewById(R.id.input_address);

        input_landmark = (EditText) findViewById(R.id.input_landmark);
        input_city = (EditText) findViewById(R.id.input_city);
        input_state = (EditText) findViewById(R.id.input_state);
        input_mobile = (EditText) findViewById(R.id.input_mobile);



        btn_done = (Button) findViewById(R.id.btn_done);

        input_pincode.addTextChangedListener(new MyTextWatcher(input_pincode));
        input_name.addTextChangedListener(new MyTextWatcher(input_name));
        input_address.addTextChangedListener(new MyTextWatcher(input_address));

        input_landmark.addTextChangedListener(new MyTextWatcher(input_landmark));
        input_city.addTextChangedListener(new MyTextWatcher(input_city));
        input_state.addTextChangedListener(new MyTextWatcher(input_state));
        input_mobile.addTextChangedListener(new MyTextWatcher(input_mobile));

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAddress();
            }
        });

    }


    /**
     * Validating form
     */
    private void submitAddress() {
        if (!validateName(input_pincode,input_layout_pincode)) {
            return;
        }

        if (!validateName(input_name,input_layout_name)) {
            return;
        }

        if (!validateName(input_address,input_layout_address)) {
            return;
        }

        if (!validateName(input_city,input_layout_city)) {
            return;
        }

        if (!validateName(input_state,input_layout_state)) {
            return;
        }

        if (!validateName(input_mobile,input_layout_mobile)) {
            return;
        }

        Toast.makeText(getApplicationContext(), "Thank You!", Toast.LENGTH_SHORT).show();
    }

    private boolean validateName(EditText input,TextInputLayout input_layout) {
        if (input.getText().toString().trim().isEmpty()) {
            input_layout.setError("this field can't be blank");
            requestFocus(input);
            return false;
        } else {
            input_layout.setErrorEnabled(false);
        }

        return true;
    }



    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_pincode:
                    validateName(input_pincode,input_layout_pincode);
                    break;
                case R.id.input_name:
                    validateName(input_name,input_layout_name);
                    break;
                case R.id.input_address:
                    validateName(input_address,input_layout_address);
                    break;

                case R.id.input_city:
                    validateName(input_city,input_layout_city);
                    break;
                case R.id.input_state:
                    validateName(input_state,input_layout_state);
                    break;
                case R.id.input_mobile:
                    validateName(input_mobile,input_layout_mobile);
                    break;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Launcher.HomeActivity(AddressActivity.this);
        }
        return true;
    }
}
