package com.nhom4nguoi.ecommerce.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.adapter.CartAdapter;
import com.nhom4nguoi.ecommerce.api.ApiClient;
import com.nhom4nguoi.ecommerce.api.CartApi;
import com.nhom4nguoi.ecommerce.api.OrderApi;
import com.nhom4nguoi.ecommerce.model.CartItem;
import com.nhom4nguoi.ecommerce.response.APIOrder.OrderRequest;
import com.nhom4nguoi.ecommerce.response.APIOrder.OrderResponse;
import com.nhom4nguoi.ecommerce.response.ApiCart.CartItemsItem;
import com.nhom4nguoi.ecommerce.response.ApiCart.CartResponse;
import com.nhom4nguoi.ecommerce.util.SharedPrefManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartActivity extends AppCompatActivity {

    GridView gridView;
    CartAdapter cartAdapter;
    TextView txtSumCart;
    Button btnBuy;
    ImageButton homeButton, categoryButton, userButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initViews();
        getCartItem();
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                purchase();
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(CartActivity.this, PersonalActivity.class);
                startActivity(intent);
            }
        });
        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(CartActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void purchase() {
        OrderApi orderApi;
        Retrofit retrofit = ApiClient.getClient();
        orderApi = retrofit.create(OrderApi.class);
        OrderRequest orderRequest = new OrderRequest();
        LocalDate date = LocalDate.now();
        orderRequest.setOrderDate(date.toString());
        orderRequest.setDeliverMethod("Fast");
        List<Integer> cartIds = new ArrayList<>();
        for (CartItem item: cartAdapter.getCartItems()) {
            cartIds.add(item.getId());
        }
        orderRequest.setCartItemId(cartIds);
        orderApi.submitOrder(SharedPrefManager.getInstance(this).getJWT(), orderRequest).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                Toast.makeText(CartActivity.this, "Purchase Successfully!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {

            }
        });
    }

    private void initViews() {
        gridView = (GridView) findViewById(R.id.gridview_cart);
        txtSumCart = (TextView) findViewById(R.id.txt_sumCart);
        btnBuy = (Button) findViewById(R.id.btn_buy);
        homeButton = (ImageButton) findViewById(R.id.btn_home);
        categoryButton = (ImageButton) findViewById(R.id.btn_category);
        userButton = (ImageButton) findViewById(R.id.btn_user);
    }

    private void getCartItem() {
        String jwt = SharedPrefManager.getInstance(this).getJWT();
        CartApi cartApi;
        Retrofit retrofit = ApiClient.getClient();
        cartApi = retrofit.create(CartApi.class);
        List<CartItem> cartItems = new ArrayList<>();
        cartApi.getCurrentCart(jwt).enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                CartResponse cartResponse = response.body();
                int sum = 0;
                for (CartItemsItem cartItem: cartResponse.getCartItems()) {
                    Double price = (Double) cartItem.getMattress().getPrice();
                    CartItem cartItem1 = new CartItem(
                            cartItem.getMattress().getName(),
                            String.valueOf(price.intValue()),
                            cartItem.getId(),
                            String.valueOf(cartItem.getQuantity()),
                            cartItem.getChoiceSize().getName());
                    cartItem1.setSizeNumber(cartItem.getChoiceSize().getId());
                    int productId = cartItem.getMattress().getId();
                    cartItem1.setPath("http://172.22.224.1:8081/api/v1/mattresses/"+productId+"/images");
                    cartItems.add(cartItem1);
                    Double totalPrice = (Double) cartItem.getTotalPrice();
                    sum = sum + totalPrice.intValue();
                }
                ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
                if (cartItems.size()==0){
                    layoutParams.height = 1360;
                } else if (cartItems.size()<4){
                    layoutParams.height = (cartItems.size())*985;
                } else {
                    layoutParams.height = (cartItems.size())*685;
                }
                layoutParams.width = 1267;
                gridView.setLayoutParams(layoutParams);
                cartAdapter = new CartAdapter(
                        CartActivity.this,
                        cartItems,
                        R.layout.item_cart
                );
                gridView.setAdapter(cartAdapter);
                txtSumCart.setText(String.valueOf(sum));
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {

            }
        });
    }
}