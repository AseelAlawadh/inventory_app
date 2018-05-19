package com.aseelalawadh.inventory_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProduct extends AppCompatActivity {

    Button add;
    InventoryDBHelper mInventoryDBHelper;
    private EditText productName;
    private EditText productPrice;
    private EditText productQuantity;
    private EditText supplierName;
    private EditText supplierPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        productName = findViewById(R.id.productName_EditText);
        productPrice = findViewById(R.id.productPrice_Edittext);
        productQuantity = findViewById(R.id.productQuantity_Edittext);
        supplierName = findViewById(R.id.supplierName_Edittext);
        supplierPhone = findViewById(R.id.supplierPhone_Edittext);

        add = (Button) findViewById(R.id.addProduct_BT);
        mInventoryDBHelper = new InventoryDBHelper(getApplicationContext());


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!productName.getText().toString().isEmpty() && !productPrice.getText().toString().isEmpty() && !productQuantity.getText().toString().isEmpty()
                        && !supplierName.getText().toString().isEmpty() && !supplierPhone.getText().toString().isEmpty()) {
                    mInventoryDBHelper.open();
                    mInventoryDBHelper.createProduct(productName.getText().toString(), Integer.parseInt(productPrice.getText().toString()),
                            Integer.parseInt(productQuantity.getText().toString()), supplierName.getText().toString(),
                            supplierPhone.getText().toString());
                    mInventoryDBHelper.close();
                    Toast.makeText(getApplicationContext(), "Product is added ", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Filed cannot be empty!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
