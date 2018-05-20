package com.aseelalawadh.inventory_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddProductActivity extends AppCompatActivity {

    private InventoryDBHelper mInventoryDBHelper;
    private EditText productName;
    private EditText productPrice;
    private EditText productQuantity;
    private EditText supplierName;
    private EditText supplierPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mInventoryDBHelper = new InventoryDBHelper(getApplicationContext());

        productName = findViewById(R.id.productName_EditText);
        productPrice = findViewById(R.id.productPrice_Edittext);
        productQuantity = findViewById(R.id.productQuantity_Edittext);
        supplierName = findViewById(R.id.supplierName_Edittext);
        supplierPhone = findViewById(R.id.supplierPhone_Edittext);

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
                this.saveAction();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveAction() {
        String name = productName.getText().toString();
        String price = productPrice.getText().toString();
        String quantity = productQuantity.getText().toString();
        String supplier_naame = supplierName.getText().toString();
        String supplier_phone = supplierPhone.getText().toString();

        if (!name.isEmpty() && !price.isEmpty() && !quantity.isEmpty() && !supplier_naame.isEmpty() && !supplier_phone.isEmpty()) {
            mInventoryDBHelper.open();
            mInventoryDBHelper.createProduct(name, Integer.parseInt(price), Integer.parseInt(quantity), supplier_naame, supplier_phone);
            mInventoryDBHelper.close();
            Toast.makeText(getApplicationContext(), R.string.add_item_successful, Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), R.string.null_massage, Toast.LENGTH_LONG).show();
        }
    }
}
