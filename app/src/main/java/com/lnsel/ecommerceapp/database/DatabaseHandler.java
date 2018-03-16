package com.lnsel.ecommerceapp.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lnsel.ecommerceapp.models.CartItemIO;
import com.lnsel.ecommerceapp.models.WishItemIO;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "localManager";

	// table name
	private static final String TABLE_CART_LIST = "cart_list";
	private static final String TABLE_WISH_LIST = "wish_list";

	// cart Table Columns names
	private static final String KEY_ID = "id";
	private static final String PRODUCT_ID = "product_id";
	private static final String KEY_NAME = "name";
	private static final String KEY_PRICE = "price";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART_LIST + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"+PRODUCT_ID+ " INTEGER," + KEY_NAME + " TEXT,"
				+ KEY_PRICE + " TEXT" + ")";
		db.execSQL(CREATE_CART_TABLE);

		String CREATE_WISH_LIST_TABLE = "CREATE TABLE " + TABLE_WISH_LIST + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"+PRODUCT_ID+ " INTEGER," + KEY_NAME + " TEXT,"
				+ KEY_PRICE + " TEXT" + ")";
		db.execSQL(CREATE_WISH_LIST_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART_LIST);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WISH_LIST);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new item to cart
	public void addCart(CartItemIO contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(PRODUCT_ID,contact.getProductID());
		values.put(KEY_NAME, contact.getName()); // Contact Name
		values.put(KEY_PRICE, contact.getUnitPrice()); // Contact Phone

		// Inserting Row
		db.insert(TABLE_CART_LIST, null, values);
		db.close(); // Closing database connection
	}

	// Adding new item to wish
	public void addWish(WishItemIO contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(PRODUCT_ID,contact.getProductID());
		values.put(KEY_NAME, contact.getName()); // Contact Name
		values.put(KEY_PRICE, contact.getUnitPrice()); // Contact Phone

		// Inserting Row
		db.insert(TABLE_WISH_LIST, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact
	CartItemIO getCart(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CART_LIST, new String[] { KEY_ID,
				KEY_NAME, KEY_PRICE }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		CartItemIO contact = new CartItemIO(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2));
		// return contact
		return contact;
	}
	
	// Getting All Contacts
	public List<CartItemIO> getAllCart() {
		List<CartItemIO> contactList = new ArrayList<CartItemIO>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CART_LIST;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				CartItemIO contact = new CartItemIO();
				contact.setID(Integer.parseInt(cursor.getString(0)));
				contact.setProductID(cursor.getInt(1));
				contact.setName(cursor.getString(2));
				contact.setUnitPrice(cursor.getString(3));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}

		// return contact list
		return contactList;
	}

	// Updating single contact
	public int updateCart(CartItemIO contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.getName());
		values.put(KEY_PRICE, contact.getUnitPrice());

		// updating row
		return db.update(TABLE_CART_LIST, values, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
	}

	// Deleting single contact
	public void deleteCart(CartItemIO contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CART_LIST, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
		db.close();
	}


	// Getting Cart Count
	public int getCartCount() {
		String countQuery = "SELECT  * FROM " + TABLE_CART_LIST;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		//cursor.close();
		// return count
		return cursor.getCount();
	}

	// Getting Wish Count
	public int getWishCount() {
		String countQuery = "SELECT  * FROM " + TABLE_WISH_LIST;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		//cursor.close();
		// return count
		return cursor.getCount();
	}

}
