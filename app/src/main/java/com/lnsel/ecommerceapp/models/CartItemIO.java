package com.lnsel.ecommerceapp.models;

public class CartItemIO {
	
	//private variables
	int _id;
	int _product_id;
	String _name;
	String _unit_price;
	
	// Empty constructor
	public CartItemIO(){
		
	}
	// constructor
	public CartItemIO(int id, int _product_id,String name, String _unit_price){
		this._id = id;
		this._product_id=_product_id;
		this._name = name;
		this._unit_price = _unit_price;
	}
	
	// constructor
	public CartItemIO(int _product_id,String name, String _unit_price){
		this._product_id=_product_id;
		this._name = name;
		this._unit_price = _unit_price;
	}
	// getting ID
	public int getID(){
		return this._id;
	}
	
	// setting id
	public void setID(int id){
		this._id = id;
	}

	// getting ID
	public int getProductID(){
		return this._product_id;
	}

	// setting id
	public void setProductID(int _product_id){
		this._product_id = _product_id;
	}
	
	// getting name
	public String getName(){
		return this._name;
	}
	
	// setting name
	public void setName(String name){
		this._name = name;
	}
	
	// getting phone number
	public String getUnitPrice(){
		return this._unit_price;
	}
	
	// setting phone number
	public void setUnitPrice(String _unit_price){
		this._unit_price = _unit_price;
	}
}
