package com.aseelalawadh.inventory_app;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    private Context mContext;

    private TextView id;
    private TextView name;
    private TextView price;
    private TextView quantity;

    public ProductAdapter(Context context, ArrayList<Product> items) {
        super(context, 0, items);
        mContext = context;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.display_list_item, parent, false);
            id = listItemView.findViewById(R.id.id_textView);
            name = listItemView.findViewById(R.id.name_textView);
            price = listItemView.findViewById(R.id.price_textView);
            quantity = listItemView.findViewById(R.id.quantity_textView);
        }

        Product item = getItem(position);

        id.setText(String.valueOf(position + 1));
        name.setText(item.getProductName());
        price.setText(String.valueOf(item.getProductPrice()));
        quantity.setText(String.valueOf(item.getProductQuantity()));

        return listItemView;
    }

}

