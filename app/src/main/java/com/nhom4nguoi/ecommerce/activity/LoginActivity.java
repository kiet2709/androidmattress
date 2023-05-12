package com.nhom4nguoi.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nhom4nguoi.ecommerce.presenter.LoginPresenter;
import com.nhom4nguoi.ecommerce.presenterImpl.LoginPresenterImpl;
import com.nhom4nguoi.ecommerce.util.SharedPrefManager;
import com.nhom4nguoi.ecommerce.view.LoginView;
import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.model.User;

public class LoginActivity extends AppCompatActivity implements LoginView {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initViews();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickLogin();
            }
        });
    }

    private void clickLogin() {
        User user = new User(edtUsername.getText().toString(),edtPassword.getText().toString());
        loginPresenter.login(user);
    }

    private void initViews() {
        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        loginPresenter = new LoginPresenterImpl(this);
    }

    @Override
    public void onComplete(String jwt) {
        SharedPrefManager.getInstance(this).storeJWT(jwt);
        Log.d("kiet", "onComplete: " + SharedPrefManager.getInstance(this).getJWT());
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onError() {
        Toast.makeText(LoginActivity.this, "Login fail, please check your username and password again!", Toast.LENGTH_LONG).show();
    }
}