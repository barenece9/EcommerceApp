package com.lnsel.ecommerceapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;

import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.other.ClickEffect;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckoutItemAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();
    public CheckoutItemAdapter(Context context,
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
            itemView = inflater.inflate(R.layout.checkout_list_item, null, false);
            holder = new MyViewHolder();
            holder.img_btn_delete=(ImageButton)itemView.findViewById(R.id.img_btn_delete);



            itemView.setTag(holder);
        } else {
            holder = (MyViewHolder) itemView.getTag();
        }
       // resultp = data.get(position);
       // holder.emp_name.setText(""+resultp.get("employee_name"));

        holder.img_btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickEffect.addClickEffect(holder.img_btn_delete);
            }
        });

        return itemView;
    }
    private static class MyViewHolder {
       // public TextView grid_text;
       // public ImageView img_more;
       public ImageButton img_btn_delete;
    }


}