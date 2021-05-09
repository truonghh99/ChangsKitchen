package com.example.changskitchen.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.changskitchen.adapters.MenuAdapter;
import com.example.changskitchen.databinding.FragmentFutureMenusBinding;
import com.example.changskitchen.helpers.DateHelper;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FutureMenusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FutureMenusFragment extends Fragment {

    final String TAG = "FutureMenusFragment";
    FragmentFutureMenusBinding fragmentFutureMenusBinding;
    public MenuAdapter adapter;
    List<String> menuIds = new ArrayList<>();
    RecyclerView rvMenu;

    public FutureMenusFragment() {
    }

    public static FutureMenusFragment newInstance() {
        FutureMenusFragment fragment = new FutureMenusFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMenuIdList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentFutureMenusBinding = FragmentFutureMenusBinding.inflate(getLayoutInflater());
        rvMenu = fragmentFutureMenusBinding.rvMenu;

        adapter = new MenuAdapter(getContext(), menuIds);
        rvMenu.setAdapter(adapter);
        rvMenu.setLayoutManager(new GridLayoutManager(getContext(), 2));

        return fragmentFutureMenusBinding.getRoot();
    }

    private void getMenuIdList() {
        String today = DateHelper.convertToMenuId(new Date());
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("menus");
        Query futureMenusQuery = ref.orderByKey().startAt(today);
        futureMenusQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.e(TAG, snapshot.toString());
                menuIds.add(snapshot.getKey());
                adapter.notifyDataSetChanged();
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
        });
    }
}