package com.nhom4nguoi.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.adapter.BannerAdapter;
import com.nhom4nguoi.ecommerce.adapter.ClothAdapter;
import com.nhom4nguoi.ecommerce.adapter.ClothAdapter2;
import com.nhom4nguoi.ecommerce.api.ApiClient;
import com.nhom4nguoi.ecommerce.api.ProductApi;
import com.nhom4nguoi.ecommerce.model.Banner;
import com.nhom4nguoi.ecommerce.model.Cloth;
import com.nhom4nguoi.ecommerce.response.APIGetLatestProduct.ApiLatestProductResponse;
import com.nhom4nguoi.ecommerce.response.APIGetLatestProduct.ApiLatestProductResponseItem;
import com.nhom4nguoi.ecommerce.response.APIGetLatestProduct.ApiLatestProductResponseStatic;
import com.nhom4nguoi.ecommerce.util.SharedPrefManager;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcvCloth, rcvForyou;
    GridView gridView;
    ImageButton homeButton, categoryButton, userButton, btnSearch;
    ClothAdapter clothAdapter, clothAdapterForyou;
    ClothAdapter2 clothAdapter2;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private BannerAdapter bannerAdapter;
    private EditText edtSearch;
    ImageView btnCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        bannerAdapter = new BannerAdapter(this, getBannerList());

        viewPager.setAdapter(bannerAdapter);
        circleIndicator.setViewPager(viewPager);
        bannerAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        clothAdapter = new ClothAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcvCloth.setLayoutManager(linearLayoutManager);
        loadBestSellerProduct();
        rcvCloth.setAdapter(clothAdapter);

        clothAdapterForyou = new ClothAdapter(this);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcvForyou.setLayoutManager(linearLayoutManager1);
        loadProductForYou();
        rcvForyou.setAdapter(clothAdapterForyou);

        loadAllProduct();
        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(MainActivity.this, PersonalActivity.class);
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
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    ApiLatestProductResponse apiLatestProductResponse;

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
                    Intent intent = new Intent(MainActivity.this, LoadProductActivity.class);
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
        viewPager = findViewById(R.id.viewpaper);
        circleIndicator = findViewById(R.id.circle_indicator);

        rcvCloth = findViewById(R.id.rcv_cloth);
        rcvForyou = findViewById(R.id.rcv_cloth_foryou);
        gridView = (GridView) findViewById(R.id.gridview_product);
        homeButton = (ImageButton) findViewById(R.id.btn_home);
        categoryButton = (ImageButton) findViewById(R.id.btn_category);
        userButton = (ImageButton) findViewById(R.id.btn_user);
        homeButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
        btnSearch = (ImageButton) findViewById(R.id.btn_search);
        edtSearch = (EditText) findViewById(R.id.edt_search);
        btnCart = (ImageView) findViewById(R.id.btnCart);
    }
    private void loadProductForYou() {
        apiLatestProductResponse = new ApiLatestProductResponse();
        List<Cloth> clothes = new ArrayList<>();
        ProductApi productApi;
        Retrofit retrofit = ApiClient.getClient();
        productApi = retrofit.create(ProductApi.class);
        productApi.getLatestProduct().enqueue(new Callback<List<ApiLatestProductResponseItem>>() {
            @Override
            public void onResponse(Call<List<ApiLatestProductResponseItem>> call, Response<List<ApiLatestProductResponseItem>> response) {
                if (response.isSuccessful()){
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
                    clothAdapter.setData(clothes);
                }
            }

            @Override
            public void onFailure(Call<List<ApiLatestProductResponseItem>> call, Throwable t) {
                Log.d("kiet", "onFailure: Error in api latest product");
            }
        });
    }
    void loadBestSellerProduct(){
        apiLatestProductResponse = new ApiLatestProductResponse();
        List<Cloth> clothes = new ArrayList<>();
        ProductApi productApi;
        Retrofit retrofit = ApiClient.getClient();
        productApi = retrofit.create(ProductApi.class);
        productApi.getBestSellerProduct().enqueue(new Callback<List<ApiLatestProductResponseItem>>() {
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
                clothAdapterForyou.setData(clothes);

            }

            @Override
            public void onFailure(Call<List<ApiLatestProductResponseItem>> call, Throwable t) {
                Log.d("kiet", "onFailure: Error in api best seller product");
            }
        });
    }
    void loadAllProduct(){
        apiLatestProductResponse = new ApiLatestProductResponse();
        List<Cloth> clothes = new ArrayList<>();
        ProductApi productApi;
        Retrofit retrofit = ApiClient.getClient();
        productApi = retrofit.create(ProductApi.class);
        productApi.getAllProduct().enqueue(new Callback<List<ApiLatestProductResponseItem>>() {
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
                ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
                layoutParams.height = (clothes.size())*320;
                layoutParams.width = 1067;
                gridView.setLayoutParams(layoutParams);
                clothAdapter2 = new ClothAdapter2(
                        MainActivity.this,
                        clothes,
                        R.layout.item_product2
                );
                gridView.setAdapter(clothAdapter2);
            }

            @Override
            public void onFailure(Call<List<ApiLatestProductResponseItem>> call, Throwable t) {
                Log.d("kiet", "onFailure: Error in api load all product");
            }
        });
    }

    private List<Banner> getBannerList() {
        List<Banner> bannerList = new ArrayList<>();
        bannerList.add(new Banner(R.drawable.banner1));
        bannerList.add(new Banner(R.drawable.banner2));
        bannerList.add(new Banner(R.drawable.banner3));
        return bannerList;
    }

}