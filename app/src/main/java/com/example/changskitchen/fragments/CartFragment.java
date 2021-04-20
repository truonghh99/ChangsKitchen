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
    TextView tvDate;
    Button btCheckout;
    OrderItemAdapter adapter;

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

        tvDate.setText("Order date: " + convertToDate(CurrentOrder.menuId));
        btCheckout.setText("Checkout - " + CurrentOrder.totalPrice + "$");

        adapter = new OrderItemAdapter(getActivity(), CurrentOrder.orderItems, btCheckout);
        rvCartItems.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCartItems.setAdapter(adapter);

        return fragmentCartBinding.getRoot();
    }

    private String convertToDate(String menuId) {
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