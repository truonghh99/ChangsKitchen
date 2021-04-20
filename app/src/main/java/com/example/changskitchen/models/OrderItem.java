package com.example.changskitchen.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderItem implements Parcelable {

    public String orderId;
    public String dishId;
    public String quantity;
    public float price;

    public OrderItem() {
    }

    protected OrderItem(Parcel in) {
        orderId = in.readString();
        dishId = in.readString();
        quantity = in.readString();
        price = in.readFloat();
    }

    public static final Creator<OrderItem> CREATOR = new Creator<OrderItem>() {
        @Override
        public OrderItem createFromParcel(Parcel in) {
            return new OrderItem(in);
        }

        @Override
        public OrderItem[] newArray(int size) {
            return new OrderItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(orderId);
        dest.writeString(dishId);
        dest.writeString(quantity);
        dest.writeFloat(price);
    }
}
