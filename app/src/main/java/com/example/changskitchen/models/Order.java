package com.example.changskitchen.models;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.changskitchen.storage.CurrentOrder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Order implements Parcelable {

    public String orderId;
    public String uid;
    public List<OrderItem> items;
    public String date;
    public float tax;
    public float tip;
    public float finalPrice;
    public String status;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("orders");

    public Order(){
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        items = CurrentOrder.orderItems;
        date = CurrentOrder.menuId;
        tax = CurrentOrder.tax;
        tip = CurrentOrder.tip;
        finalPrice = CurrentOrder.finalPrice;
        status = "PLACED";
    }

    protected Order(Parcel in) {
        uid = in.readString();
        items = in.createTypedArrayList(OrderItem.CREATOR);
        date = in.readString();
        tax = in.readFloat();
        tip = in.readFloat();
        finalPrice = in.readFloat();
        status = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public void saveToDatabase() {
        DatabaseReference orderRef = ref.child(CurrentOrder.key);
        orderRef.setValue(this);
    }

    public String getStringDate() {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(this.date);
        } catch (ParseException e) {
        }
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(date);
    }

    public String getSummary() {
        String res = "";
        res += (int) items.get(0).quantity + " " + items.get(0).name;
        for (int i = 1; i < items.size(); ++i) {
            res += ", " + (int) items.get(i).quantity + " " + items.get(i).name;
        }
        return res;
    }

    public String getSummaryPrice() {
        String res = "";
        res += (int) items.get(0).price;
        for (int i = 1; i < items.size(); ++i) {
            res += "\n" + items.get(i).price;
        }
        return res;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uid);
        dest.writeTypedList(items);
        dest.writeString(date);
        dest.writeFloat(tax);
        dest.writeFloat(tip);
        dest.writeFloat(finalPrice);
        dest.writeString(status);
    }
}
