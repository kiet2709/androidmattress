package com.nhom4nguoi.ecommerce.api;

import com.nhom4nguoi.ecommerce.response.ProfileRequest;
import com.nhom4nguoi.ecommerce.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;

public interface UserApi {
    @GET("users/current")
    Call<UserResponse> getCurrentUser(@Header("Authorization") String jwt);

    @PATCH("users/current/profile")
    @Headers({ "Content-Type: application/json;"})
    Call<UserResponse> updateProfile(@Header("Authorization") String jwt, @Body ProfileRequest profileRequest);
}
