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
import com.example.changskitchen.databinding.FragmentDishBinding;
import com.example.changskitchen.models.Dish;
import com.example.changskitchen.models.OrderItem;
import com.example.changskitchen.storage.CurrentOrder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DishFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DishFragment extends DialogFragment {

    private static final String TAG = "DialogFragment";
    private static final String DISH_KEY = "dish";
    private static final String MENU_KEY = "menu";

    private Dish dish;
    private String menuId;

    private FragmentDishBinding fragmentDishBinding;
    private TextView tvName;
    private TextView tvDescription;
    private EditText etNote;
    private EditText etQuantity;
    private Button btAdd;

    public static DishFragment newInstance(Dish dish, String menuId) {
        DishFragment fragment = new DishFragment();
        Bundle args = new Bundle();
        args.putParcelable(DISH_KEY, dish);
        args.putString(MENU_KEY, menuId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dish = getArguments().getParcelable(DISH_KEY);
            menuId = getArguments().getString(MENU_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDishBinding = FragmentDishBinding.inflate(getLayoutInflater());
        tvName = fragmentDishBinding.tvName;
        tvDescription = fragmentDishBinding.tvDescription;
        etNote = fragmentDishBinding.etNote;
        etQuantity = fragmentDishBinding.etQuantity;
        btAdd = fragmentDishBinding.btAdd;

        tvName.setText(dish.name);
        tvDescription.setText(dish.description);
        getTextForButton();

        etQuantity.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    getTextForButton();
                    return true;
                }
                return false;
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderItem orderItem = new OrderItem();
                orderItem.name = dish.name;
                orderItem.description = dish.description;
                orderItem.unitPrice = dish.price;
                orderItem.quantity = Float.valueOf(etQuantity.getText().toString());
                orderItem.price = orderItem.unitPrice * orderItem.quantity;
                orderItem.note = etNote.getText().toString();
                CurrentOrder.addItem(orderItem, menuId, getActivity());
                Log.e(TAG, "clicked");
                dismiss();
            }
        });
        return fragmentDishBinding.getRoot();
    }

    private void getTextForButton() {
        int quantity = Integer.valueOf(etQuantity.getText().toString());
        float total = quantity * dish.price;
        btAdd.setText("Add to Cart - " + total + "$");
    }
}