package com.example.changskitchen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.changskitchen.R;
import com.example.changskitchen.databinding.FragmentCompletedOrderBinding;
import com.example.changskitchen.helpers.DateHelper;
import com.example.changskitchen.models.Menu;
import com.example.changskitchen.models.Order;
import com.example.changskitchen.storage.CurrentOrder;

import org.w3c.dom.Text;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompletedOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompletedOrderFragment extends Fragment {

    final static String ORDER_KEY = "ORDER";
    private static final String TAG = "CompletedOrderFragment";
    FragmentCompletedOrderBinding fragmentCompletedOrderBinding;
    Order order;
    String dishNames;
    String dishPrices;

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
        dishNames = order.getSummary().replace(", ", "\n");
        dishPrices = order.getSummaryPrice();
        String tip = String.valueOf(order.tip);
        String tax = String.valueOf(order.tax);
        String total = String.valueOf(order.finalPrice);

        TextView tvDishName = fragmentCompletedOrderBinding.tvDishName;
        TextView tvDishPrice = fragmentCompletedOrderBinding.tvDishNamePrice;
        TextView tvTip = fragmentCompletedOrderBinding.tvTipValue;
        TextView tvTax = fragmentCompletedOrderBinding.tvTaxValue;
        TextView tvTotal = fragmentCompletedOrderBinding.tvTotalValue;
        Button btReorder = fragmentCompletedOrderBinding.btReorder;

        tvDishName.setText(dishNames);
        tvDishPrice.setText(dishPrices);
        tvTip.setText(tip);
        tvTax.setText(tax);
        tvTotal.setText(total);
        btReorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reorder();
            }
        });

        return fragmentCompletedOrderBinding.getRoot();
    }

    private void reorder() {
        if (CurrentOrder.menuId == null) CurrentOrder.menuId = DateHelper.convertToMenuId(new Date());
        int added = 0;
        for (int i = 0; i < order.items.size(); ++i) {
            Log.e(TAG, order.items.get(i).name);
            if (CurrentOrder.menuMap.containsKey(order.items.get(i).name)) {
                Log.e(TAG, "MATCHED " + order.items.get(i).name);
                CurrentOrder.addItem(order.items.get(i), CurrentOrder.menuId);
                ++added;
            }
        }
        Toast.makeText(getContext(), "Added " + added + " items to your car. The rest is not available in the current menu",
                        Toast.LENGTH_LONG).show();
    }
}