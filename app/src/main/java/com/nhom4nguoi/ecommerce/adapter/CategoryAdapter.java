package com.nhom4nguoi.ecommerce.adapter;

import static androidx.core.content.ContextCompat.startActivity;

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
import com.nhom4nguoi.ecommerce.activity.CategoryActivity;
import com.nhom4nguoi.ecommerce.activity.LoadProductActivity;
import com.nhom4nguoi.ecommerce.activity.MainActivity;
import com.nhom4nguoi.ecommerce.api.ApiClient;
import com.nhom4nguoi.ecommerce.api.CategoryApi;
import com.nhom4nguoi.ecommerce.model.Category;
import com.nhom4nguoi.ecommerce.model.Cloth;
import com.nhom4nguoi.ecommerce.response.APIGetLatestProduct.ApiLatestProductResponse;
import com.nhom4nguoi.ecommerce.response.APIGetLatestProduct.ApiLatestProductResponseItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private List<Category> categories;
    private int layout;

    public CategoryAdapter(Context context, List<Category> categories, int layout) {
        this.context = context;
        this.categories = categories;
        this.layout = layout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public CategoryAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return categories==null? 0 :categories.size();
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
        CategoryAdapter.ViewHolder viewHolder;

        if (view==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout,null);

            viewHolder = new CategoryAdapter.ViewHolder();
            viewHolder.textName = (TextView) view.findViewById(R.id.txt_category);
            viewHolder.imagePic = (ImageView) view.findViewById(R.id.img_category);
            view.setTag(viewHolder);
        }else{
            viewHolder= (CategoryAdapter.ViewHolder) view.getTag();
        }
        Category category = categories.get(i);
        viewHolder.textName.setText(category.getName());
        Glide.with(context).load(category.getPath()).into(viewHolder.imagePic);
        viewHolder.textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProductByCategory(category.getId());
            }
        });
        viewHolder.imagePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProductByCategory(category.getId());
            }
        });
        return view;
    }
    ApiLatestProductResponse apiLatestProductResponse;
    private void loadProductByCategory(int id) {
        apiLatestProductResponse = new ApiLatestProductResponse();
        List<Cloth> clothes = new ArrayList<>();
        CategoryApi categoryApi;
        Retrofit retrofit = ApiClient.getClient();
        categoryApi = retrofit.create(CategoryApi.class);
        categoryApi.getProductByCategoryId(id).enqueue(new Callback<List<ApiLatestProductResponseItem>>() {
            @Override
            public void onResponse(Call<List<ApiLatestProductResponseItem>> call, Response<List<ApiLatestProductResponseItem>> response) {
                if (response.isSuccessful()){
                    apiLatestProductResponse.setApiLatestProductResponse(response.body());
                    Intent intent = new Intent(context, LoadProductActivity.class);
                    for (ApiLatestProductResponseItem apiLatestProductResponseItem : apiLatestProductResponse.getApiLatestProductResponse()) {
                        Cloth cloth = new Cloth(
                                apiLatestProductResponseItem.getId(),
                                apiLatestProductResponseItem.getName(),
                                apiLatestProductResponseItem.getPrice()
                        );
                        cloth.setPath("http://172.22.224.1:8081/api/v1/mattresses/"+cloth.getId()+"/images");
                        clothes.add(cloth);
                    }
                    Log.d("kiet", "onResponse: clothes search="+clothes.size());
                    intent.putExtra("products", (Serializable) clothes);
                    context.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<List<ApiLatestProductResponseItem>> call, Throwable t) {
                Log.d("kiet", "onFailure: Error in api load product by category id");
            }
        });
    }

    private class ViewHolder{
        TextView textName;
        ImageView imagePic;
    }
}
