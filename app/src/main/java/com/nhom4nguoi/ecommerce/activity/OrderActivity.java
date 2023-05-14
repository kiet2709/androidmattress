package com.nhom4nguoi.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.adapter.OrderAdapter;
import com.nhom4nguoi.ecommerce.api.ApiClient;
import com.nhom4nguoi.ecommerce.api.CartApi;
import com.nhom4nguoi.ecommerce.api.OrderApi;
import com.nhom4nguoi.ecommerce.model.Order;
import com.nhom4nguoi.ecommerce.response.APICurrentOrder.CurrentOrderResponseItem;
import com.nhom4nguoi.ecommerce.util.SharedPrefManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderActivity extends AppCompatActivity {

    GridView gridView;
    OrderAdapter orderAdapter;
    Button btnConfirm, btnInProgress, btnCompleted;
    ImageButton homeButton, categoryButton, userButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initViews();
        getOrders();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
        btnInProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, OrderInProgressActivity.class);
                startActivity(intent);
            }
        });
        btnCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, OrderCompletedActivity.class);
                startActivity(intent);
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(OrderActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(OrderActivity.this, PersonalActivity.class);
                startActivity(intent);
            }
        });
        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(OrderActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        gridView = (GridView) findViewById(R.id.gridview_orderConfirm);
        btnConfirm = (Button) findViewById(R.id.btnConfirmOrder);
        btnInProgress = (Button) findViewById(R.id.btnInProgressOrder);
        btnCompleted = (Button) findViewById(R.id.btnCompletedOrder);
        homeButton = (ImageButton) findViewById(R.id.btn_home);
        categoryButton = (ImageButton) findViewById(R.id.btn_category);
        userButton = (ImageButton) findViewById(R.id.btn_user);
    }

    private void getOrders() {
        OrderApi orderApi;
        Retrofit retrofit = ApiClient.getClient();
        orderApi = retrofit.create(OrderApi.class);
        List<Order> orders = new ArrayList<>();

        orderApi.getCurrentOrder(SharedPrefManager.getInstance(this).getJWT()).enqueue(new Callback<List<CurrentOrderResponseItem>>() {
            @Override
            public void onResponse(Call<List<CurrentOrderResponseItem>> call, Response<List<CurrentOrderResponseItem>> response) {
                Log.d("kiet", "onResponse: da vao order response");
                List<CurrentOrderResponseItem> orderList = response.body();
                for (CurrentOrderResponseItem item: orderList) {
                    if (item.getOrderTrack().getStatus().equals("In preparing")){
                        Order order = new Order();
                        String date = item.getOrderDate().substring(0, 9);
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            order.setOrderDate(df.parse(date));
                        } catch (ParseException e) {
//                        throw new RuntimeException(e);
                            Log.d("kiet", "onResponse: cannot parse");
                            order.setOrderDate(new Date());
                        };
                        Double price = (Double) item.getTotalProductPrice();
                        order.setSum(String.valueOf(price.intValue()) );
                        orders.add(order);
                    }
                    ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
                    if (orders.size()<2){
                        layoutParams.height = (orders.size())*1950;
                    } else {
                        layoutParams.height = (orders.size())*1050;
                    }
                    layoutParams.width = 1067;
                    gridView.setLayoutParams(layoutParams);
                    orderAdapter = new OrderAdapter(
                            OrderActivity.this,
                            orders,
                            R.layout.item_order
                    );
                    gridView.setAdapter(orderAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<CurrentOrderResponseItem>> call, Throwable t) {

            }
        });
    }

}