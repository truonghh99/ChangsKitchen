package com.example.changskitchen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.changskitchen.R;
import com.example.changskitchen.activities.MainActivity;
import com.example.changskitchen.adapters.DishAdapter;
import com.example.changskitchen.adapters.OrderItemAdapter;
import com.example.changskitchen.databinding.FragmentCartBinding;
import com.example.changskitchen.databinding.FragmentDishBinding;
import com.example.changskitchen.models.Order;
import com.example.changskitchen.models.OrderItem;
import com.example.changskitchen.storage.CurrentOrder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    private static final String TAG = "CartFragment";

    private FragmentCartBinding fragmentCartBinding;

    RecyclerView rvCartItems;
    static TextView tvDate;
    static Button btCheckout;
    static OrderItemAdapter adapter;

    public CartFragment() {
    }

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCartBinding = FragmentCartBinding.inflate(getLayoutInflater());
        rvCartItems = fragmentCartBinding.rvCartItems;
        tvDate = fragmentCartBinding.tvDate;
        btCheckout = fragmentCartBinding.btCheckout;
        updateDate();
        btCheckout.setText("Checkout - " + CurrentOrder.totalPrice + "$");

        adapter = new OrderItemAdapter(getActivity(), CurrentOrder.orderItems, btCheckout);
        rvCartItems.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCartItems.setAdapter(adapter);

        btCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckoutFragment checkoutFragment = new CheckoutFragment();
                MainActivity.showDialogFragment(checkoutFragment, "Checkout");
            }
        });
        return fragmentCartBinding.getRoot();
    }

    public static void updateAdapter() {
        if (adapter == null) return;
        adapter.notifyDataSetChanged();
        updateDate();
    }

    public static void updateDate() {
        if (tvDate == null) return;
        if (CurrentOrder.orderItems.isEmpty()) {
            tvDate.setText("Your cart is empty");
        } else {
            tvDate.setText("Order date: " + CurrentOrder.date);
        }
    }
}