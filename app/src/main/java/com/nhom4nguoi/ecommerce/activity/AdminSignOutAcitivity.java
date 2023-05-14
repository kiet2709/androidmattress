package com.nhom4nguoi.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.util.SharedPrefManager;

public class AdminSignOutAcitivity extends AppCompatActivity {
    ImageButton btnHomeAdmin, btnProductAdmin, btnAdmin;
    Button btnSignout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_out_acitivity);
        initViews();
        btnHomeAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminSignOutAcitivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
        btnProductAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminSignOutAcitivity.this, AdminProductActivity.class);
                startActivity(intent);
            }
        });
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminSignOutAcitivity.this, AdminSignOutAcitivity.class);
                startActivity(intent);
            }
        });
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(AdminSignOutAcitivity.this).storeJWT("");
                Intent intent = new Intent(AdminSignOutAcitivity.this, LoginActivity.class);
                startActivity(intent);
            };
        });
    }
    void initViews(){
        btnHomeAdmin = (ImageButton) findViewById(R.id.btnHomeAdmin);
        btnProductAdmin = (ImageButton) findViewById(R.id.btnProductAdmin);
        btnAdmin = (ImageButton) findViewById(R.id.btnAdmin);
        btnAdmin.setBackgroundTintList(ColorStateList.valueOf(0xFFCC6600));
        btnSignout = (Button) findViewById(R.id.btn_signoutAdmin);
    }
}