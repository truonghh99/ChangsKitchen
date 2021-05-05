package com.example.changskitchen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.changskitchen.R;
import com.example.changskitchen.activities.MainActivity;
import com.example.changskitchen.databinding.FragmentCompletedOrderBinding;
import com.example.changskitchen.databinding.FragmentPlacedOrderBinding;
import com.example.changskitchen.models.Order;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompletedOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlacedOrderFragment extends Fragment {

    final static String ORDER_KEY = "ORDER";
    FragmentPlacedOrderBinding fragmentPlacedOrderBinding;
    Order order;
    View view_order_placed;
    View view_order_confirmed;
    View view_order_processed;
    View view_order_pickup;
    View placed_divider;
    View con_divider;
    View ready_divider;

    Button btContact;
    TextView textorderpickup;
    TextView text_confirmed;
    TextView textorderprocessed;


    public PlacedOrderFragment() {
        // Required empty public constructor
    }

    public static PlacedOrderFragment newInstance(Order order) {
        PlacedOrderFragment fragment = new PlacedOrderFragment();
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
        fragmentPlacedOrderBinding = FragmentPlacedOrderBinding.inflate(getLayoutInflater());
        view_order_placed = fragmentPlacedOrderBinding.viewOrderPlaced;
        view_order_confirmed = fragmentPlacedOrderBinding.viewOrderConfirmed;
        view_order_processed = fragmentPlacedOrderBinding.viewOrderProcessed;
        view_order_pickup = fragmentPlacedOrderBinding.viewOrderPickup;
        placed_divider = fragmentPlacedOrderBinding.placedDivider;
        con_divider = fragmentPlacedOrderBinding.conDivider;
        ready_divider = fragmentPlacedOrderBinding.readyDivider;
        btContact = fragmentPlacedOrderBinding.btContact;

        textorderpickup = fragmentPlacedOrderBinding.textorderpickup;
        text_confirmed = fragmentPlacedOrderBinding.textConfirmed;
        textorderprocessed = fragmentPlacedOrderBinding.textorderprocessed;

        btContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.switchToContactFragment(order.orderId);
            }
        });

        getOrderStatus(order.status);
        return fragmentPlacedOrderBinding.getRoot();
    }

    private void getOrderStatus(String status) {
        if (status.equals("PLACED")){
            float alfa= (float) 0.5;
            setStatus(alfa);

        }else if (status.equals("CONFIRMED")){
            float alfa= (float) 1;
            setStatus1(alfa);



        }else if (status.equals("PROCESSED")){
            float alfa= (float) 1;
            setStatus2(alfa);


        }else if (status.equals("READY")){
            float alfa= (float) 1;
            setStatus3(alfa);
        }
    }

    private void setStatus(float alfa) {
        float myf= (float) 0.5;
        view_order_placed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        view_order_confirmed.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        view_order_processed.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        con_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        placed_divider.setAlpha(alfa);
        placed_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        text_confirmed.setAlpha(alfa);
        textorderprocessed.setAlpha(alfa);
        view_order_pickup.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        ready_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_current));

        textorderpickup.setAlpha(myf);

    }

    private void setStatus1(float alfa) {
        float myf= (float) 0.5;
        view_order_placed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        view_order_confirmed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        view_order_processed.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        con_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        placed_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));

        text_confirmed.setAlpha(alfa);
        textorderprocessed.setAlpha(myf);
        view_order_pickup.setAlpha(myf);
        ready_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        view_order_pickup.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        textorderpickup.setAlpha(myf);
    }

    private void setStatus2(float alfa) {
        float myf= (float) 0.5;
        view_order_placed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        view_order_confirmed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        view_order_processed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        con_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        placed_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));

        text_confirmed.setAlpha(alfa);
        textorderprocessed.setAlpha(alfa);
        view_order_pickup.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        ready_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_current));
        textorderpickup.setAlpha(myf);


    }

    private void setStatus3(float alfa) {
        view_order_placed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        view_order_confirmed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        view_order_processed.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        con_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));

        placed_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        text_confirmed.setAlpha(alfa);
        textorderprocessed.setAlpha(alfa);
        view_order_pickup.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        ready_divider.setBackground(getResources().getDrawable(R.drawable.shape_status_completed));
        textorderpickup.setAlpha(alfa);
    }
}