package com.aseelalawadh.inventory_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditProductActivity extends AppCompatActivity {

    /*private EditText supplierName;
    private EditText supplierPhone;*/
    Button add;
    private int id;
    private EditText productName;
    private EditText productPrice;
    private EditText productQuantity;
    private int mInteger = 0;
    private Button increase_button;
    private Button decrease_button;
    private InventoryDBHelper mInventoryDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        productName = findViewById(R.id.productName_EditText);
        productPrice = findViewById(R.id.productPrice_Edittext);
        productQuantity = findViewById(R.id.productQuantity_Edittext);
        increase_button = findViewById(R.id.increase_button);
        decrease_button = findViewById(R.id.decrease_button);

        mInventoryDBHelper = new InventoryDBHelper(getApplicationContext());
        mInventoryDBHelper.open();
        Product products = mInventoryDBHelper.getProduct(id);
        mInventoryDBHelper.close();

        productName.setText(products.getProductName());
        productPrice.setText(String.valueOf(products.getProductPrice()));
        productQuantity.setText(String.valueOf(products.getProductQuantity()));
        mInteger = products.getProductQuantity();
        increase_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseInteger(productQuantity);
            }
        });
        decrease_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decreaseInteger(productQuantity);
            }
        });
    }

    public void increaseInteger(View view) {
        mInteger = mInteger + 1;
        display(mInteger);
    }

    public void decreaseInteger(View view) {
        if ((mInteger - 1) < 0) {
            return;
        }
        mInteger = mInteger - 1;
        display(mInteger);
    }

    private void display(int number) {
        TextView displayInteger = findViewById(R.id.productQuantity_Edittext);
        displayInteger.setText(String.valueOf(number));
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
        switch (id) {
            case R.id.action_save:
                String name = productName.getText().toString();
                String price = productPrice.getText().toString();
                String quantity = productQuantity.getText().toString();
                if (!name.isEmpty() && !price.isEmpty() && !quantity.isEmpty()) {
                    mInventoryDBHelper.open();
                    mInventoryDBHelper.updateProduct(id, name, Integer.parseInt(price), Integer.parseInt(quantity));
                    mInventoryDBHelper.close();
                    Toast.makeText(getApplicationContext(), R.string.updated, Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), R.string.null_massage, Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
