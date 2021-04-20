package com.example.changskitchen.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.List;

public class Menu implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
