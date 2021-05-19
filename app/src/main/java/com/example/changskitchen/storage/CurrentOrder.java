package com.example.changskitchen.storage;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.changskitchen.fragments.CartFragment;
import com.example.changskitchen.models.Dish;
import com.example.changskitchen.models.Menu;
import com.example.changskitchen.models.Order;
import com.example.changskitchen.models.OrderItem;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CurrentOrder {
    private static String TAG = "Current Order";
    public static String menuId = null;
    public static String date = "4/21/2021";
    public static List<OrderItem> orderItems = new ArrayList<>();
    public static float tax;
    public static float tip;
    public static float totalPrice;
    public static float finalPrice;
    public static String key = "00000";
    public static HashMap<String, Dish> menuMap = new HashMap<>();

    public static void prepareId() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("orders");
        ref.orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                String last_key = dataSnapshot.getKey();
                key = String.format("%05d", Integer.parseInt(last_key) + 1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @androidx.annotation.Nullable String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @androidx.annotation.Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public static void addItem(OrderItem orderItem, String id, Context context) {
        Log.e(TAG, "Adding id: " + menuId + " " + id);
        if (orderItems.isEmpty() || menuId.equals(id)) {
            menuId = id;
            fetchAvailableDish(menuId);
            date = convertToDate(menuId);
            orderItems.add(orderItem);
            totalPrice += orderItem.price;
            Toast.makeText(context, "Added to your cart", Toast.LENGTH_SHORT).show();
            Log.e(TAG, String.valueOf(orderItems.size()));
        } else {
            Toast.makeText(context, "Please check out or clear your current cart before ordering from a different menu", Toast.LENGTH_LONG).show();
        }
        CartFragment.updateDate();
    }

    public static void clear() {
        orderItems.clear();
        menuId = null;
        tax = 0;
        tip = 0;
        totalPrice = 0;
        finalPrice = 0;
    }
    private static String convertToDate(String menuId) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(menuId);
        } catch (ParseException e) {
            Log.e(TAG, e.toString());
        }
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(date);
    }

    public static void fetchAvailableDish(String id) {
        Log.e(TAG, "Fetching available dishes from " + id);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("menus");
        DatabaseReference dishRef = ref.child(id).child("dishes");
        ChildEventListener dishListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String dishId = (String) snapshot.getValue();
                Dish.addDishToAvailable(dishId);
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

    public static void addItem(OrderItem orderItem, String id) {
        Log.e(TAG, "Adding id: " + menuId + " " + id);
        if (orderItems.isEmpty() || menuId.equals(id)) {
            menuId = id;
            fetchAvailableDish(menuId);
            date = convertToDate(menuId);
            orderItems.add(orderItem);
            totalPrice += orderItem.price;
            Log.e(TAG, String.valueOf(orderItems.size()));
        }
        CartFragment.updateDate();
    }
}
