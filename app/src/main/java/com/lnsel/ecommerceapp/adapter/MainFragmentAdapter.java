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
import com.lnsel.ecommerceapp.activity.ProductActivity;
import com.lnsel.ecommerceapp.activity.ShopItemActivity;
import com.lnsel.ecommerceapp.circularimage.CircularImageView;
import com.lnsel.ecommerceapp.constant.Constant;
import com.lnsel.ecommerceapp.constant.Webservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class MainFragmentAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();
    public MainFragmentAdapter(Context context,
                               ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
    }

    @Override
    public int getCount() {
        return data.size();
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
            itemView = inflater.inflate(R.layout.main_fragment_list_item, null, false);
            holder = new MyViewHolder();
            holder.grid_text=(TextView) itemView.findViewById(R.id.grid_text);
            holder.img_more=(CircularImageView)itemView.findViewById(R.id.grid_image);
            itemView.setTag(holder);
        } else {
            holder = (MyViewHolder) itemView.getTag();
        }
        resultp = data.get(position);

        holder.grid_text.setText(resultp.get("SubCategoryName"));


        String image_url= Webservice.IMAGE_BASE_URL+resultp.get("image");
        System.out.println("Sub-Category-Url "+image_url);
        Picasso.with(context)
                .load(image_url)
                .placeholder(R.drawable.game_of_thrones)
                //.resize(90, 90)
                //.centerCrop()
                .into(holder.img_more);

       /* holder.img_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Position =======================  "+position);
                Constant.SubCategoryID=resultp.get("SubCategoryID");
                Intent intent=new Intent(context, ProductActivity.class);
                context.startActivity(intent);
            }
        });*/

        return itemView;
    }
    private static class MyViewHolder {
        public TextView grid_text;
        public ImageView img_more;
    }


}