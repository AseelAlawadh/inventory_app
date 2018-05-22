package com.aseelalawadh.inventory_app;


import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class ProductAdapter extends ArrayAdapter<Product> {

    private Context mContext;
    private InventoryDBHelper mInventoryDBHelper ;
    private TextView id;
    private TextView name;
    private TextView price;
    private TextView quantity;
    private Button sale_button;
    private Cursor cursor ;
    private Context context;


    public ProductAdapter(Context context, ArrayList<Product> items) {
        super(context, 0, items);
        mContext = context;
        mInventoryDBHelper = new InventoryDBHelper(mContext);
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.display_list_item, parent, false);
        }
        final Product item = getItem(position);
        id = listItemView.findViewById(R.id.id_textView);
        name = listItemView.findViewById(R.id.name_textView);
        price = listItemView.findViewById(R.id.price_textView);
        quantity = listItemView.findViewById(R.id.quantity_textView);
        sale_button = listItemView.findViewById(R.id.sale_button);

        sale_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            /*if( (item.getProductQuantity()-1) < 0)
                {
                 return;
                }*/
                int int_quantity;
                try {
                    int_quantity = Integer.parseInt(quantity.getText().toString());
                } catch (NumberFormatException e) {
                    int_quantity = 0;
                }
                if (int_quantity > 0) {
                    int_quantity -= 1;
                }

                quantity.setText(String.valueOf(item.getProductQuantity()-1));
                mInventoryDBHelper.open();
                mInventoryDBHelper.updateProduct(item.getId(), item.getProductName(), item.getProductPrice(), item.getProductQuantity());
                Log.v("PRODUCTADAPTER",String.valueOf(item.getProductQuantity()));
                mInventoryDBHelper.close();
                  ContentValues values = new ContentValues();
               // values.put(InventoryContract.COLUMN_INVENTORY_QUANTITY, item.getProductQuantity());
                values.put(InventoryContract.COLUMN_INVENTORY_QUANTITY, int_quantity);


/*

                cursor.moveToPosition(position);
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(InventoryContract._ID));
                Log.i(TAG, "id: " + id);

                int int_quantity;
                try {
                    int_quantity = Integer.parseInt(quantity.getText().toString());
                } catch (NumberFormatException e) {
                    int_quantity = 0;
                }
                if (int_quantity > 0) {
                    int_quantity -= 1;
                }
                quantity.setText(String.valueOf(int_quantity));

                ContentValues values = new ContentValues();
                values.put(InventoryContract.COLUMN_INVENTORY_QUANTITY, int_quantity);

                Uri uri = ContentUris.withAppendedId(InventoryContract.CONTENT_URI, id);

                context.getContentResolver().update(uri, values, null, null);
*/

            }
        });

        id.setText(String.valueOf(position + 1));
        name.setText(item.getProductName());
        price.setText(String.valueOf(item.getProductPrice()));
        quantity.setText(String.valueOf(item.getProductQuantity()));

        return listItemView;
    }


}

