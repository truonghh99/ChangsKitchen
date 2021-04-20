package com.example.changskitchen.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.List;

public class Menu {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("server/saving-data/fireblog");

    public String menuId;
    public Date date;
    public List<Dish> dishes;

    public Menu() {
    }

    public void saveToDatabase() {
        DatabaseReference dishRef = ref.child("menu");
        dishRef.child(menuId).setValue(this);
    }
}
