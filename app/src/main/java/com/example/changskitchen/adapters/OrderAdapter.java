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
import android.widget.Filter;
import android.widget.Filterable;
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
import com.example.changskitchen.fragments.PlacedOrderFragment;
import com.example.changskitchen.helpers.DateHelper;
import com.example.changskitchen.models.Dish;
import com.example.changskitchen.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "OrderAdapter";
    private Context context;
    public static List<Order> orders;
    public static List<Order> ordersFull;


    private Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Order> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(ordersFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Order order : ordersFull) {
                    if (order.getSummary().toLowerCase().contains(filterPattern) ||
                            DateHelper.convertToDate(order.date).toLowerCase().contains(filterPattern)) {
                        filteredList.add(order);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            orders.clear();
            orders.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public void updateFullList(List<Order> ids) {
        this.orders = new ArrayList<>(ids);
        this.ordersFull = new ArrayList<>(ids);
        Log.e(TAG, "FULL SIZE: " + orders.size());
        Log.e(TAG, "CURRENT SIZE: " + ordersFull.size());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemOrderBinding itemOrderBinding = ItemOrderBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemOrderBinding);
    }

    public OrderAdapter(Context context, List<Order> orders) {
        this.context = context;
        this.orders = new ArrayList<>(orders);
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

    @Override
    public Filter getFilter() {
        return filter;
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
                        MainActivity.switchFragment(fragment, order.orderId.replace('-', '#'));
                    }
                });
            } else {
                cvOrder.setCardBackgroundColor(Color.parseColor("#d8eaab"));
                cvOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PlacedOrderFragment fragment = PlacedOrderFragment.newInstance(order);
                        MainActivity.switchFragment(fragment, order.orderId.replace('-', '#'));
                    }
                });
            }
        }
    }

}