package com.lnsel.ecommerceapp.models;

/**
 * Created by db on 6/16/2017.
 */
public class FilterItem {

    private String items;
    private boolean selected;
    private int id;


    public FilterItem(String items) {

        this.items = items;

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItems() {

        return items;
    }

    public void setItemName(String name) {

        this.items = name;
    }
    public boolean getSelected() {
        return selected;
    }

    public boolean setSelected(Boolean selected) {
        return this.selected = selected;
    }
}
