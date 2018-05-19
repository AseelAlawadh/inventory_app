package com.aseelalawadh.inventory_app;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    private int id;
    private String productName;
    private int productPrice;
    private int productQuantity;
    private String supplierName;
    private String supplierPhone;

    public Product() {
        id = -1;
        productName = null;
        productPrice = -1;
        productQuantity = -1;
        supplierName = null;
        supplierPhone = null;
    }

    protected Product(Parcel in) {
        id = in.readInt();
        productName = in.readString();
        productPrice = in.readInt();
        productQuantity = in.readInt();
        supplierName = in.readString();
        supplierPhone = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getSupplierName());
        dest.writeString(this.getSupplierPhone());
        dest.writeString(this.getProductName());
        dest.writeInt(this.getId());
        dest.writeInt(this.getProductPrice());
        dest.writeInt(this.getProductQuantity());
    }
}
