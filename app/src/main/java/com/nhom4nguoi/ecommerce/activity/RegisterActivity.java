package com.nhom4nguoi.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.api.ApiClient;
import com.nhom4nguoi.ecommerce.api.AuthApi;
import com.nhom4nguoi.ecommerce.response.Register.RegisterRequest;
import com.nhom4nguoi.ecommerce.response.Register.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUsername, edtEmail, edtPassword, edtFirstName, edtLastName, edtPhone, edtBirthday, edtAddress;
    Button btnRegister;
    TextView txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        initViews();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        AuthApi authApi;
        Retrofit retrofit = ApiClient.getClient();
        authApi = retrofit.create(AuthApi.class);
        RegisterRequest registerRequest = new RegisterRequest(
                edtBirthday.getText().toString(),
                edtFirstName.getText().toString(),
                edtLastName.getText().toString(),
                edtPassword.getText().toString(),
                edtPhone.getText().toString(),
                edtAddress.getText().toString(),
                edtEmail.getText().toString(),
                true,
                edtUsername.getText().toString()
        );
        authApi.register(registerRequest).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                Toast.makeText(RegisterActivity.this, "Register successfully!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Register fail!", Toast.LENGTH_LONG).show();
            }
        });
    }

    void initViews(){
        edtUsername = (EditText) findViewById(R.id.edt_RUsername);
        edtEmail = (EditText) findViewById(R.id.edt_REmail);
        edtPassword = (EditText) findViewById(R.id.edt_RPassword);
        edtFirstName = (EditText) findViewById(R.id.edt_RFirstName);
        edtLastName = (EditText) findViewById(R.id.edt_RLastName);
        edtPhone = (EditText) findViewById(R.id.edt_RBirthday);
        edtBirthday = (EditText) findViewById(R.id.edt_RBirthday);
        edtAddress = (EditText) findViewById(R.id.edt_RAddress);
        btnRegister = (Button) findViewById(R.id.btn_RRegister);
        txtLogin = (TextView) findViewById(R.id.txt_RLogin);
    }
}