package com.nhom4nguoi.ecommerce.api;

import com.nhom4nguoi.ecommerce.response.Size.SizeResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SizeApi {

    @GET("sizes")
    Call<List<SizeResponseItem>> getAllSize();
}
