package com.example.changskitchen.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.example.changskitchen.storage.CurrentOrder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dish implements Parcelable {

    private final String TAG = "DishModel";
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
                CurrentOrder.menuMap.put(dish.name, dish);
                Log.e(TAG, "Fetched " + dish.name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public Dish(final String dishId, final Menu menu) {
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
                menu.updateView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    protected Dish(Parcel in) {
        dishId = in.readString();
        name = in.readString();
        description = in.readString();
        price = in.readFloat();
    }

    public static final Creator<Dish> CREATOR = new Creator<Dish>() {
        @Override
        public Dish createFromParcel(Parcel in) {
            return new Dish(in);
        }

        @Override
        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };

    public void saveToDatabase() {
        DatabaseReference dishRef = ref.child("dishes");
        dishRef.child(dishId).setValue(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(TAG);
        dest.writeString(dishId);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeFloat(price);
    }
}
