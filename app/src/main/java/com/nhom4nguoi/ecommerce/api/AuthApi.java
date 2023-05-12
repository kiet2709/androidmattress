package com.nhom4nguoi.ecommerce.api;

import com.nhom4nguoi.ecommerce.model.User;
import com.nhom4nguoi.ecommerce.response.SignInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("auth/signin")
    Call<SignInResponse> login(@Body User user);
}
