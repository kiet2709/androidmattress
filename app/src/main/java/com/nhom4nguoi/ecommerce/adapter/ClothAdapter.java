package com.nhom4nguoi.ecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.model.Cloth;

import java.util.List;

public class ClothAdapter extends RecyclerView.Adapter<ClothAdapter.ClothViewHolder>{

    private Context context;
    private List<Cloth> clothes;

    public ClothAdapter(Context context) {
        this.context = context;
    }


    public void setData(List<Cloth> clothes){
        this.clothes = clothes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ClothViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ClothViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClothViewHolder holder, int position) {
        Cloth cloth = clothes.get(position);
        if (cloth == null) {
            return;
        }
        holder.imageView.setImageResource(cloth.getResourceId());
        holder.txtName.setText(cloth.getTitle());
        holder.txtPrice.setText(cloth.getPrice());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false);


    }

    @Override
    public int getItemCount() {
        if (clothes != null){
            return clothes.size();
        }
        return 0;
    }

    public class ClothViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView txtName, txtPrice;

        public ClothViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_product);
            txtName = itemView.findViewById(R.id.product_name);
            txtPrice = itemView.findViewById(R.id.product_price);
        }
    }
}
