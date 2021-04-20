package com.example.changskitchen.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.changskitchen.R;
import com.example.changskitchen.databinding.FragmentDishBinding;
import com.example.changskitchen.models.Dish;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DishFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DishFragment extends DialogFragment {

    private static final String TAG = "DialogFragment";
    private static final String DISH_KEY = "dish";

    private Dish dish;
    private FragmentDishBinding fragmentDishBinding;

    public static DishFragment newInstance(Dish dish) {
        DishFragment fragment = new DishFragment();
        Bundle args = new Bundle();
        args.putParcelable(DISH_KEY, dish);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dish = getArguments().getParcelable(DISH_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dish, container, false);
    }
}