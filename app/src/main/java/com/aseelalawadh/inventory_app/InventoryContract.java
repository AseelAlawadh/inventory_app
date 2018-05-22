package com.aseelalawadh.inventory_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.provider.BaseColumns;

public class InventoryContract extends SQLiteOpenHelper {

   public static final String CONTENT_AUTHORITY = "com.course.moritz.items"; //
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);//
    public static final String PATH_ITEMS = "items"; //

    //table name
    public final static String TABLE_NAME = "inventory";
    // Unique ID number for the product
    public final static String _ID = BaseColumns._ID;
    // Product Name, Price, Quantity, Supplier Name, and Supplier Phone Number.
    public final static String COLUMN_INVENTORY_NAME = "name";
    public final static String COLUMN_INVENTORY_PRICE = "price";
    public final static String COLUMN_INVENTORY_QUANTITY = "quantity";
    public final static String COLUMN_INVENTORY_SUPPLIER_NAME = "supplier";
    public final static String COLUMN_INVENTORY_SUPPLIER_PHONE = "supplier_phone";
    private static final String DB_NAME = "MySqlDatabase";
    private static final int VERSION_NUMBER = 1;
    private static final String SQL_CREATE_INVENTORY_TABLE = "create table " + TABLE_NAME + " " +
            "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_INVENTORY_NAME + " text not null,"
            + COLUMN_INVENTORY_PRICE + " integer not null,"
            + COLUMN_INVENTORY_QUANTITY + " integer not null,"
            + COLUMN_INVENTORY_SUPPLIER_NAME + " text not null,"
            + COLUMN_INVENTORY_SUPPLIER_PHONE + " text not null)";

    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ITEMS); //


    public InventoryContract(Context context) {
        super(context, DB_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_INVENTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }
}