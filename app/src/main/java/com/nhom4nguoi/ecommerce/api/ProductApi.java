package com.nhom4nguoi.ecommerce.api;

import com.nhom4nguoi.ecommerce.model.User;
import com.nhom4nguoi.ecommerce.response.APIGetLatestProduct.ApiLatestProductResponseItem;
import com.nhom4nguoi.ecommerce.response.APIProductDetail.ProductResponse;
import com.nhom4nguoi.ecommerce.response.SignInResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductApi {

    @GET("mattresses/latest")
    Call<List<ApiLatestProductResponseItem>> getLatestProduct();
    @GET("mattresses/best-seller")
    Call<List<ApiLatestProductResponseItem>> getBestSellerProduct();
    @GET("mattresses")
    Call<List<ApiLatestProductResponseItem>> getAllProduct();
    @GET("mattresses/search")
    Call<List<ApiLatestProductResponseItem>> searchProduct(@Query("query") String query);
    @GET("mattresses/{id}")
    Call<ProductResponse> getProductById(@Path("id") int id);
}
