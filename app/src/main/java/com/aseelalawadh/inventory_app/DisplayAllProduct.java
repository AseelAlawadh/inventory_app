package com.aseelalawadh.inventory_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class DisplayAllProduct extends AppCompatActivity {

    public ListView listView;
    private InventoryDBHelper mInventoryDBHelper;
    private AddProduct mAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);

        listView = (ListView) findViewById(R.id.listView);
        mInventoryDBHelper = new InventoryDBHelper(getApplicationContext());
        mInventoryDBHelper.open();

        final List<Product> productList = mInventoryDBHelper.getAllProduct();
        mInventoryDBHelper.close();

        String[] productNames = new String[productList.size()];
        for (int i = 0; i < productList.size(); i++) {
            productNames[i] = productList.get(i).getProductName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DisplayAllProduct.this, android.R.layout.simple_list_item_1, android.R.id.text1, productNames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int postion, long l) {
                new AlertDialog.Builder(DisplayAllProduct.this)
                        .setTitle("Select Your option")
                        .setMessage("Delete or Update or show")
                        .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(DisplayAllProduct.this, EditProduct.class);
                                intent.putExtra("id", productList.get(postion).getId());
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mInventoryDBHelper.open();
                                mInventoryDBHelper.deletProduct(productList.get(postion).getId());
                                mInventoryDBHelper.close();
                                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();

                            }
                        })
                        .setNeutralButton("Show", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               /* mInventoryDBHelper.open();
                                mInventoryDBHelper.getAllProduct();
                               mInventoryDBHelper.close();*/
                                Intent intent = new Intent(DisplayAllProduct.this, ProductDetails.class);
                                startActivity(intent);

                            }
                        })
                        .show();
            }
        });

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
                Intent intent = new Intent(DisplayAllProduct.this, AddProduct.class);
                startActivity(intent);

                //displayDatabaseInfo();
                return true;

            case R.id.action_delete_all_entries:
                mInventoryDBHelper.deletProduct(item.getItemId());
               // deleteProduct();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
