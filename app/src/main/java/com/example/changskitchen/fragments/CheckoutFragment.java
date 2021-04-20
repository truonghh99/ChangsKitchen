package com.example.changskitchen.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.changskitchen.R;
import com.example.changskitchen.databinding.FragmentCheckoutBinding;
import com.example.changskitchen.databinding.FragmentDishBinding;
import com.example.changskitchen.models.Dish;
import com.example.changskitchen.models.OrderItem;
import com.example.changskitchen.storage.CurrentOrder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DishFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckoutFragment extends DialogFragment {

    private static final String TAG = "CheckoutFragment";

    private FragmentCheckoutBinding checkoutFragment;
    private TextView tvName;
    private TextView tvDescription;
    private EditText etNote;
    private EditText etQuantity;
    private Button btAdd;

    public static DishFragment newInstance(Dish dish, String menuId) {
        DishFragment fragment = new DishFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        checkoutFragment = FragmentCheckoutBinding.inflate(getLayoutInflater());
        return checkoutFragment.getRoot();
    }
}