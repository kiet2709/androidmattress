package com.nhom4nguoi.ecommerce.presenterImpl;

import android.content.Intent;
import android.util.Log;

import com.nhom4nguoi.ecommerce.activity.LoginActivity;
import com.nhom4nguoi.ecommerce.activity.MainActivity;
import com.nhom4nguoi.ecommerce.api.ApiClient;
import com.nhom4nguoi.ecommerce.api.AuthApi;
import com.nhom4nguoi.ecommerce.model.User;
import com.nhom4nguoi.ecommerce.presenter.LoginPresenter;
import com.nhom4nguoi.ecommerce.response.SignInResponse;
import com.nhom4nguoi.ecommerce.view.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginPresenterImpl implements LoginPresenter {
    LoginView loginView;
    AuthApi authApi;
    Retrofit retrofit = ApiClient.getClient();

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(User user) {
        authApi = retrofit.create(AuthApi.class);
        authApi.login(user).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (response.isSuccessful()){
                    SignInResponse signInResponse = response.body();
                    loginView.onComplete(signInResponse.getToken());
                } else {
                    loginView.onError();
                }
            }

            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Log.d("kiet", "onFailure: Error on Login API" );
            }
        });

    }
}
