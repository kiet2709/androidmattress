package com.nhom4nguoi.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.adapter.PersonalAdapter;

import java.util.ArrayList;
import java.util.List;

public class PersonalActivity extends AppCompatActivity {

    GridView gridView;
    PersonalAdapter personalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        gridView = (GridView) findViewById(R.id.gridview_personal);
        personalAdapter = new PersonalAdapter(
                PersonalActivity.this,
                getMenu(),
                R.layout.item_personal
        );
        gridView.setAdapter(personalAdapter);
    }

    private List<String> getMenu() {
        List<String> menus = new ArrayList<>();
        menus.add("Profile");
        menus.add("Order");
        menus.add("FAQ");
        menus.add("Sign out");
        return menus;
    }
}