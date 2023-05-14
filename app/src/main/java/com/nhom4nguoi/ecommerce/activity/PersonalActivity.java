package com.nhom4nguoi.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.adapter.PersonalAdapter;

import java.util.ArrayList;
import java.util.List;

public class PersonalActivity extends AppCompatActivity {

    GridView gridView;
    PersonalAdapter personalAdapter;
    ImageButton homeButton, categoryButton, userButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        initViews();
        personalAdapter = new PersonalAdapter(
                PersonalActivity.this,
                getMenu(),
                R.layout.item_personal
        );
        gridView.setAdapter(personalAdapter);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(PersonalActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(PersonalActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(PersonalActivity.this, PersonalActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        gridView = (GridView) findViewById(R.id.gridview_personal);
        homeButton = (ImageButton) findViewById(R.id.btn_home);
        categoryButton = (ImageButton) findViewById(R.id.btn_category);
        userButton = (ImageButton) findViewById(R.id.btn_user);
        userButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
    }

    private List<String> getMenu() {
        List<String> menus = new ArrayList<>();
        menus.add("Profile");
        menus.add("Order");
        menus.add("Sign out");
        return menus;
    }
}