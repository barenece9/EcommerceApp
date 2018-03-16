package com.lnsel.ecommerceapp.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.activity.SearchActivity;
import com.lnsel.ecommerceapp.adapter.CustomArrayAdapter;
import com.lnsel.ecommerceapp.adapter.CustomArrayAdapter2;
import com.lnsel.ecommerceapp.adapter.CustomData;
import com.lnsel.ecommerceapp.imageslider.BaseSliderView;
import com.lnsel.ecommerceapp.imageslider.DescriptionAnimation;
import com.lnsel.ecommerceapp.imageslider.SliderLayout;
import com.lnsel.ecommerceapp.imageslider.TextSliderView;
import com.lnsel.ecommerceapp.imageslider.ViewPagerEx;
import com.lnsel.ecommerceapp.models.HorizontalListView;

import java.util.HashMap;

@SuppressWarnings("deprecation")
public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    public static final String ARG_PAGE = "ARG_PAGE";
    private static final String TAG_COURSES = "allcourses";
    private static final String TAG_FEES = "course_fees";
    private static final String TAG_NAME = "course_name";
    private static final String TAG_DURATION = "course_duration";
    private static final String TAG_DESCRIPTION = "course_description";

    boolean _areLecturesLoaded = false;

    private static View rootView;

    EditText etn_search;
    ImageView selectedImage;

    private SliderLayout mDemoSlider;


    private HorizontalListView mHlvCustomList;
    private HorizontalListView mHlvCustomListWithDividerAndFadingEdge;

    private String[] mSimpleListValues = new String[] { "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2" };

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
            new CustomData(Color.LTGRAY, "Light Gray")
    };


    public static HomeFragment newInstance(int pageNo) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNo);
        HomeFragment fragment = new HomeFragment();
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
            rootView = inflater.inflate(R.layout.fragment_home, container, false);
        } catch (InflateException e) {

        }

        etn_search=(EditText)rootView.findViewById(R.id.etn_search);
        etn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });

        // Get references to UI widgets
        mHlvCustomList = (HorizontalListView) rootView.findViewById(R.id.hlvCustomList);
        mHlvCustomListWithDividerAndFadingEdge = (HorizontalListView) rootView.findViewById(R.id.hlvCustomListWithDividerAndFadingEdge);

        mHlvCustomListWithDividerAndFadingEdge.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"Click",Toast.LENGTH_SHORT).show();
            }
        });
        setupCustomLists();




        // Note that Gallery view is deprecated in Android 4.1---
        Gallery gallery = (Gallery) rootView.findViewById(R.id.gallery1);
        selectedImage=(ImageView)rootView.findViewById(R.id.imageView);
        gallery.setSpacing(1);
        final GalleryImageAdapter galleryImageAdapter= new GalleryImageAdapter(getActivity());
        gallery.setAdapter(galleryImageAdapter);


        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // show the selected Image
                selectedImage.setImageResource(galleryImageAdapter.mImageIds[position]);
            }
        });



        // image slider..............

        mDemoSlider = (SliderLayout)rootView.findViewById(R.id.slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR96ZlbGvUi4VZYxs9x6K19mItXmdb-z52NV2J3TjHSpeW8yD4P");
        url_maps.put("Big Bang Theory", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQY5j37p6AyI_1idMlZlIwl2ApBvSmaFglxcLHazfTIVKSZkjEIBg");
        url_maps.put("House of Cards", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVKppUTm3kpL8otLm04bnfiYBAbJa-EcjhixiHF_m50ih-U-q8");
        url_maps.put("Game of Thrones", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfLb6PN98LGsIXgFHduDZMjQIl5SrMpYhsYxeZcC0Eo28bMTDE");

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
       /* file_maps.put("Hannibal",R.drawable.hannibal);
        file_maps.put("Big Bang Theory",R.drawable.bigbang);
        file_maps.put("House of Cards",R.drawable.house);
        file_maps.put("Game of Thrones", R.drawable.game_of_thrones);*/
        file_maps.put("Hannibal",R.drawable.product1);
        file_maps.put("Big Bang Theory",R.drawable.product2);
        file_maps.put("House of Cards",R.drawable.product3);
        file_maps.put("Game of Thrones", R.drawable.product4);

        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(5000);
        mDemoSlider.addOnPageChangeListener(this);

        set_programmatically_layout();

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
        CustomArrayAdapter adapter = new CustomArrayAdapter(getActivity(), mCustomData);
        mHlvCustomList.setAdapter(adapter);

        CustomArrayAdapter2 adapter2 = new CustomArrayAdapter2(getActivity(), mCustomData);
        mHlvCustomListWithDividerAndFadingEdge.setAdapter(adapter2);


    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getActivity(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}




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

    public  void set_programmatically_layout(){

        LinearLayout ll_parent_programmatically = (LinearLayout) rootView.findViewById(R.id.ll_parent_programmatically);

        for (int j=0;j<=2;j++) {

            LinearLayout ll_parent_parent = new LinearLayout(getActivity());
            ll_parent_parent.setId(0);
            ll_parent_parent.setOrientation(LinearLayout.VERTICAL);
            ll_parent_parent.setBackgroundColor(Color.parseColor("#ffffff"));
            LinearLayout.LayoutParams ll_parms_parent = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ll_parms_parent.gravity = Gravity.CENTER_VERTICAL;
            ll_parms_parent.setMargins(10, 10, 10, 10);
            ll_parent_parent.setLayoutParams(ll_parms_parent);


            TextView tv_title = new TextView(getActivity());
            tv_title.setText("Related to items you've viewed "+j);
            tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            tv_title.setTypeface(Typeface.DEFAULT_BOLD);
            tv_title.setTextColor(Color.parseColor("#000000"));
            LinearLayout.LayoutParams tv_title_parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 45);
            tv_title_parms.gravity = Gravity.CENTER_VERTICAL;
            tv_title_parms.setMargins(15,20,10,10);
            tv_title.setLayoutParams(tv_title_parms);
            ll_parent_parent.addView(tv_title);


            ImageView divider1 = new ImageView(getActivity());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
            lp.setMargins(10, 5, 10, 10);
            divider1.setLayoutParams(lp);
            divider1.setBackgroundColor(Color.parseColor("#cccccc"));
            ll_parent_parent.addView(divider1);


            for (int i = 0; i <= 2; i++) {

                    LinearLayout ll_parent_child = new LinearLayout(getActivity());
                    ll_parent_child.setId(i);
                    ll_parent_child.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams ll_parms_child = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    ll_parms_child.gravity = Gravity.CENTER_VERTICAL;
                    ll_parms_child.setMargins(0, 12, 0, 0);
                    ll_parent_child.setLayoutParams(ll_parms_child);
                    ll_parent_child.setOnClickListener(getOnClickDoSomething(ll_parent_child,j,i));

                        ImageView imageView = new ImageView(getActivity());
                        imageView.setId(i);
                        imageView.setBackgroundResource(R.drawable.women_model);
                        LinearLayout.LayoutParams image_parms = new LinearLayout.LayoutParams(120, 120);
                        image_parms.gravity = Gravity.CENTER_VERTICAL;
                        imageView.setLayoutParams(image_parms);
                        ll_parent_child.addView(imageView);


                        LinearLayout ll_parent = new LinearLayout(getActivity());
                        ll_parent.setId(i);
                        ll_parent.setOrientation(LinearLayout.VERTICAL);
                        LinearLayout.LayoutParams ll_parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 150);
                        ll_parms.gravity = Gravity.CENTER_VERTICAL;
                        ll_parent.setLayoutParams(ll_parms);
                        ll_parent_child.addView(ll_parent);

                            TextView tv_product_name = new TextView(getActivity());
                            tv_product_name.setText("Benetton Beige Casuals Slim Fit Shirt, I don't know if I understand you correctly "+i);
                            tv_product_name.setTextColor(Color.parseColor("#795548"));
                            tv_product_name.setTypeface(Typeface.DEFAULT_BOLD);
                            tv_product_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
                            ll_parent.addView(tv_product_name);


                            TextView tv_price = new TextView(getActivity());
                            tv_price.setText("Unit Price : Rs. 1,000");
                            tv_price.setTextColor(Color.parseColor("#898C88"));
                            tv_price.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                            ll_parent.addView(tv_price);

                            TextView tv_payable_amount = new TextView(getActivity());
                            tv_payable_amount.setText("Payable Amount : Rs. 1,292");
                            tv_payable_amount.setTextColor(Color.parseColor("#f85534"));
                            tv_payable_amount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                            ll_parent.addView(tv_payable_amount);

                    ll_parent_parent.addView(ll_parent_child);
            }


            ImageView divider2 = new ImageView(getActivity());
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
            lp2.setMargins(10, 10, 10, 5);
            divider2.setLayoutParams(lp2);
            divider2.setBackgroundColor(Color.parseColor("#cccccc"));
            ll_parent_parent.addView(divider2);


            TextView tv_see_more = new TextView(getActivity());
            tv_see_more.setText("See More >>");
            tv_see_more.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
            tv_see_more.setTypeface(Typeface.DEFAULT_BOLD);
            tv_see_more.setTextColor(Color.parseColor("#EE6F17"));
            LinearLayout.LayoutParams tv_more_parms = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 40);
            tv_more_parms.gravity = Gravity.CENTER_VERTICAL;
            tv_more_parms.setMargins(15,5,10,10);
            tv_see_more.setLayoutParams(tv_more_parms);
            ll_parent_parent.addView(tv_see_more);
            tv_see_more.setOnClickListener(getOnClickDoSomething(tv_see_more,j));

            ll_parent_programmatically.addView(ll_parent_parent);
        }

    }

    View.OnClickListener getOnClickDoSomething(final LinearLayout linearLayout, final int group_id, final int child_id)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Group :"+group_id+" "+"Child : "+child_id,Toast.LENGTH_SHORT).show();
            }
        };
    }

    View.OnClickListener getOnClickDoSomething(final TextView textView, final int group_id)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Group :"+group_id,Toast.LENGTH_SHORT).show();
            }
        };
    }

}
