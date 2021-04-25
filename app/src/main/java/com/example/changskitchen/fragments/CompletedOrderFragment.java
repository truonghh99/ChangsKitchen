package com.example.changskitchen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.changskitchen.R;
import com.example.changskitchen.databinding.FragmentCompletedOrderBinding;
import com.example.changskitchen.models.Order;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompletedOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompletedOrderFragment extends Fragment {

    final static String ORDER_KEY = "ORDER";
    FragmentCompletedOrderBinding fragmentCompletedOrderBinding;
    Order order;

    public CompletedOrderFragment() {
        // Required empty public constructor
    }

    public static CompletedOrderFragment newInstance(Order order) {
        CompletedOrderFragment fragment = new CompletedOrderFragment();
        Bundle args = new Bundle();
        args.putParcelable(ORDER_KEY, order);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.order = getArguments().getParcelable(ORDER_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCompletedOrderBinding = FragmentCompletedOrderBinding.inflate(getLayoutInflater());
        return fragmentCompletedOrderBinding.getRoot();
    }
}