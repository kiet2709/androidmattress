package com.nhom4nguoi.ecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.activity.ProductDetailActivity;
import com.nhom4nguoi.ecommerce.api.ApiClient;
import com.nhom4nguoi.ecommerce.api.ProductApi;
import com.nhom4nguoi.ecommerce.model.Cloth;
import com.nhom4nguoi.ecommerce.model.Product;
import com.nhom4nguoi.ecommerce.model.Size;
import com.nhom4nguoi.ecommerce.response.APIGetLatestProduct.ApiLatestProductResponse;
import com.nhom4nguoi.ecommerce.response.APIProductDetail.InventoriesItem;
import com.nhom4nguoi.ecommerce.response.APIProductDetail.ProductResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        Glide.with(context).load(cloth.getPath()).into(holder.imageView);
        holder.txtName.setText(cloth.getTitle());
        holder.txtPrice.setText(cloth.getPrice());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProductDetail(cloth.getId());
            }
        });
        holder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProductDetail(cloth.getId());
            }
        });
        holder.txtPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProductDetail(cloth.getId());
            }
        });
    }
    ApiLatestProductResponse apiLatestProductResponse;
    private void loadProductDetail(int id) {
        apiLatestProductResponse = new ApiLatestProductResponse();
        List<Cloth> clothes = new ArrayList<>();
        ProductApi productApi;
        Retrofit retrofit = ApiClient.getClient();
        productApi = retrofit.create(ProductApi.class);
        productApi.getProductById(id).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                ProductResponse productResponse = response.body();
                Product product = new Product();
                product.setId(productResponse.getId());
                product.setName(productResponse.getName());
                product.setPrice(String.valueOf(productResponse.getPrice()));
                product.setDescription(productResponse.getDescription());
                product.setPath("http://172.22.224.1:8081/api/v1/mattresses/"+product.getId()+"/images");
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("product", product);
                List<Size> sizes = new ArrayList<>();
                for (InventoriesItem item: productResponse.getInventories()) {
                    Size size = new Size();
                    size.setId(item.getSize().getId());
                    size.setName(item.getSize().getName());
                    sizes.add(size);
                }
                intent.putExtra("sizes", (Serializable) sizes);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.d("kiet", "onFailure: Error in api product detail");
            }
        });
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
