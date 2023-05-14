package com.nhom4nguoi.ecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

public class ClothAdapter2 extends BaseAdapter {
    private Context context;
    private List<Cloth> clothes;
    private int layout;

    public ClothAdapter2(Context context, List<Cloth> clothes, int layout) {
        this.context = context;
        this.clothes = clothes;
        this.layout = layout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Cloth> getClothes() {
        return clothes;
    }

    public void setClothes(List<Cloth> clothes) {
        this.clothes = clothes;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public ClothAdapter2(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return clothes==null? 0 :clothes.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout,null);

            viewHolder = new ViewHolder();
            viewHolder.textName = (TextView) view.findViewById(R.id.product_name2);
            viewHolder.textDesc = (TextView) view.findViewById(R.id.product_price2);
            viewHolder.imagePic = (ImageView) view.findViewById(R.id.img_product2);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        Cloth cloth = clothes.get(i);
        viewHolder.textName.setText(cloth.getTitle());
        viewHolder.textDesc.setText(cloth.getPrice().toString());
        Glide.with(context).load(cloth.getPath()).into(viewHolder.imagePic);
        viewHolder.textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProductDetail(cloth.getId());
            }
        });
        viewHolder.textDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProductDetail(cloth.getId());
            }
        });
        viewHolder.imagePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProductDetail(cloth.getId());
            }
        });
        return view;
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
    private class ViewHolder{
        TextView textName,textDesc;
        ImageView imagePic;
    }

}
