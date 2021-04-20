package com.example.changskitchen.adapters;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.changskitchen.R;
import com.example.changskitchen.databinding.ItemMenuBinding;
import com.example.changskitchen.databinding.ItemOrderItemBinding;
import com.example.changskitchen.models.OrderItem;
import com.example.changskitchen.storage.CurrentOrder;

import java.util.ArrayList;
import java.util.List;


public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {
    private List<OrderItem> callListResponses = new ArrayList<>();
    private Activity context;
    private Button btCheckout;

    public OrderItemAdapter(Activity context, List callListResponses, Button btCheckout)
    {
        super();
        this.context = context;
        this.callListResponses=callListResponses;
        this.btCheckout = btCheckout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemOrderItemBinding itemOrderItemBinding = ItemOrderItemBinding.inflate(layoutInflater, parent, false);
        return new OrderItemAdapter.ViewHolder(itemOrderItemBinding);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final OrderItem orderItem = callListResponses.get(position);

        holder.tvName.setText(orderItem.name);
        holder.tvQuantity.setText(String.valueOf(orderItem.quantity));
        holder.tvTotal.setText(String.valueOf(orderItem.price));

        holder.ivMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(orderItem, holder.tvQuantity, holder.tvTotal, -1);
            }
        });
        holder.ivPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(orderItem, holder.tvQuantity, holder.tvTotal, 1);
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(position);
            }
        });
    }

    private void deleteItem(int position) {
        CurrentOrder.totalPrice -= CurrentOrder.orderItems.get(position).price;
        CurrentOrder.orderItems.remove(position);
        notifyDataSetChanged();
        btCheckout.setText("Checkout - " + CurrentOrder.totalPrice + "$");
    }

    private void updateQuantity(OrderItem orderItem, TextView tvQuantity, TextView tvTotal, int i) {
        orderItem.quantity += i;
        orderItem.price += orderItem.unitPrice * i;
        CurrentOrder.totalPrice += orderItem.unitPrice * i;

        tvQuantity.setText(String.valueOf(orderItem.quantity));
        tvTotal.setText(String.valueOf(orderItem.price));
        btCheckout.setText("Checkout - " + CurrentOrder.totalPrice + "$");
    }

    @Override
    public int getItemCount() {
        if(callListResponses!=null){
            return callListResponses.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvQuantity, tvTotal;
        ImageView ivMinus, ivPlus, ivDelete;


        public ViewHolder(ItemOrderItemBinding itemOrderItemBinding) {
            super(itemOrderItemBinding.getRoot());
            ivMinus=(ImageView) itemOrderItemBinding.ivMinus;
            ivPlus=(ImageView) itemOrderItemBinding.ivPlus;
            ivDelete=(ImageView) itemOrderItemBinding.ivDelete;
            tvName = itemOrderItemBinding.tvName;
            tvQuantity = itemOrderItemBinding.tvQuantity;
            tvTotal = itemOrderItemBinding.tvTotal;

        }
    }



}
