package com.nhom4nguoi.ecommerce.response.APIGetLatestProduct;

import java.util.List;

public final class ApiLatestProductResponseStatic {
    private static List<ApiLatestProductResponseItem> apiLatestProductResponse;

    public static List<ApiLatestProductResponseItem> getApiLatestProductResponse(){
        return apiLatestProductResponse;
    }

    public static void setApiLatestProductResponse(List<ApiLatestProductResponseItem> apiLatestProductResponse) {
        apiLatestProductResponse = apiLatestProductResponse;
    }
}
