package com.aseelalawadh.inventory_app;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductDetailsActivity extends AppCompatActivity {

    public ArrayList<Product> pr = new ArrayList<>();
    private int mInteger = 0;
    private Product product;
    private TextView productName;
    private TextView productPrice;
    private TextView productQuantity;
    private TextView product_Supplier_name;
    private TextView product_Supplier_phone;
    private Button increase_button;
    private Button decrease_button;
    private Button orderProduct;
    private Button deleteProduct;
    private InventoryDBHelper mInventoryDBHelper;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


        productName = findViewById(R.id.productName_textView);
        productPrice = findViewById(R.id.productPrice_textView);
        productQuantity = findViewById(R.id.productQuantity_textView);
        increase_button = findViewById(R.id.increase_button);
        decrease_button = findViewById(R.id.decrease_button);
        orderProduct = findViewById(R.id.OrderProduct_button);
        deleteProduct = findViewById(R.id.deleteProduct_button);
        product_Supplier_name = findViewById(R.id.supplierName_textView);
        product_Supplier_phone = findViewById(R.id.supplierPhone_textView);


        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        mInventoryDBHelper = new InventoryDBHelper(getApplicationContext());
        mInventoryDBHelper.open();
        product = mInventoryDBHelper.getProduct(id);
        mInventoryDBHelper.close();

        String product_name = product.getProductName();
        productName.setText(product_name);


        String product_price = String.valueOf(product.getProductPrice());
        productPrice.setText(product_price);

        final String product_quantity = String.valueOf(product.getProductQuantity());
        productQuantity.setText(product_quantity);
        mInteger = product.getProductQuantity();

        String supplierName = product.getSupplierName();
        product_Supplier_name.setText(supplierName);

        final String product_SupplierPhone = product.getSupplierPhone();
        product_Supplier_phone.setText(product_SupplierPhone);

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

        deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog();
            }
        });

        orderProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", product_Supplier_phone.getText().toString(), null));
                startActivity(intent);
            }
        });
    }

    public void increaseInteger(View view) {
        mInteger = mInteger + 1;
        display(mInteger);
        mInventoryDBHelper.open();
        mInventoryDBHelper.updateProduct(product.getId(), product.getProductName(), product.getProductPrice(), mInteger);
        mInventoryDBHelper.close();
    }

    public void decreaseInteger(View view) {
        if ((mInteger - 1) < 0) {
            return;
        }
        mInteger = mInteger - 1;
        display(mInteger);

        mInventoryDBHelper.open();
        mInventoryDBHelper.updateProduct(product.getId(), product.getProductName(), product.getProductPrice(), mInteger);
        mInventoryDBHelper.close();
    }

    private void display(int number) {
        productQuantity.setText(String.valueOf(number));
    }

    private void deleteItem() {

        mInventoryDBHelper.open();
        Boolean isDeleted = mInventoryDBHelper.deleteProduct(this.id);
        mInventoryDBHelper.close();
        if (isDeleted) {
            Toast.makeText(getApplicationContext(), R.string.done, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.error_with_delete, Toast.LENGTH_LONG).show();
        }
    }

    private void showDeleteDialog() {
        //Create an AlertDialog and set message
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_massage);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteItem();
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}