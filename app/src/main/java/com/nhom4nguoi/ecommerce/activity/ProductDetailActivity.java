package com.nhom4nguoi.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.api.ApiClient;
import com.nhom4nguoi.ecommerce.api.CartApi;
import com.nhom4nguoi.ecommerce.api.ProductApi;
import com.nhom4nguoi.ecommerce.model.Product;
import com.nhom4nguoi.ecommerce.model.Size;
import com.nhom4nguoi.ecommerce.response.APIProductDetail.Image;
import com.nhom4nguoi.ecommerce.response.APIProductDetail.InventoriesItem;
import com.nhom4nguoi.ecommerce.response.APIProductDetail.ProductResponse;
import com.nhom4nguoi.ecommerce.response.ApiCart.CartResponse;
import com.nhom4nguoi.ecommerce.response.CartRequest;
import com.nhom4nguoi.ecommerce.util.SharedPrefManager;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductDetailActivity extends AppCompatActivity {

    Spinner spinner;
    ImageView image;
    TextView txtPrice, txtDescription;
    Button btnAddToCart;
    Hashtable<String, Integer> sizeIdName;
    Product product;
    ImageButton homeButton, categoryButton, userButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        Intent intent = getIntent();
        product = (Product) intent.getSerializableExtra("product");
        initViews();
        Glide.with(ProductDetailActivity.this).load(product.getPath()).into(image);
        txtPrice.setText(product.getPrice());
        txtDescription.setText(product.getDescription());
        List<Size> size = (List<Size>) intent.getSerializableExtra("sizes");
        sizeIdName = new Hashtable<String, Integer>();
        List<String> sizeName = new ArrayList<>();
        for (Size sizeItem: size) {
            sizeName.add(sizeItem.getName());
            sizeIdName.put(sizeItem.getName(), sizeItem.getId());
        }
        String[] sizes = sizeName.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, sizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                view.setBackgroundColor(0xFFFFFFFF);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(ProductDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(ProductDetailActivity.this, PersonalActivity.class);
                startActivity(intent);
            }
        });
        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(ProductDetailActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addToCart() {
        int sizeId = sizeIdName.get(spinner.getSelectedItem().toString());
        int quantity = 1;
        int mattressId = product.getId();
        String jwt = SharedPrefManager.getInstance(this).getJWT();
        CartApi cartApi;
        Retrofit retrofit = ApiClient.getClient();
        cartApi = retrofit.create(CartApi.class);
        CartRequest cartRequest = new CartRequest(quantity, mattressId, String.valueOf(sizeId));
        cartApi.addToCart(jwt, cartRequest).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                Toast.makeText(ProductDetailActivity.this, "Add to cart successfully!" + jwt, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Log.d("kiet", "onFailure: Error in api add to cart");
                Toast.makeText(ProductDetailActivity.this, "Add to cart failure, try again!", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initViews() {
        spinner = (Spinner) findViewById(R.id.spinnerProduct);
        image = (ImageView) findViewById(R.id.img_productDetail) ;
        txtPrice = (TextView) findViewById(R.id.txt_priceDetail);
        txtDescription = (TextView) findViewById(R.id.txt_descriptionDetail);
        btnAddToCart = (Button) findViewById(R.id.btn_addToCart);
        homeButton = (ImageButton) findViewById(R.id.btn_home);
        categoryButton = (ImageButton) findViewById(R.id.btn_category);
        userButton = (ImageButton) findViewById(R.id.btn_user);
    }
}