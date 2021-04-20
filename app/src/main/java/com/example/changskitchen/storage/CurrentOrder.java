package com.example.changskitchen.storage;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.changskitchen.models.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class CurrentOrder {
    private static String TAG = "Current Order";
    public static String menuId = null;
    public static List<OrderItem> orderItems = new ArrayList<>();
    public static float totalPrice;

    public static void addItem(OrderItem orderItem, String id, Context context) {
        if (menuId == null || menuId == id) {
            menuId = id;
            orderItems.add(orderItem);
            totalPrice += orderItem.price;
            Toast.makeText(context, "Added to your cart", Toast.LENGTH_SHORT).show();
            Log.e(TAG, String.valueOf(orderItems.size()));
        } else {
            Toast.makeText(context, "Please check out or clear your current cart before ordering from a different menu", Toast.LENGTH_LONG).show();
        }
    }
}
