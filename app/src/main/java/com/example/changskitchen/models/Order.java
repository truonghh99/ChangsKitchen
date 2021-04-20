package com.example.changskitchen.models;
import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Order {

    public String orderID;
    public String orderTotalPrice;
    public String orderStatus;
    public String orderNotes;
    public String orderTimestamp;
    public String customerName;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/fireblog");

    public Order(){
        //Default constructor calls
    }

    public Order(String id, String price, String status, String notes, String timestamp, String name){
        this.orderID = id;
        this.orderTotalPrice = price;
        this.orderStatus = status;
        this.orderNotes = notes;
        this.orderTimestamp = timestamp;
        this.customerName = name;
    }


    public void saveOrderToDatabase(String oid, final String oPrice, final String oStatus, final String oNotes, final String oTimestamp) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference usersRef = ref.child("users").child(user.getUid());

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                User user = dataSnapshot.getValue(User.class);
                customerName = user.name;
                DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference().child("orders").push();
                String id =orderRef.getKey();
                Order order = new Order(id, oPrice, oStatus, oNotes, oTimestamp, customerName);
                orderRef.setValue(order);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        usersRef.addValueEventListener(userListener);


    }


}
