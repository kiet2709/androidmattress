package com.nhom4nguoi.ecommerce.api;

import com.nhom4nguoi.ecommerce.response.ApiCart.CartResponse;
import com.nhom4nguoi.ecommerce.response.ApiResponseDelete;
import com.nhom4nguoi.ecommerce.response.CartRequest;
import com.nhom4nguoi.ecommerce.response.CartUpdateRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartApi {

    @POST("carts/current/items/add")
    Call<CartResponse> addToCart(@Header("Authorization") String jwt, @Body CartRequest cartRequest);
    @GET("carts/current/items")
    Call<CartResponse> getCurrentCart(@Header("Authorization") String jwt);

    @PATCH("carts/current/items/{id}")
    @Headers({ "Content-Type: application/json;"})
    Call<CartResponse> updateCart(@Header("Authorization") String jwt, @Path("id") int id, @Body CartUpdateRequest cartUpdateRequest);
    @DELETE("carts/current/items/{id}")
    Call<ApiResponseDelete> deleteCartItem(@Header("Authorization") String jwt, @Path("id") int id);

}
