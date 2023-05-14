package com.nhom4nguoi.ecommerce.api;

import com.nhom4nguoi.ecommerce.model.User;
import com.nhom4nguoi.ecommerce.response.Register.RegisterRequest;
import com.nhom4nguoi.ecommerce.response.Register.RegisterResponse;
import com.nhom4nguoi.ecommerce.response.SignInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("auth/signin")
    Call<SignInResponse> login(@Body User user);
    @POST("auth/register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);
}
