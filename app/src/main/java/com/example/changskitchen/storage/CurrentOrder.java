package com.example.changskitchen.storage;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.changskitchen.models.OrderItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CurrentOrder {
    private static String TAG = "Current Order";
    public static String menuId = null;
    public static String date = "4/21/2021";
    public static List<OrderItem> orderItems = new ArrayList<>();
    public static float totalPrice;

    public static void addItem(OrderItem orderItem, String id, Context context) {
        if (orderItems.isEmpty() || menuId == id) {
            menuId = id;
            date = convertToDate(menuId);
            orderItems.add(orderItem);
            totalPrice += orderItem.price;
            Toast.makeText(context, "Added to your cart", Toast.LENGTH_SHORT).show();
            Log.e(TAG, String.valueOf(orderItems.size()));
        } else {
            Toast.makeText(context, "Please check out or clear your current cart before ordering from a different menu", Toast.LENGTH_LONG).show();
        }
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
}
