package com.example.changskitchen.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.changskitchen.R;
import com.example.changskitchen.activities.MainActivity;
import com.example.changskitchen.adapters.OrderAdapter;
import com.example.changskitchen.databinding.FragmentHistoryBinding;
import com.example.changskitchen.models.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    private static final String TAG = "HistoryFragment";
    private List<Order> orderList = new ArrayList<>();
    private RecyclerView rvOrders;
    public OrderAdapter adapter;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("orders");

    public HistoryFragment() {
    }

    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getAllOrder();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentHistoryBinding fragmentHistoryBinding = FragmentHistoryBinding.inflate(getLayoutInflater());

        // Set up adapter & recycler view
        rvOrders = fragmentHistoryBinding.rvOrders;
        adapter = new OrderAdapter(getActivity(), orderList);
        rvOrders.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOrders.setAdapter(adapter);

        setUpSearchBar();
        return fragmentHistoryBinding.getRoot();
    }

    public void getAllOrder() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final Query orders = ref.orderByChild("uid").equalTo(uid);

        ChildEventListener orderListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Order order = snapshot.getValue(Order.class);
                order.orderId = snapshot.getKey();
                orderList.add(0, order);
                adapter.updateFullList(orderList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Order order = snapshot.getValue(Order.class);
                order.orderId = snapshot.getKey();
                for (int i = 0; i < orderList.size(); ++i) {
                    if (orderList.get(i).orderId.equals(order.orderId)) {
                        orderList.set(i, order);
                    }
                }
                adapter.updateFullList(orderList);
                adapter.notifyDataSetChanged();
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
        orders.addChildEventListener(orderListener);
    }

    private void setUpSearchBar() {
        if (MainActivity.searchView == null) return;
        MainActivity.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}