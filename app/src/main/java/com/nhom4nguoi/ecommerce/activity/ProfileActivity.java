package com.nhom4nguoi.ecommerce.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.api.ApiClient;
import com.nhom4nguoi.ecommerce.api.CartApi;
import com.nhom4nguoi.ecommerce.api.UserApi;
import com.nhom4nguoi.ecommerce.response.ProfileRequest;
import com.nhom4nguoi.ecommerce.response.UserResponse;
import com.nhom4nguoi.ecommerce.util.SharedPrefManager;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProfileActivity extends AppCompatActivity {

    EditText  txtFirstname, txtLastname, txtBirthday, txtPhoneNumber, txtAddress;
    ImageView avatar;
    Button save;
    TextView txtUsername;
    ImageButton homeButton, categoryButton, userButton;
    private final int GALERY_REQ_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initViews();
        loadUser();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(ProfileActivity.this, PersonalActivity.class);
                startActivity(intent);
            }
        });
        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryButton.setBackgroundTintList(ColorStateList.valueOf(0xFF15181D));
                Intent intent = new Intent(ProfileActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });

    }


    private void updateProfile() {
        UserApi userApi;
        Retrofit retrofit = ApiClient.getClient();
        userApi = retrofit.create(UserApi.class);
        ProfileRequest profileRequest = new ProfileRequest();
        profileRequest.setAddress(txtAddress.getText().toString());
        profileRequest.setBirthday(txtBirthday.getText().toString());
        profileRequest.setFirstName(txtFirstname.getText().toString());
        profileRequest.setImage(null);
        profileRequest.setLastName(txtLastname.getText().toString());
        profileRequest.setPhoneNumber(txtPhoneNumber.getText().toString());
        userApi.updateProfile(SharedPrefManager.getInstance(this).getJWT(), profileRequest).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Log.d("kiet", "onResponse: " + response.code()+ " "+ profileRequest);
                Toast.makeText(ProfileActivity.this, "Update successfully!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }

    private void initViews() {
        txtUsername = (TextView) findViewById(R.id.txt_username);
        txtFirstname = (EditText) findViewById(R.id.txt_firstname);
        txtLastname = (EditText) findViewById(R.id.txt_lastname);
        txtBirthday = (EditText) findViewById(R.id.txt_birthday);
        txtPhoneNumber = (EditText) findViewById(R.id.txt_phoneNumber);
        txtAddress = (EditText) findViewById(R.id.txt_address);
        avatar = (ImageView) findViewById(R.id.imageProfile);
        save = (Button) findViewById(R.id.btn_updateProfile);
        homeButton = (ImageButton) findViewById(R.id.btn_home);
        categoryButton = (ImageButton) findViewById(R.id.btn_category);
        userButton = (ImageButton) findViewById(R.id.btn_user);
    }

    void loadUser(){
        UserApi userApi;
        Retrofit retrofit = ApiClient.getClient();
        userApi = retrofit.create(UserApi.class);
        userApi.getCurrentUser(SharedPrefManager.getInstance(this).getJWT()).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                txtUsername.setText(userResponse.getUsername());
                txtFirstname.setText(userResponse.getFirstName());
                txtLastname.setText(userResponse.getLastName());
                txtBirthday.setText(userResponse.getBirthday());
                txtAddress.setText(userResponse.getAddress());
                txtPhoneNumber.setText(userResponse.getPhoneNumber());
                Glide.with(ProfileActivity.this).load("http://172.22.224.1:8081/api/v1/users/current/images").into(avatar);
                Log.d("kiet", "onResponse: " + userResponse);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }
}