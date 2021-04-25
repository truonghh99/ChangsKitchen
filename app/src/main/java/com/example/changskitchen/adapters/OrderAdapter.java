package com.example.changskitchen.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.changskitchen.activities.MainActivity;
import com.example.changskitchen.databinding.ItemDishBinding;
import com.example.changskitchen.databinding.ItemOrderBinding;
import com.example.changskitchen.databinding.ItemOrderItemBinding;
import com.example.changskitchen.fragments.CompletedOrderFragment;
import com.example.changskitchen.fragments.DishFragment;
import com.example.changskitchen.models.Dish;
import com.example.changskitchen.models.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private static final String TAG = "OrderAdapter";
    private Context context;
    public static List<Order> orders;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemOrderBinding itemOrderBinding = ItemOrderBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemOrderBinding);
    }

    public OrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.bind(order);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDate;
        private TextView tvSummary;
        private TextView tvPrice;
        private CardView cvOrder;

        public ViewHolder(@NonNull ItemOrderBinding itemOrderBinding) {
            super(itemOrderBinding.getRoot());
            tvDate = itemOrderBinding.tvDate;
            tvSummary = itemOrderBinding.tvSummary;
            tvPrice = itemOrderBinding.tvPrice;
            cvOrder = itemOrderBinding.cvOrder;
        }

        public void bind(final Order order) {
            tvDate.setText(order.getStringDate());
            tvSummary.setText(order.getSummary());
            tvPrice.setText("" + order.finalPrice + '$');
            if (order.status.equals("COMPLETED")) {
                cvOrder.setCardBackgroundColor(Color.parseColor("#f2dc96"));
                cvOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CompletedOrderFragment fragment = CompletedOrderFragment.newInstance(order);
                        MainActivity.switchFragment(fragment, "Completed Order");
                    }
                });
            } else {
                cvOrder.setCardBackgroundColor(Color.parseColor("#d8eaab"));
            }
        }
    }

}