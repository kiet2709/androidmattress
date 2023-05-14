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
import com.nhom4nguoi.ecommerce.adapter.OrderCompletedAdminAdapter;
import com.nhom4nguoi.ecommerce.adapter.OrderInProgressAdminAdapter;
import com.nhom4nguoi.ecommerce.api.ApiClient;
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

public class AdminOrderCompleted extends AppCompatActivity {
    GridView gridView;
    OrderCompletedAdminAdapter orderCompletedAdminAdapter;
    Button btnConfirm, btnInProgress, btnCompleted;
    ImageButton btnHomeAdmin, btnProductAdmin, btnAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_completed);
        initViews();
        getOrders();
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminOrderCompleted.this, AdminActivity.class);
                startActivity(intent);
            }
        });
        btnInProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminOrderCompleted.this, AdminOrderInProgressActivity.class);
                startActivity(intent);
            }
        });
        btnCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminOrderCompleted.this, AdminOrderCompleted.class);
                startActivity(intent);
            }
        });
        btnHomeAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminOrderCompleted.this, AdminActivity.class);
                startActivity(intent);
            }
        });
        btnProductAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminOrderCompleted.this, AdminProductActivity.class);
                startActivity(intent);
            }
        });
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminOrderCompleted.this, AdminSignOutAcitivity.class);
                startActivity(intent);
            }
        });

    }
    private void initViews() {
        gridView = (GridView) findViewById(R.id.gridview_orderCompletedAdmin);
        btnConfirm = (Button) findViewById(R.id.btnConfirmOrder);
        btnInProgress = (Button) findViewById(R.id.btnInProgressOrder);
        btnCompleted = (Button) findViewById(R.id.btnCompletedOrder);
        btnHomeAdmin = (ImageButton) findViewById(R.id.btnHomeAdmin);
        btnProductAdmin = (ImageButton) findViewById(R.id.btnProductAdmin);
        btnAdmin = (ImageButton) findViewById(R.id.btnAdmin);
        btnAdmin.setBackgroundTintList(ColorStateList.valueOf(0xFFCC6600));

    }
    private void getOrders() {
        OrderApi orderApi;
        Retrofit retrofit = ApiClient.getClient();
        orderApi = retrofit.create(OrderApi.class);
        List<Order> orders = new ArrayList<>();
        orderApi.getAllOrders(SharedPrefManager.getInstance(this).getJWT()).enqueue(new Callback<List<CurrentOrderResponseItem>>() {
            @Override
            public void onResponse(Call<List<CurrentOrderResponseItem>> call, Response<List<CurrentOrderResponseItem>> response) {
                Log.d("kiet", "onResponse: da vao order response");
                List<CurrentOrderResponseItem> orderList = response.body();
                for (CurrentOrderResponseItem item: orderList) {
                    if (item.getOrderTrack().getStatus().equals("Completed")){
                        Order order = new Order();
                        order.setId(item.getId());
                        order.setOrderTrackId(3);
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
                    layoutParams.height = (orders.size())*900;
                    layoutParams.width = 1067;
                    gridView.setLayoutParams(layoutParams);
                    orderCompletedAdminAdapter = new OrderCompletedAdminAdapter(
                            AdminOrderCompleted.this,
                            orders,
                            R.layout.item_order_admin
                    );
                    gridView.setAdapter(orderCompletedAdminAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<CurrentOrderResponseItem>> call, Throwable t) {

            }
        });
    }
}