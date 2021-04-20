package com.example.changskitchen.adapters;

import android.content.Context;
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

import com.example.changskitchen.databinding.ItemMenuBinding;
import com.example.changskitchen.models.Dish;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
            String date = convertToDate(menuId);
            String weekday = convertToWeekDay(menuId);
            tvWeekDay.setText(weekday);
            tvDate.setText(date);
            cvMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Switch to dish detail
                }
            });
        }

        private String convertToWeekDay(String menuId) {
            Log.e(TAG, menuId);
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyyMMdd").parse(menuId);
                Log.e(TAG, date.toString());
            } catch (ParseException e) {
                Log.e(TAG, e.toString());
            }
            DateFormat dateFormat =new SimpleDateFormat("EEEE");
            return dateFormat.format(date);
        }

        private String convertToDate(String menuId) {
            Date date = new Date();
            try {
                date = new SimpleDateFormat("yyyyMMdd").parse(menuId);
            } catch (ParseException e) {
                Log.e(TAG, e.toString());
            }
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            return dateFormat.format(date);
        }
    }

}