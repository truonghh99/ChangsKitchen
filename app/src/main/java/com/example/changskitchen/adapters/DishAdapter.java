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

import com.example.changskitchen.activities.MainActivity;
import com.example.changskitchen.databinding.ItemDishBinding;
import com.example.changskitchen.fragments.DishFragment;
import com.example.changskitchen.models.Dish;

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {

    private static final String TAG = "CurrentFoodAdapter";
    private Context context;
    public static List<Dish> dishes;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemDishBinding itemDishBinding = ItemDishBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemDishBinding);
    }

    public DishAdapter(Context context, List<Dish> dishes) {
        this.context = context;
        this.dishes = dishes;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dish dish = dishes.get(position);
        holder.bind(dish);
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvDescription;
        private TextView tvPrice;
        private CardView cvDish;

        public ViewHolder(@NonNull ItemDishBinding itemDishBinding) {
            super(itemDishBinding.getRoot());
            tvName = itemDishBinding.tvName;
            tvDescription = itemDishBinding.tvDescription;
            tvPrice = itemDishBinding.tvPrice;
            cvDish = itemDishBinding.cvDish;
        }

        public void bind(final Dish dish) {
            tvName.setText(dish.name);
            tvDescription.setText(dish.description);
            tvPrice.setText("" + dish.price + '$');
            cvDish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DishFragment fragment = DishFragment.newInstance(dish);
                    MainActivity.showDialogFragment(fragment, "Dish Detail");
                }
            });
        }
    }

}