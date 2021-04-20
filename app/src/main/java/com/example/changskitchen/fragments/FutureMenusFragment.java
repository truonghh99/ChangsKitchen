package com.example.changskitchen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.changskitchen.R;
import com.example.changskitchen.adapters.MenuAdapter;
import com.example.changskitchen.databinding.FragmentFutureMenusBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FutureMenusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FutureMenusFragment extends Fragment {

    final String TAG = "FutureMenusFragment";
    FragmentFutureMenusBinding fragmentFutureMenusBinding;
    MenuAdapter adapter;
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
        if (getArguments() != null) {
        }
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
}