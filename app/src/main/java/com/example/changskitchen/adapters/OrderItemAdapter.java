package com.example.changskitchen.adapters;

import android.app.Activity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.changskitchen.R;
import com.example.changskitchen.databinding.ItemMenuBinding;
import com.example.changskitchen.databinding.ItemOrderItemBinding;
import com.example.changskitchen.models.OrderItem;

import java.util.ArrayList;
import java.util.List;


public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {
    private List<OrderItem> callListResponses = new ArrayList<>();
    final List templist=new ArrayList<>();
    private Activity context;
    int lastPosition=0;

    public OrderItemAdapter(Activity context, List callListResponses)
    {
        super();
        this.context = context;
        this.callListResponses=callListResponses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemOrderItemBinding itemOrderItemBinding = ItemOrderItemBinding.inflate(layoutInflater, parent, false);
        return new OrderItemAdapter.ViewHolder(itemOrderItemBinding);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final OrderItem orderItem = callListResponses.get(position);

        holder.tvName.setText(orderItem.name);
        holder.tvNote.setText(orderItem.note);
        holder.tvQuantity.setText(String.valueOf(orderItem.quantity));
        holder.tvTotal.setText(String.valueOf(orderItem.price));

        holder.ivMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(orderItem, holder.tvQuantity, -1);
            }
        });
        holder.ivPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(orderItem, holder.tvQuantity, 1);
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(orderItem);
            }
        });
    }

    private void deleteItem(OrderItem orderItem) {
    }

    private void updateQuantity(OrderItem orderItem, TextView tvQuantity, int i) {
        orderItem.quantity += i;
        tvQuantity.setText((int) orderItem.quantity);
    }

    @Override
    public int getItemCount() {
        if(callListResponses!=null){
            return callListResponses.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvNote, tvQuantity, tvTotal;
        ImageView ivMinus, ivPlus, ivDelete;


        public ViewHolder(ItemOrderItemBinding itemOrderItemBinding) {
            super(itemOrderItemBinding.getRoot());
            ivMinus=(ImageView) itemOrderItemBinding.ivMinus;
            ivPlus=(ImageView) itemOrderItemBinding.ivPlus;
            ivDelete=(ImageView) itemOrderItemBinding.ivDelete;
            tvName = itemOrderItemBinding.tvName;
            tvNote = itemOrderItemBinding.tvNote;
            tvQuantity = itemOrderItemBinding.tvQuantity;
            tvTotal = itemOrderItemBinding.tvTotal;

        }
    }



}
