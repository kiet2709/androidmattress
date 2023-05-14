package com.nhom4nguoi.ecommerce.api;

import com.nhom4nguoi.ecommerce.activity.CategoryActivity;
import com.nhom4nguoi.ecommerce.response.APICategory.CategoryItem;
import com.nhom4nguoi.ecommerce.response.APIGetLatestProduct.ApiLatestProductResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoryApi {

    @GET("categories")
    Call<List<CategoryItem>> getAllCategory();

    @GET("categories/{id}/mattresses")
    Call<List<ApiLatestProductResponseItem>> getProductByCategoryId(@Path("id") int id);
}
