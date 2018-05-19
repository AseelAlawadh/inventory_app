package com.aseelalawadh.inventory_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class InventoryDBHelper {
    InventoryContract mInventoryContract;
    SQLiteDatabase db;
    String[] allColumes=new String[]{mInventoryContract._ID,mInventoryContract.COLUMN_INVENTORY_NAME,
            mInventoryContract.COLUMN_INVENTORY_PRICE,mInventoryContract.COLUMN_INVENTORY_QUANTITY,
            mInventoryContract.COLUMN_INVENTORY_SUPPLIER_NAME , mInventoryContract.COLUMN_INVENTORY_SUPPLIER_PHONE};

    public InventoryDBHelper(Context context){
        mInventoryContract=new InventoryContract(context);

    }

    public void open(){
        try {
            db=mInventoryContract.getWritableDatabase();
        }catch (Exception e){
            Log.d("ERROR",e.getMessage());
        }
    }

    public void close(){
        db.close();
    }


    public void createProduct(String productName,int price,int quantity,String supplierName , String supplierPhone){
        ContentValues list=new ContentValues();
        list.put(InventoryContract.COLUMN_INVENTORY_NAME,productName);
        list.put(InventoryContract.COLUMN_INVENTORY_PRICE,price);
        list.put(InventoryContract.COLUMN_INVENTORY_QUANTITY,quantity);
        list.put(InventoryContract.COLUMN_INVENTORY_SUPPLIER_NAME,supplierName);
        list.put(InventoryContract.COLUMN_INVENTORY_SUPPLIER_PHONE,supplierPhone);

        db.insert(InventoryContract.TABLE_NAME,null,list);

    }

    public Product getProduct(int id){
        Product products = new Product();
        Cursor cursor=db.rawQuery("SELECT * FROM "+InventoryContract.TABLE_NAME+" WHERE "+InventoryContract._ID+" = ?",new String[]{id+""});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            products.setId(cursor.getInt(0));
            products.setProductName(cursor.getString(1));
            products.setProductPrice(cursor.getInt(2));
            products.setProductQuantity(cursor.getInt(3));
            products.setSupplierName(cursor.getString(4));
            products.setSupplierPhone(cursor.getString(5));
            cursor.close();
        }

        return products;
    }
    public void updateProduct(int id,String productName,int price,int quantity){
        ContentValues list=new ContentValues();
        list.put(InventoryContract.COLUMN_INVENTORY_NAME,productName);
        list.put(InventoryContract.COLUMN_INVENTORY_PRICE,price);
        list.put(InventoryContract.COLUMN_INVENTORY_QUANTITY,quantity);

        db.update(InventoryContract.TABLE_NAME,list,InventoryContract._ID+" = "+id,null);

    }
   /* public void updateProduct(int id,String productName,int price,int quantity,String supplierName , String supplierPhone){
        ContentValues list=new ContentValues();
        list.put(InventoryContract.COLUMN_INVENTORY_NAME,productName);
        list.put(InventoryContract.COLUMN_INVENTORY_PRICE,price);
        list.put(InventoryContract.COLUMN_INVENTORY_QUANTITY,quantity);
        list.put(InventoryContract.COLUMN_INVENTORY_SUPPLIER_NAME,supplierName);
        list.put(InventoryContract.COLUMN_INVENTORY_SUPPLIER_PHONE,supplierPhone);

        db.update(InventoryContract.TABLE_NAME,list,InventoryContract._ID+" = "+id,null);

    }*/

    public boolean deletProduct(int id){
        int isDeleted = db.delete(InventoryContract.TABLE_NAME,InventoryContract._ID+"= "+id,null);
        Log.v("Value of isDeleted", String.valueOf(isDeleted));
        if (isDeleted == 1) {
            return true;
        }
        return false;
    }

    public List<Product> getAllProduct(){
        List<Product> productList=new ArrayList<Product>();

        Cursor cursor=db.query(InventoryContract.TABLE_NAME,allColumes,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Product products=new Product();
            products.setId(cursor.getInt(0));
            products.setProductName(cursor.getString(1));
            products.setProductPrice(cursor.getInt(2));
            products.setProductQuantity(cursor.getInt(3));
            products.setSupplierName(cursor.getString(4));
            products.setSupplierPhone(cursor.getString(5));

            productList.add(products);
            cursor.moveToNext();



        }
        cursor.close();
        return productList;

    }

}
