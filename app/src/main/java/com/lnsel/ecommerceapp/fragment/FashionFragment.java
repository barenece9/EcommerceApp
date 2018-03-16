package com.lnsel.ecommerceapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lnsel.ecommerceapp.R;
import com.lnsel.ecommerceapp.activity.ShopItemActivity;
import com.lnsel.ecommerceapp.adapter.ItemClickListener;
import com.lnsel.ecommerceapp.adapter.Section;
import com.lnsel.ecommerceapp.adapter.SectionedExpandableLayoutHelper;
import com.lnsel.ecommerceapp.models.Item;

import java.util.ArrayList;


public class FashionFragment extends Fragment implements ItemClickListener {
    public static final String ARG_PAGE = "ARG_PAGE";
    private static final String TAG_COURSES = "allcourses";
    private static final String TAG_FEES = "course_fees";
    private static final String TAG_NAME = "course_name";
    private static final String TAG_DURATION = "course_duration";
    private static final String TAG_DESCRIPTION = "course_description";

    boolean _areLecturesLoaded = false;

    RecyclerView mRecyclerView;
    private static View rootView;
    public static FashionFragment newInstance(int pageNo) {

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNo);
        FashionFragment fragment = new FashionFragment();
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
            rootView = inflater.inflate(R.layout.fragment_woman, container, false);
        } catch (InflateException e) {

        }


        //setting the recycler view
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        SectionedExpandableLayoutHelper sectionedExpandableLayoutHelper = new SectionedExpandableLayoutHelper(getActivity(),
                mRecyclerView, this, 3);

        //random data
       /* ArrayList<Item> arrayList = new ArrayList<>();
        arrayList.add(new Item("Clothing", 0));
        arrayList.add(new Item("Footwear", 1));
        arrayList.add(new Item("Watches", 2));
        arrayList.add(new Item("Accessories", 3));
        sectionedExpandableLayoutHelper.addSection("Men's Fashion", arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Item("Ethnic Wear", 0));
        arrayList.add(new Item("Western Wear", 1));
        arrayList.add(new Item("Footwear", 2));
        arrayList.add(new Item("Watches", 3));
        sectionedExpandableLayoutHelper.addSection("Women's Fashion", arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Item("Kid's Clothing", 0));
        arrayList.add(new Item("Toys & Games", 1));
        arrayList.add(new Item("Footwear", 2));
        arrayList.add(new Item("Watches", 3));
        sectionedExpandableLayoutHelper.addSection("Baby & Kids", arrayList);


        arrayList = new ArrayList<>();
        arrayList.add(new Item("Necklaces Sets", 0));
        arrayList.add(new Item("Earrings", 1));
        arrayList.add(new Item("Bangles", 2));
        arrayList.add(new Item("Bracelets", 3));
        sectionedExpandableLayoutHelper.addSection("Jewellery", arrayList);*/

        sectionedExpandableLayoutHelper.notifyDataSetChanged();

        //checking if adding single item works
       // sectionedExpandableLayoutHelper.addItem("Jewellery", new Item("Trousers",5));
        sectionedExpandableLayoutHelper.notifyDataSetChanged();

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


    @Override
    public void itemClicked(Item item) {
       // Toast.makeText(getActivity(), "Item: " + item.getName() + " clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), ShopItemActivity.class);
        startActivity(intent);
    }

    @Override
    public void itemClicked(Section section) {

        Toast.makeText(getActivity(), "Section: " + section.getName() + " clicked", Toast.LENGTH_SHORT).show();

    }

}
