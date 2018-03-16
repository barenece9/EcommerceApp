package com.lnsel.ecommerceapp.fragment;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.adapter.CustomArrayAdapter;
import com.lnsel.ecommerceapp.adapter.CustomArrayAdapter2;
import com.lnsel.ecommerceapp.adapter.CustomData;
import com.lnsel.ecommerceapp.adapter.ShopAdapter;
import com.lnsel.ecommerceapp.models.HorizontalListView;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("deprecation")
public class ShopItemFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private static final String TAG_COURSES = "allcourses";
    private static final String TAG_FEES = "course_fees";
    private static final String TAG_NAME = "course_name";
    private static final String TAG_DURATION = "course_duration";
    private static final String TAG_DESCRIPTION = "course_description";

    boolean _areLecturesLoaded = false;

    private static View rootView;

    ImageView imageview;
    AnimationDrawable animation;



    private HorizontalListView mHlvCustomList;

    private String[] mSimpleListValues = new String[] { "26", "28", "30",
            "32", "34", "36", "38", "40",
            "42", "44" };

    private CustomData[] mCustomData = new CustomData[] {
            new CustomData(Color.RED, "Red"),
            new CustomData(Color.DKGRAY, "Dark Gray"),
            new CustomData(Color.GREEN, "Green"),
            new CustomData(Color.LTGRAY, "Light Gray"),
            new CustomData(Color.WHITE, "White"),
            new CustomData(Color.RED, "Red"),
            new CustomData(Color.BLACK, "Black"),
            new CustomData(Color.CYAN, "Cyan"),
            new CustomData(Color.DKGRAY, "Dark Gray"),
            new CustomData(Color.GREEN, "Green"),
            new CustomData(Color.RED, "Red"),
            new CustomData(Color.LTGRAY, "Light Gray"),
            new CustomData(Color.WHITE, "White"),
            new CustomData(Color.BLACK, "Black"),
            new CustomData(Color.CYAN, "Cyan"),
            new CustomData(Color.DKGRAY, "Dark Gray"),
            new CustomData(Color.GREEN, "Green"),
            new CustomData(Color.LTGRAY, "Light Gray"),
            new CustomData(Color.RED, "Red"),
            new CustomData(Color.WHITE, "White"),
            new CustomData(Color.DKGRAY, "Dark Gray"),
            new CustomData(Color.GREEN, "Green"),
            new CustomData(Color.LTGRAY, "Light Gray"),
            new CustomData(Color.WHITE, "White"),
            new CustomData(Color.RED, "Red"),
            new CustomData(Color.BLACK, "Black"),
            new CustomData(Color.CYAN, "Cyan"),
            new CustomData(Color.DKGRAY, "Dark Gray"),
            new CustomData(Color.GREEN, "Green"),
            new CustomData(Color.LTGRAY, "Light Gray"),
            new CustomData(Color.RED, "Red"),
            new CustomData(Color.WHITE, "White"),
            new CustomData(Color.BLACK, "Black"),
            new CustomData(Color.CYAN, "Cyan"),
            new CustomData(Color.DKGRAY, "Dark Gray"),
            new CustomData(Color.GREEN, "Green"),
            new CustomData(Color.LTGRAY, "Light Gray")
    };




    public static ShopItemFragment newInstance(int pageNo) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNo);
        ShopItemFragment fragment = new ShopItemFragment();
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
            rootView = inflater.inflate(R.layout.fragment_shop_item, container, false);
        } catch (InflateException e) {

        }
      //  imageview=(ImageView)rootView.findViewById(R.id.image_view) ;

        animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.women_model), 3000);
        animation.addFrame(getResources().getDrawable(R.drawable.dress_temp), 3000);

        animation.setOneShot(false);

        imageview.setBackgroundDrawable(animation);
        // start the animation!
        animation.start();



        // Get references to UI widgets
        mHlvCustomList = (HorizontalListView) rootView.findViewById(R.id.hlvCustomList);

        setupSimpleList();


        // Note that Gallery view is deprecated in Android 4.1---
       /* Gallery gallery = (Gallery) rootView.findViewById(R.id.gallery1);
        selectedImage=(ImageView)rootView.findViewById(R.id.imageView);
        gallery.setSpacing(1);
        final GalleryImageAdapter galleryImageAdapter= new GalleryImageAdapter(getActivity());
        gallery.setAdapter(galleryImageAdapter);


        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // show the selected Image
                selectedImage.setImageResource(galleryImageAdapter.mImageIds[position]);
            }
        });*/

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


    private void setupCustomLists() {
        // Make an array adapter using the built in android layout to render a list of strings
        CustomArrayAdapter adapter = new CustomArrayAdapter(getActivity(), mCustomData);
        mHlvCustomList.setAdapter(adapter);
        // Assign adapter to HorizontalListView
    }

    private void setupSimpleList() {
        // Make an array adapter using the built in android layout to render a list of strings
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, mSimpleListValues);

        // Assign adapter to the HorizontalListView
        mHlvCustomList.setAdapter(adapter);
    }




    public class GalleryImageAdapter extends BaseAdapter
    {
        private Context mContext;
        public GalleryImageAdapter(Context context)
        {
            mContext = context;
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }


        // Override this method according to your need
        public View getView(int index, View view, ViewGroup viewGroup)
        {
            // TODO Auto-generated method stub
            ImageView i = new ImageView(mContext);

            i.setImageResource(mImageIds[index]);
            i.setLayoutParams(new Gallery.LayoutParams(400, 400));

            i.setScaleType(ImageView.ScaleType.FIT_XY);




            return i;
        }

        //the images to display
        Integer[] mImageIds = {
                R.drawable.images_1,
                R.drawable.images_2,
                R.drawable.image_3,
                R.drawable.images_4,
                R.drawable.image_5,
                R.drawable.images_1,
                R.drawable.images_2
        };

    }

}
