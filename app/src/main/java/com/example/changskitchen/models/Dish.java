package com.example.changskitchen.models;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Dish {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/fireblog");

    public String dishId;
    public String name;
    public String description;
    public float price;

    public Dish() {
    }

    public void saveToDatabase() {
        DatabaseReference dishRef = ref.child("menu");
        dishRef.child(dishId).setValue(this);
    }
}
