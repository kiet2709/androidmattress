package com.nhom4nguoi.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.GridView;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.adapter.OrderAdapter;
import com.nhom4nguoi.ecommerce.adapter.OrderAdapterInProgress;
import com.nhom4nguoi.ecommerce.model.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderInProgressActivity extends AppCompatActivity {

    GridView gridView;
    OrderAdapterInProgress orderAdapterInProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_in_progress);
        gridView = (GridView) findViewById(R.id.gridview_orderInProgress);
        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.height = (getOrders().size())*450;
        layoutParams.width = 1067;
        gridView.setLayoutParams(layoutParams);
        orderAdapterInProgress = new OrderAdapterInProgress(
                OrderInProgressActivity.this,
                getOrders(),
                R.layout.item_order
        );
        gridView.setAdapter(orderAdapterInProgress);
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