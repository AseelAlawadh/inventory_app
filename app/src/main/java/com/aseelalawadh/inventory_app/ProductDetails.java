package com.aseelalawadh.inventory_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProductDetails extends AppCompatActivity {

    private int id;
    private TextView productName ;
    private TextView productPrice;
    private TextView productQuantity;
    private InventoryDBHelper mInventoryDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        Intent intent=getIntent();
        id=intent.getIntExtra("id", 0);

        //mInventoryDBHelper.getProduct(id);
        productName = findViewById(R.id.productName_textView);
        productPrice = findViewById(R.id.productPrice_textView);
        productQuantity = findViewById(R.id.productQuantity_textView);


      //  add=(Button)findViewById(R.id.addBT);
        mInventoryDBHelper =new InventoryDBHelper(getApplicationContext());

        mInventoryDBHelper.open();

        Product products = mInventoryDBHelper.getProduct(id);
        mInventoryDBHelper.close();

      productName.setText(products.getProductName());
        Log.v("Show Detaiils", String.valueOf(products.getProductPrice()));
        productPrice.setText(String.valueOf(products.getProductPrice()));
        productQuantity.setText(String.valueOf(products.getProductQuantity()));

    }

}
