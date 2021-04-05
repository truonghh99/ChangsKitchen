package com.example.changskitchen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.changskitchen.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrentMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentMenuFragment extends Fragment {

    public CurrentMenuFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CurrentMenuFragment newInstance() {
        CurrentMenuFragment fragment = new CurrentMenuFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_current_menu, container, false);
    }
}