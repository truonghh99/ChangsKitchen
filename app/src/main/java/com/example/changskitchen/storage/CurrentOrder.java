package com.example.changskitchen.storage;

import android.content.Context;
import android.widget.Toast;

import com.example.changskitchen.models.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class CurrentOrder {
    public static String menuId = null;
    public static List<OrderItem> orderItems = new ArrayList<>();

    public static void addItem(OrderItem orderItem, String id, Context context) {
        if (menuId == null) {
            menuId = id;
        } else {
            if (menuId != id) {
                Toast.makeText(context, "Please check out or clear your current cart before ordering from a different menu",
                               Toast.LENGTH_LONG);
            } else {
                orderItems.add(orderItem);
            }
        }
    }
}
