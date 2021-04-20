package com.example.changskitchen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.changskitchen.R;
import com.example.changskitchen.adapters.DishAdapter;
import com.example.changskitchen.databinding.FragmentMenuBinding;
import com.example.changskitchen.models.Dish;
import com.example.changskitchen.models.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    private static final String TAG = "MenuFragment";
    private static final String MENU_KEY = "MenuKey";
    private Menu menu;
    private List<Dish> dishes = new ArrayList<>();
    private RecyclerView rvDishes;
    private DishAdapter adapter;

    public MenuFragment() {
    }

    public static MenuFragment newInstance(Menu menu) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putParcelable(MENU_KEY, menu);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            menu = getArguments().getParcelable(MENU_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentMenuBinding fragmentMenuBinding = FragmentMenuBinding.inflate(getLayoutInflater());
        rvDishes = fragmentMenuBinding.rvDishes;
        adapter = new DishAdapter(getActivity(), menu.dishes);
        rvDishes.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDishes.setAdapter(adapter);

        return fragmentMenuBinding.getRoot();
    }
}