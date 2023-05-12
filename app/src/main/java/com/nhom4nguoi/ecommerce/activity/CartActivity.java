package com.nhom4nguoi.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.GridView;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.adapter.CartAdapter;
import com.nhom4nguoi.ecommerce.model.CartItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    GridView gridView;
    CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        gridView = (GridView) findViewById(R.id.gridview_cart);
        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.height = (getCartItem().size())*485;
        layoutParams.width = 1067;
        gridView.setLayoutParams(layoutParams);
        cartAdapter = new CartAdapter(
                CartActivity.this,
                getCartItem(),
                R.layout.item_cart
        );
        gridView.setAdapter(cartAdapter);
        Log.d("kiet", "onCreate: " + getCartItem().get(0).getResourceId());
    }

    private List<CartItem> getCartItem() {
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem("item 1", "100", R.drawable.product1, "1"));
        cartItems.add(new CartItem("item 2", "150", R.drawable.product2, "2"));
        cartItems.add(new CartItem("item 3", "50", R.drawable.product3, "3"));
        cartItems.add(new CartItem("item 4", "70", R.drawable.product4, "4"));
        cartItems.add(new CartItem("item 5", "120", R.drawable.product1, "5"));
        return cartItems;
    }
}