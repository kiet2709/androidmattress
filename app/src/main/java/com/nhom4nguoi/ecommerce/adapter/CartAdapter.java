package com.nhom4nguoi.ecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.activity.CartActivity;
import com.nhom4nguoi.ecommerce.activity.ProductDetailActivity;
import com.nhom4nguoi.ecommerce.api.ApiClient;
import com.nhom4nguoi.ecommerce.api.CartApi;
import com.nhom4nguoi.ecommerce.api.ProductApi;
import com.nhom4nguoi.ecommerce.model.CartItem;
import com.nhom4nguoi.ecommerce.model.Category;
import com.nhom4nguoi.ecommerce.model.Cloth;
import com.nhom4nguoi.ecommerce.model.Product;
import com.nhom4nguoi.ecommerce.model.Size;
import com.nhom4nguoi.ecommerce.response.APIGetLatestProduct.ApiLatestProductResponse;
import com.nhom4nguoi.ecommerce.response.APIProductDetail.InventoriesItem;
import com.nhom4nguoi.ecommerce.response.APIProductDetail.ProductResponse;
import com.nhom4nguoi.ecommerce.response.ApiCart.CartResponse;
import com.nhom4nguoi.ecommerce.response.ApiResponseDelete;
import com.nhom4nguoi.ecommerce.response.CartUpdateRequest;
import com.nhom4nguoi.ecommerce.util.SharedPrefManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private List<CartItem> cartItems;
    private int layout;

    public CartAdapter(Context context, List<CartItem> cartItems, int layout) {
        this.context = context;
        this.cartItems = cartItems;
        this.layout = layout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public CartAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return cartItems==null? 0 :cartItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CartAdapter.ViewHolder viewHolder;

        if (view==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout,null);

            viewHolder = new CartAdapter.ViewHolder();
            viewHolder.textName = (TextView) view.findViewById(R.id.txt_cartItemName);
            viewHolder.imagePic = (ImageView) view.findViewById(R.id.img_cart);
            viewHolder.textPrice = (TextView) view.findViewById(R.id.txt_cartItemPrice);
            viewHolder.quantity = (TextView) view.findViewById(R.id.txt_cartItemQuantity);
            viewHolder.minus = (Button) view.findViewById(R.id.btnMinus);
            viewHolder.plus = (Button) view.findViewById(R.id.btnPlus);
            viewHolder.delBtn = (ImageButton) view.findViewById(R.id.btnDel);
            viewHolder.txtSize = (TextView) view.findViewById(R.id.txtSize);
            viewHolder.txtSizeValue = (TextView) view.findViewById(R.id.txtSizeValue);
            view.setTag(viewHolder);
        }else{
            viewHolder= (CartAdapter.ViewHolder) view.getTag();
        }
        CartItem cartItem = cartItems.get(i);
        viewHolder.textName.setText(cartItem.getName());
        Glide.with(context).load(cartItem.getPath()).into(viewHolder.imagePic);
        viewHolder.textPrice.setText(cartItem.getPrice());
        viewHolder.quantity.setText(cartItem.getQuantity());
        viewHolder.txtSizeValue.setText(cartItem.getSize());
        viewHolder.textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProductDetail(cartItem.getId());
            }
        });
        viewHolder.textPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProductDetail(cartItem.getId());
            }
        });
        viewHolder.imagePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadProductDetail(cartItem.getId());
            }
        });
        viewHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartApi cartApi;
                Retrofit retrofit = ApiClient.getClient();
                cartApi = retrofit.create(CartApi.class);
                int newQuantity = Integer.parseInt(cartItem.getQuantity())-1;
                CartUpdateRequest cartUpdateRequest = new CartUpdateRequest(newQuantity, cartItem.getSizeNumber());
                cartApi.updateCart(SharedPrefManager.getInstance(context).getJWT(),
                        cartItem.getId(),
//                        newQuantity,
//                        cartItem.getSizeNumber()
                        cartUpdateRequest).enqueue(new Callback<CartResponse>() {
                    @Override
                    public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                        viewHolder.quantity.setText(String.valueOf(newQuantity));
                        CartResponse cartResponse = response.body();
                        if (response.isSuccessful()){
                            Log.d("kiet", "onResponse: success");
                        } else {
                            Log.d("kiet", response.code()+ "onResponse: jwt:" + SharedPrefManager.getInstance(context).getJWT());

                            Log.d("kiet", "onResponse: " + newQuantity + " " +cartItem.getSizeNumber() + " " + cartItem.getId());
                        }
                        Intent intent = new Intent(context, CartActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<CartResponse> call, Throwable t) {

                    }
                });

            }
        });
        viewHolder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartApi cartApi;
                Retrofit retrofit = ApiClient.getClient();
                cartApi = retrofit.create(CartApi.class);
                int newQuantity = Integer.parseInt(cartItem.getQuantity())+1;
                CartUpdateRequest cartUpdateRequest = new CartUpdateRequest(newQuantity, cartItem.getSizeNumber());
                cartApi.updateCart(SharedPrefManager.getInstance(context).getJWT(),
                        cartItem.getId(),
//                        newQuantity,
//                        cartItem.getSizeNumber()
                        cartUpdateRequest).enqueue(new Callback<CartResponse>() {
                    @Override
                    public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                        viewHolder.quantity.setText(String.valueOf(newQuantity));
                        CartResponse cartResponse = response.body();
                        if (response.isSuccessful()){
                            Log.d("kiet", "onResponse: success");
                        } else {
                            Log.d("kiet", response.code()+ "onResponse: jwt:" + SharedPrefManager.getInstance(context).getJWT());

                            Log.d("kiet", "onResponse: " + newQuantity + " " +cartItem.getSizeNumber() + " " + cartItem.getId());
                        }
                        Intent intent = new Intent(context, CartActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<CartResponse> call, Throwable t) {

                    }
                });
            }
        });
        viewHolder.delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartApi cartApi;
                Retrofit retrofit = ApiClient.getClient();
                cartApi = retrofit.create(CartApi.class);
                cartApi.deleteCartItem(SharedPrefManager.getInstance(context).getJWT(),
                        cartItem.getId()).enqueue(new Callback<ApiResponseDelete>() {
                    @Override
                    public void onResponse(Call<ApiResponseDelete> call, Response<ApiResponseDelete> response) {
                        ApiResponseDelete apiResponseDelete = response.body();
                        Log.d("kiet", response.code()+ "onResponse: success" + cartItem.getId()+" " + apiResponseDelete);
                        Intent intent = new Intent(context, CartActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ApiResponseDelete> call, Throwable t) {

                    }
                });
            }
        });
        return view;
    }
    ApiLatestProductResponse apiLatestProductResponse;
    private void loadProductDetail(int id) {
        apiLatestProductResponse = new ApiLatestProductResponse();
        List<Cloth> clothes = new ArrayList<>();
        ProductApi productApi;
        Retrofit retrofit = ApiClient.getClient();
        productApi = retrofit.create(ProductApi.class);
        productApi.getProductById(id).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                ProductResponse productResponse = response.body();
                Product product = new Product();
                product.setId(productResponse.getId());
                product.setName(productResponse.getName());
                product.setPrice(String.valueOf(productResponse.getPrice()));
                product.setDescription(productResponse.getDescription());
                product.setPath("http://172.22.224.1:8081/api/v1/mattresses/"+product.getId()+"/images");
                Intent intent = new Intent(context, ProductDetailActivity.class);
                intent.putExtra("product", product);
                List<Size> sizes = new ArrayList<>();
                for (InventoriesItem item: productResponse.getInventories()) {
                    Size size = new Size();
                    size.setId(item.getSize().getId());
                    size.setName(item.getSize().getName());
                    sizes.add(size);
                }
                intent.putExtra("sizes", (Serializable) sizes);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.d("kiet", "onFailure: Error in api product detail");
            }
        });
    }

    private class ViewHolder{
        TextView textName;
        ImageView imagePic;
        TextView textPrice, txtSize, txtSizeValue;
        TextView quantity;
        Button minus, plus;
        ImageButton delBtn;
    }
}
