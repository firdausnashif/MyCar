package com.daus.mycar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    List<CarItem> data;
    Activity activity;

    public CarAdapter(List<CarItem> data, Activity activity){
        this.data = data;
        this.activity = activity;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_car, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView carName, description, tv_code, price;

        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            carName = itemView.findViewById(R.id.carName);
            description = itemView.findViewById(R.id.description);
            tv_code = itemView.findViewById(R.id.tv_code);
            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.imageView);

        }

        public void bind(CarItem carItem) {
            carName.setText(carItem.name_car);
            description.setText(carItem.description);
            tv_code.setText(carItem.code);
            price.setText(carItem.price);

            Glide.with(activity).load(carItem.getImage()).into(imageView);

        }
    }
}
