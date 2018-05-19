package com.aseelalawadh.inventory_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListProductsActivity extends AppCompatActivity {

    public ListView listView;
    private InventoryDBHelper mInventoryDBHelper;
    private ProductAdapter adapter;
    private ArrayList<Product> productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListProductsActivity.this, AddProductActivity.class);
                startActivity(intent);
            }
        });


        listView = findViewById(R.id.listView);

        mInventoryDBHelper = new InventoryDBHelper(getApplicationContext());
        mInventoryDBHelper.open();
        productList = (ArrayList<Product>) mInventoryDBHelper.getAllProduct();
        mInventoryDBHelper.close();
        adapter = new ProductAdapter(this, productList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int postion, long l) {
                Product item = productList.get(postion);
                new AlertDialog.Builder(ListProductsActivity.this)
                        .setTitle("Select Your option")
                        .setMessage("Delete or Update or Show")
                        .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(ListProductsActivity.this, EditProductActivity.class);
                                Product item = productList.get(postion);
                                intent.putExtra("id", item.getId());
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Product item = productList.get(postion);
                                mInventoryDBHelper.open();
                                mInventoryDBHelper.deletProduct(item.getId());
                                mInventoryDBHelper.close();
                                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNeutralButton("Show", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(ListProductsActivity.this, ProductDetails.class);
                                Product item = productList.get(postion);
                                Log.v("", item.getProductName());
                                intent.putExtra("id", item.getId());

                                startActivity(intent);

                            }
                        }).show();
            }
        });

        ListView product_ListView = (ListView) findViewById(R.id.listView);
        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        View emptyView = findViewById(R.id.empty_view);
        product_ListView.setEmptyView(emptyView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {

            case R.id.action_insert_data:
                // insertProduct();
                Intent intent = new Intent(ListProductsActivity.this, AddProductActivity.class);
                startActivity(intent);
                finish();

                //displayDatabaseInfo();
                return true;

            case R.id.action_delete_all_entries:
                mInventoryDBHelper.deletProduct(item.getItemId());
                // deleteProduct();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mInventoryDBHelper.open();
        productList = (ArrayList<Product>) mInventoryDBHelper.getAllProduct();
        adapter.clear();
        adapter.addAll(productList);
        adapter.notifyDataSetChanged();
        mInventoryDBHelper.close();

    }
}
