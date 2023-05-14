package com.nhom4nguoi.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.adapter.CategoryAdapter;
import com.nhom4nguoi.ecommerce.api.ApiClient;
import com.nhom4nguoi.ecommerce.api.CategoryApi;
import com.nhom4nguoi.ecommerce.api.ProductApi;
import com.nhom4nguoi.ecommerce.model.Category;
import com.nhom4nguoi.ecommerce.model.Cloth;
import com.nhom4nguoi.ecommerce.response.APICategory.CategoryItem;
import com.nhom4nguoi.ecommerce.response.APIGetLatestProduct.ApiLatestProductResponse;
import com.nhom4nguoi.ecommerce.response.APIGetLatestProduct.ApiLatestProductResponseItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CategoryActivity extends AppCompatActivity {

    GridView gridView;
    CategoryAdapter categoryAdapter;
    ImageButton homeButton, categoryButton, userButton;
    ImageView btnCart;
    EditText edtSearch;
    ImageButton btnSearch;
    ApiLatestProductResponse apiLatestProductResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initViews();
        getCategories();

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(CategoryActivity.this, PersonalActivity.class);
                startActivity(intent);
            }
        });
        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(CategoryActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void search() {
        apiLatestProductResponse = new ApiLatestProductResponse();
        List<Cloth> clothes = new ArrayList<>();
        ProductApi productApi;
        Retrofit retrofit = ApiClient.getClient();
        productApi = retrofit.create(ProductApi.class);
        productApi.searchProduct(edtSearch.getText().toString()).enqueue(new Callback<List<ApiLatestProductResponseItem>>() {
            @Override
            public void onResponse(Call<List<ApiLatestProductResponseItem>> call, Response<List<ApiLatestProductResponseItem>> response) {
                if (response.isSuccessful()){
                    apiLatestProductResponse.setApiLatestProductResponse(response.body());
                    Intent intent = new Intent(CategoryActivity.this, LoadProductActivity.class);
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
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<List<ApiLatestProductResponseItem>> call, Throwable t) {
                Log.d("kiet", "onFailure: Error in api search product");

            }
        });
    }

    void initViews(){
        gridView = (GridView) findViewById(R.id.gridview_category);
        homeButton = (ImageButton) findViewById(R.id.btn_home);
        categoryButton = (ImageButton) findViewById(R.id.btn_category);
        userButton = (ImageButton) findViewById(R.id.btn_user);
        categoryButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
        edtSearch = (EditText) findViewById(R.id.edt_search);
        btnSearch = (ImageButton) findViewById(R.id.btn_search);
        btnCart = (ImageView) findViewById(R.id.btnCart);
    }
    private void getCategories(){
        CategoryApi categoryApi;
        Retrofit retrofit = ApiClient.getClient();
        categoryApi = retrofit.create(CategoryApi.class);
        categoryApi.getAllCategory().enqueue(new Callback<List<CategoryItem>>() {
            @Override
            public void onResponse(Call<List<CategoryItem>> call, Response<List<CategoryItem>> response) {
                List<CategoryItem> categoryItems = response.body();
                List<Category> categories = new ArrayList<>();
                for (CategoryItem categoryItem: categoryItems) {
                    Category category = new Category(categoryItem.getName(), categoryItem.getId());
                    category.setPath("http://172.22.224.1:8081/api/v1/categories/" + category.getId()+"/images");
                    categories.add(category);
                }
                ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
                layoutParams.height = (categories.size())*390;
                layoutParams.width = 1067;
                gridView.setLayoutParams(layoutParams);
                categoryAdapter = new CategoryAdapter(
                        CategoryActivity.this,
                        categories,
                        R.layout.item_category
                );
                gridView.setAdapter(categoryAdapter);
            }

            @Override
            public void onFailure(Call<List<CategoryItem>> call, Throwable t) {
                Log.d("kiet", "onFailure: Error in api all category");
            }
        });
    }


}