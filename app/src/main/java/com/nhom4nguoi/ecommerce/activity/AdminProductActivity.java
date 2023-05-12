package com.nhom4nguoi.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.adapter.AdminProductAdapter;
import com.nhom4nguoi.ecommerce.adapter.CartAdapter;
import com.nhom4nguoi.ecommerce.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class AdminProductActivity extends AppCompatActivity {
    Spinner spinner;
    GridView gridView;
    AdminProductAdapter adminProductAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);
        spinner = (Spinner) findViewById(R.id.spinnerAdmin);
        String[] sizes = {"Ahsdghdsgh", "Bhzdfb", "Czbfbf"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item_2, sizes);
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
        gridView = (GridView) findViewById(R.id.gridview_productAdmin);
        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.height = (getCartItem().size())*485;
        layoutParams.width = 1067;
        gridView.setLayoutParams(layoutParams);
        adminProductAdapter = new AdminProductAdapter(
                AdminProductActivity.this,
                getCartItem(),
                R.layout.admin_item_product
        );
        gridView.setAdapter(adminProductAdapter);
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