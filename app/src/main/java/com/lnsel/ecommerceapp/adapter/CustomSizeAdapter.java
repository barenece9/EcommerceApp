package com.lnsel.ecommerceapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.other.ClickEffect;

/** An array adapter that knows how to render views when given CustomData classes */
public class CustomSizeAdapter extends ArrayAdapter<CustomData> {
    private LayoutInflater mInflater;

    public CustomSizeAdapter(Context context, CustomData[] values) {
        super(context, R.layout.custom_size_view, values);
        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if (convertView == null) {
            // Inflate the view since it does not exist
            convertView = mInflater.inflate(R.layout.custom_size_view, parent, false);

            // Create and save off the holder in the tag so we get quick access to inner fields
            // This must be done for performance reasons
            holder = new Holder();
            holder.btn_size = (Button) convertView.findViewById(R.id.btn_size);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        // Populate the text
        holder.btn_size.setText(getItem(position).getText());
       /* holder.btn_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ClickEffect.addClickEffect(holder.btn_size);
            }
        });*/

        // Set the color
       // convertView.setBackgroundColor(getItem(position).getBackgroundColor());

        return convertView;
    }

    /** View holder for the views we need access to */
    private static class Holder {
        public Button btn_size;
    }
}
