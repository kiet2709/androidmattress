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

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.adapter.ClothAdapter2;
import com.nhom4nguoi.ecommerce.api.ApiClient;
import com.nhom4nguoi.ecommerce.api.ProductApi;
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

public class LoadProductActivity extends AppCompatActivity {
    GridView gridView;
    ImageButton homeButton, categoryButton, userButton;
    ClothAdapter2 clothAdapter2;
    EditText edtSearch;
    ImageButton btnSearch;
    ApiLatestProductResponse apiLatestProductResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_product);
        initViews();
        Intent intent = getIntent();
        List<Cloth> clothes = (List<Cloth>) intent.getSerializableExtra("products");
        loadProductSearchByAnotherActivity(clothes);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProduct();
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoadProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(LoadProductActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(LoadProductActivity.this, PersonalActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadProduct() {
        apiLatestProductResponse = new ApiLatestProductResponse();
        List<Cloth> clothes = new ArrayList<>();
        ProductApi productApi;
        Retrofit retrofit = ApiClient.getClient();
        productApi = retrofit.create(ProductApi.class);
        productApi.searchProduct(edtSearch.getText().toString()).enqueue(new Callback<List<ApiLatestProductResponseItem>>() {
            @Override
            public void onResponse(Call<List<ApiLatestProductResponseItem>> call, Response<List<ApiLatestProductResponseItem>> response) {
                apiLatestProductResponse.setApiLatestProductResponse(response.body());
                for (ApiLatestProductResponseItem apiLatestProductResponseItem : apiLatestProductResponse.getApiLatestProductResponse()) {
                    Cloth cloth = new Cloth(
                            apiLatestProductResponseItem.getId(),
                            apiLatestProductResponseItem.getName(),
                            apiLatestProductResponseItem.getPrice()
                    );
                    cloth.setPath("http://172.22.224.1:8081/api/v1/mattresses/"+cloth.getId()+"/images");
                    clothes.add(cloth);
                }
                loadProductSearchByAnotherActivity(clothes);
                Log.d("kiet", "onResponse: clothes search="+clothes.size());
            }

            @Override
            public void onFailure(Call<List<ApiLatestProductResponseItem>> call, Throwable t) {

            }
        });
    }

    private void initViews() {
        gridView = (GridView) findViewById(R.id.gridview_product);
        homeButton = (ImageButton) findViewById(R.id.btn_home);
        categoryButton = (ImageButton) findViewById(R.id.btn_category);
        userButton = (ImageButton) findViewById(R.id.btn_user);
        edtSearch = (EditText) findViewById(R.id.edt_search);
        btnSearch = (ImageButton) findViewById(R.id.btn_search);
    }
    private void loadProductSearchByAnotherActivity(List<Cloth> clothes){

        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        if (clothes.size()<4){
            layoutParams.height = (clothes.size())*850;
        } else {
            layoutParams.height = (clothes.size())*650;
        }
        layoutParams.width = 1067;
        gridView.setLayoutParams(layoutParams);
        clothAdapter2 = new ClothAdapter2(
                LoadProductActivity.this,
                clothes,
                R.layout.item_product2
        );
        gridView.setAdapter(clothAdapter2);
    }
}