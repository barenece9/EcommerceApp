package com.lnsel.ecommerceapp.models;

/**
 * Created by bpncool on 2/23/2016.
 */
public class Item {

    private final int id;
    private final String name;
    private final String image;
    private final String SubCategoryID;

    public String getImage() {
        return image;
    }

    public Item(int id ,String SubCategoryID,String name,String image) {
        this.image=image;
        this.name = name;
        this.id = id;
        this.SubCategoryID=SubCategoryID;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubCategoryID() {
        return SubCategoryID;
    }
}
