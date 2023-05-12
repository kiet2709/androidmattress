package com.nhom4nguoi.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.GridView;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.adapter.OrderCompletedAdminAdapter;
import com.nhom4nguoi.ecommerce.adapter.OrderInProgressAdminAdapter;
import com.nhom4nguoi.ecommerce.model.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminOrderCompleted extends AppCompatActivity {
    GridView gridView;
    OrderCompletedAdminAdapter orderCompletedAdminAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_completed);
        gridView = (GridView) findViewById(R.id.gridview_orderCompletedAdmin);
        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.height = (getOrders().size())*500;
        layoutParams.width = 1067;
        gridView.setLayoutParams(layoutParams);
        orderCompletedAdminAdapter = new OrderCompletedAdminAdapter(
                AdminOrderCompleted.this,
                getOrders(),
                R.layout.item_order_admin
        );
        gridView.setAdapter(orderCompletedAdminAdapter);
    }
    private List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(new Date(), "100"));
        orders.add(new Order(new Date(), "120"));
        orders.add(new Order(new Date(), "20"));
        orders.add(new Order(new Date(), "300"));
        return orders;
    }
}