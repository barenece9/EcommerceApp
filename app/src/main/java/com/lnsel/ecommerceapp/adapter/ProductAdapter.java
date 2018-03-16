package com.lnsel.ecommerceapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.activity.ProductActivity;
import com.lnsel.ecommerceapp.activity.ShopItemActivity;
import com.lnsel.ecommerceapp.appconroller.AppController;
import com.lnsel.ecommerceapp.circularimage.CircularImageView;
import com.lnsel.ecommerceapp.constant.Constant;
import com.lnsel.ecommerceapp.constant.Webservice;
import com.lnsel.ecommerceapp.database.DatabaseHandler;
import com.lnsel.ecommerceapp.models.WishItemIO;
import com.lnsel.ecommerceapp.other.ClickEffect;
import com.lnsel.ecommerceapp.other.Method;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    public ProductAdapter(Context context,
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
            itemView = inflater.inflate(R.layout.product_grid_item, null, false);
            holder = new MyViewHolder();
            holder.ProductName=(TextView) itemView.findViewById(R.id.ProductName);
            holder.ProductSalesPrice=(TextView) itemView.findViewById(R.id.ProductSalesPrice);
            holder.ProductPrice=(TextView) itemView.findViewById(R.id.ProductPrice);
            holder.btn_wish=(ImageButton) itemView.findViewById(R.id.btn_wish);
            holder.ProductImage=(NetworkImageView) itemView.findViewById(R.id.ProductImage);
            itemView.setTag(holder);
        } else {
            holder = (MyViewHolder) itemView.getTag();
        }
        resultp = data.get(position);

        holder.ProductName.setText(resultp.get("ProductName"));

        holder.ProductSalesPrice.setText("Rs. "+resultp.get("ProductSalesPrice"));
        holder.ProductPrice.setText("Rs. "+resultp.get("ProductPrice"));

        String image_url= Webservice.IMAGE_BASE_URL+resultp.get("ProductImage");
        System.out.println("Sub-Category-Url "+image_url);
       /* Picasso.with(context)
                .load(image_url)
                //.resize(90, 90)
                .placeholder(R.drawable.game_of_thrones)
                .into(holder.ProductImage);*/

        if(!data.get(position).get("ProductImage").equalsIgnoreCase("")) {
            holder.ProductImage.setImageUrl(Webservice.IMAGE_BASE_URL+data.get(position).get("ProductImage"), imageLoader);
        }else {
            holder.ProductImage.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfLb6PN98LGsIXgFHduDZMjQIl5SrMpYhsYxeZcC0Eo28bMTDE", imageLoader);
        }

        holder.ProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Position =======================  "+position);
                Constant.ProductID=data.get(position).get("ProductID");
                Intent intent=new Intent(context, ShopItemActivity.class);
                context.startActivity(intent);
            }
        });

        holder.btn_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context instanceof ProductActivity){
                    ((ProductActivity)context).addWishList(position);
                    ClickEffect.addClickEffect(holder.btn_wish);
                }
            }
        });

        return itemView;
    }
    private static class MyViewHolder {
        public TextView ProductName,ProductSalesPrice,ProductPrice;
        public ImageButton btn_wish;
        public NetworkImageView ProductImage;
    }


}