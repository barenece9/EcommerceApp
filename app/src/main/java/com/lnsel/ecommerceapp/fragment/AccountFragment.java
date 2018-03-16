package com.lnsel.ecommerceapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.activity.AddressActivity;
import com.lnsel.ecommerceapp.activity.LoginActivity;
import com.lnsel.ecommerceapp.activity.MyOrderActivity;
import com.lnsel.ecommerceapp.activity.ResetPasswordActivity;
import com.lnsel.ecommerceapp.other.Launcher;


public class AccountFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private static final String TAG_COURSES = "allcourses";
    private static final String TAG_FEES = "course_fees";
    private static final String TAG_NAME = "course_name";
    private static final String TAG_DURATION = "course_duration";
    private static final String TAG_DESCRIPTION = "course_description";

    boolean _areLecturesLoaded = false;

    TextView btn_login;
    Button btn_my_address,btn_my_order,btn_reset_password;

    private static View rootView;
    public static AccountFragment newInstance(int pageNo) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNo);
        AccountFragment fragment = new AccountFragment();
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
            rootView = inflater.inflate(R.layout.fragment_account, container, false);
        } catch (InflateException e) {

        }

        btn_login=(TextView)rootView.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.LoginActivity(getActivity());
            }
        });

        btn_my_address=(Button)rootView.findViewById(R.id.btn_my_address);
        btn_my_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.AddressActivity(getActivity());
            }
        });

        btn_my_order=(Button)rootView.findViewById(R.id.btn_my_order);
        btn_my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.OrderActivity(getActivity());
            }
        });

        btn_reset_password=(Button)rootView.findViewById(R.id.btn_reset_password);
        btn_reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Launcher.ResetPasswordActivity(getActivity());
            }
        });

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

}
