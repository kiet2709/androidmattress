package com.nhom4nguoi.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.adapter.AdminProductAdapter;
import com.nhom4nguoi.ecommerce.adapter.CartAdapter;
import com.nhom4nguoi.ecommerce.api.ApiClient;
import com.nhom4nguoi.ecommerce.api.ProductApi;
import com.nhom4nguoi.ecommerce.api.SizeApi;
import com.nhom4nguoi.ecommerce.model.CartItem;
import com.nhom4nguoi.ecommerce.response.APIGetLatestProduct.ApiLatestProductResponseItem;
import com.nhom4nguoi.ecommerce.response.APIGetLatestProduct.InventoriesItem;
import com.nhom4nguoi.ecommerce.response.Size.SizeResponseItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminProductActivity extends AppCompatActivity {
    Spinner spinner;
    GridView gridView;
    AdminProductAdapter adminProductAdapter;
    ImageButton btnHomeAdmin, btnProductAdmin, btnAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);
        initViews();
        getCartItem();
        btnHomeAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminProductActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
        btnProductAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminProductActivity.this, AdminProductActivity.class);
                startActivity(intent);
            }
        });
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminProductActivity.this, AdminSignOutAcitivity.class);
                startActivity(intent);
            }
        });
    }
    void initViews(){
        gridView = (GridView) findViewById(R.id.gridview_productAdmin);
        btnHomeAdmin = (ImageButton) findViewById(R.id.btnHomeAdmin);
        btnProductAdmin = (ImageButton) findViewById(R.id.btnProductAdmin);
        btnAdmin = (ImageButton) findViewById(R.id.btnAdmin);
        btnProductAdmin.setBackgroundTintList(ColorStateList.valueOf(0xFFCC6600));
    }
    private void getCartItem() {
        ProductApi productApi;
        Retrofit retrofit = ApiClient.getClient();
        productApi = retrofit.create(ProductApi.class);
        List<CartItem> cartItems = new ArrayList<>();
        productApi.getAllProduct().enqueue(new Callback<List<ApiLatestProductResponseItem>>() {
            @Override
            public void onResponse(Call<List<ApiLatestProductResponseItem>> call, Response<List<ApiLatestProductResponseItem>> response) {
                List<ApiLatestProductResponseItem> items = response.body();
                for (ApiLatestProductResponseItem item:items) {
                    CartItem cartItem = new CartItem();
                    cartItem.setId(item.getId());
                    cartItem.setPrice(String.valueOf(item.getPrice()));
                    cartItem.setName(item.getName());
                    int quantity = 0;
                    for (InventoriesItem inventory: item.getInventories()) {
                        quantity = quantity + inventory.getQuantity();
                    }
                    cartItem.setQuantity(String.valueOf(quantity));
                    cartItem.setPath("http://172.22.224.1:8081/api/v1/mattresses/"+cartItem.getId()+"/images");
                    cartItems.add(cartItem);
                    ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
                    layoutParams.height = (cartItems.size())*885;
                    layoutParams.width = 1067;
                    gridView.setLayoutParams(layoutParams);
                    adminProductAdapter = new AdminProductAdapter(
                            AdminProductActivity.this,
                            cartItems,
                            R.layout.admin_item_product
                    );
                    gridView.setAdapter(adminProductAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<ApiLatestProductResponseItem>> call, Throwable t) {

            }
        });
    }
}