package com.example.changskitchen.models;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dish {

    final String TAG = "DishModel";
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/fireblog").child("dishes");

    public String dishId;
    public String name;
    public String description;
    public float price;

    public Dish() {
    }

    public Dish(final String dishId) {
        Log.e(TAG, "Fetching dish with id: " + dishId);
        this.dishId = dishId;
        DatabaseReference currRef = ref.child(dishId);
        currRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e(TAG, dataSnapshot.toString());
                Dish dish = dataSnapshot.getValue(Dish.class);
                name = dish.name;
                description = dish.description;
                price = dish.price;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public void saveToDatabase() {
        DatabaseReference dishRef = ref.child("dishes");
        dishRef.child(dishId).setValue(this);
    }
}
