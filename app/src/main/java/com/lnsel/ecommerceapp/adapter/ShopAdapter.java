package com.lnsel.ecommerceapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.activity.ShopItemActivity;
import com.lnsel.ecommerceapp.fragment.ShopItemFragment;

import java.util.ArrayList;
import java.util.HashMap;

public class ShopAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();
    public ShopAdapter(Context context,
                              ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
    }

    @Override
    public int getCount() {
        return 12;
       // return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        View itemView = convertView;
        final MyViewHolder holder;
        if (itemView == null) {
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.shop_list_item, null, false);
            holder = new MyViewHolder();
            holder.grid_text=(TextView) itemView.findViewById(R.id.grid_text);

           // holder.btn_action=(Button)itemView.findViewById(R.id.btn_action);
            holder.img_more=(ImageView)itemView.findViewById(R.id.grid_image);



            itemView.setTag(holder);
        } else {
            holder = (MyViewHolder) itemView.getTag();
        }
       // resultp = data.get(position);

       // holder.emp_name.setText(""+resultp.get("employee_name"));


        holder.img_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Position =======================  "+position);
                Intent intent=new Intent(context, ShopItemActivity.class);
                context.startActivity(intent);
            }
        });

        return itemView;
    }
    private static class MyViewHolder {
        public TextView grid_text;
        public Button btn_action;
        public ImageView img_more;
    }


}