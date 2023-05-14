package com.nhom4nguoi.ecommerce.api;

import com.nhom4nguoi.ecommerce.response.APICurrentOrder.CurrentOrderResponseItem;
import com.nhom4nguoi.ecommerce.response.APIOrder.OrderRequest;
import com.nhom4nguoi.ecommerce.response.APIOrder.OrderResponse;
import com.nhom4nguoi.ecommerce.response.OrderStatusRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface OrderApi {

    @POST("orders/current/submit")
    Call<OrderResponse> submitOrder(@Header("Authorization") String jwt, @Body OrderRequest orderRequest);
    @GET("orders/current")
    Call<List<CurrentOrderResponseItem>> getCurrentOrder(@Header("Authorization") String jwt);
    @GET("orders")
    Call<List<CurrentOrderResponseItem>> getAllOrders(@Header("Authorization") String jwt);
    @PUT("orders/update")
    Call<CurrentOrderResponseItem> updateOrder(@Header("Authorization") String jwt, @Body OrderStatusRequest orderStatusRequest);
}
