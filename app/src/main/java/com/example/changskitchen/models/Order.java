package com.example.changskitchen.models;
import androidx.annotation.NonNull;

import com.example.changskitchen.storage.CurrentOrder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Order {

    public String uid;
    public List<OrderItem> items;
    public String date;
    public float tax;
    public float tip;
    public float finalPrice;
    public String status;


    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/fireblog").child("orders");

    public Order(){
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        items = CurrentOrder.orderItems;
        date = CurrentOrder.menuId;
        tax = CurrentOrder.tax;
        tip = CurrentOrder.tip;
        finalPrice = CurrentOrder.finalPrice;
        status = "Placed";
    }


}
