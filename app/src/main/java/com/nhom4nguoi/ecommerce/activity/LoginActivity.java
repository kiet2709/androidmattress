package com.nhom4nguoi.ecommerce.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom4nguoi.ecommerce.api.ApiClient;
import com.nhom4nguoi.ecommerce.api.AuthApi;
import com.nhom4nguoi.ecommerce.response.SignInResponse;
import com.nhom4nguoi.ecommerce.util.SharedPrefManager;
import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.model.User;

import java.util.Arrays;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    AuthApi authApi;
    Retrofit retrofit = ApiClient.getClient();
    TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initViews();
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLogin();
            }
        });
    }

    private void clickLogin() {
        User user = new User(edtUsername.getText().toString(),edtPassword.getText().toString());
        authApi = retrofit.create(AuthApi.class);
        authApi.login(user).enqueue(new Callback<SignInResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                SignInResponse signInResponse = response.body();
                SharedPrefManager.getInstance(LoginActivity.this).storeJWT(signInResponse.getToken());
                //author
                String jwt = signInResponse.getToken().replace("Bearer ", "");
                String[] jwtSplit = signInResponse.getToken().split("\\.");
                byte[] decodedBytes = Base64.getDecoder().decode(jwtSplit[1]);
                String role = new String(decodedBytes);
                if (response.code()==403){
                    Toast.makeText(LoginActivity.this, "You are blocked", Toast.LENGTH_LONG).show();
                } else if (role.contains("ROLE_ADMIN")){
                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                    startActivity(intent);
                } else if (role.contains("ROLE_USER")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "There're something wrong", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Log.d("kiet", "onFailure: Error on Login API" );

            }
        });

    }

    private void initViews() {
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtRegister = (TextView) findViewById(R.id.txt_register);
    }


}