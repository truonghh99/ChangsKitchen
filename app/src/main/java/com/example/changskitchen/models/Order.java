package com.example.changskitchen.models;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.firebase.auth.FirebaseAuth.*;

public class Order {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/fireblog");


    public String orderID;
    public double orderTotalPrice;
    public String orderStatus;
    public String orderNotes;
    public String timestamp;

    public Order() {
        orderID = getInstance().getCurrentOrder().getorderID();
    }

    public void saveToDatabase() {
        DatabaseReference usersRef = ref.child("orders");
        usersRef.child(orderID).setValue(this);
    }





}
