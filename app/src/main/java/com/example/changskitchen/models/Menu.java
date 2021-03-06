package com.example.changskitchen.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.changskitchen.adapters.DishAdapter;
import com.example.changskitchen.fragments.MenuFragment;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Menu implements Parcelable {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("menus");

    public String menuId;
    public Date date;
    public List<Dish> dishes;
    public MenuFragment menuFragment;

    public Menu() {
    }

    public Menu(String id, final MenuFragment menuFragment) {
        this.menuFragment = menuFragment;
        this.menuId = id;
        final Menu thisMenu = this;
        dishes = new ArrayList<>();
        DatabaseReference dishRef = ref.child(id).child("dishes");
        ChildEventListener dishListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String dishId = (String) snapshot.getValue();
                Dish dish = new Dish(dishId, thisMenu);
                dishes.add(dish);
                updateView();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        dishRef.addChildEventListener(dishListener);
    }

    public void updateView() {
        if (menuFragment == null) return;
        menuFragment.adapter.notifyDataSetChanged();
        menuFragment.adapter.updateFullList(dishes);
    }

    protected Menu(Parcel in) {
        menuId = in.readString();
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    public void saveToDatabase() {
        ref.child(menuId).setValue(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(menuId);
    }
}
