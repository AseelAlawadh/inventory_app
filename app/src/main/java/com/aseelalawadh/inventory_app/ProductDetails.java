package com.aseelalawadh.inventory_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductDetails extends AppCompatActivity {

    public ArrayList<Product> pr = new ArrayList<>();
    int mInteger = 0;
    private Product product;
    private TextView productName;
    private TextView productPrice;
    private TextView productQuantity;
    private TextView product_Supplier_name;
    private TextView product_Supplier_phone;
    private InventoryDBHelper mInventoryDBHelper;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


        productName = findViewById(R.id.productName_textView);
        productPrice = findViewById(R.id.productPrice_textView);
        productQuantity = findViewById(R.id.productQuantity_textView);
        product_Supplier_name = findViewById(R.id.supplierName_textView);
        product_Supplier_phone = findViewById(R.id.supplierPhone_textView);


        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        mInventoryDBHelper = new InventoryDBHelper(getApplicationContext());
        mInventoryDBHelper.open();
        product = mInventoryDBHelper.getProduct(id);
        mInventoryDBHelper.close();

        Log.v("", String.valueOf(product.getProductName()));
        Log.v("", String.valueOf(product.getProductPrice()));
        Log.v("", String.valueOf(product.getProductQuantity()));
        Log.v("", String.valueOf(product.getSupplierName()));
        Log.v("", String.valueOf(product.getSupplierPhone()));

        String product_name = product.getProductName();
        productName.setText(product_name);


        String product_price = String.valueOf(product.getProductPrice());
        productPrice.setText(product_price);

        String product_quantity = String.valueOf(product.getProductQuantity());
        productQuantity.setText(product_quantity);


        String supplierName = product.getSupplierName();
        product_Supplier_name.setText(supplierName);

        String product_SupplierPhone = product.getSupplierPhone();
        product_Supplier_phone.setText(product_SupplierPhone);

    }

    public void increaseInteger(View view) {
        mInteger = mInteger + 1;
        display(mInteger);

    }

    public void decreaseInteger(View view) {
        mInteger = mInteger - 1;
        display(mInteger);
    }

    private void display(int number) {
        TextView displayInteger = findViewById(R.id.increase_button);
        displayInteger.setText(String.valueOf(number));
    }
}
