package com.example.changskitchen.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.changskitchen.activities.MainActivity;
import com.example.changskitchen.databinding.ItemMenuBinding;
import com.example.changskitchen.fragments.MenuFragment;
import com.example.changskitchen.helpers.DateHelper;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private static final String TAG = "CurrentFoodAdapter";
    private Context context;
    public static List<String> menuIds;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMenuBinding itemMenuBinding = ItemMenuBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemMenuBinding);
    }

    public MenuAdapter(Context context, List<String> menuIds) {
        this.context = context;
        this.menuIds = menuIds;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String menuId = menuIds.get(position);
        holder.bind(menuId);
    }

    @Override
    public int getItemCount() {
        return menuIds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvWeekDay;
        private TextView tvDate;
        private CardView cvMenu;

        public ViewHolder(@NonNull ItemMenuBinding itemMenuBinding) {
            super(itemMenuBinding.getRoot());
            tvWeekDay = itemMenuBinding.tvWeekDay;
            tvDate = itemMenuBinding.tvDate;
            cvMenu = itemMenuBinding.cvMenu;
        }

        public void bind(final String menuId) {
            String date = DateHelper.convertToDate(menuId);
            String weekday = DateHelper.convertToWeekDay(menuId);
            tvWeekDay.setText(weekday);
            tvDate.setText(date);
            cvMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MenuFragment newFragment = MenuFragment.newInstance(menuId);
                    MainActivity.switchFragment(newFragment, DateHelper.convertToDate(menuId));
                }
            });
        }
    }

}