package com.nhom4nguoi.ecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.activity.AdminOrderCompleted;
import com.nhom4nguoi.ecommerce.activity.AdminOrderInProgressActivity;
import com.nhom4nguoi.ecommerce.api.ApiClient;
import com.nhom4nguoi.ecommerce.api.OrderApi;
import com.nhom4nguoi.ecommerce.model.Order;
import com.nhom4nguoi.ecommerce.response.APICurrentOrder.CurrentOrderResponseItem;
import com.nhom4nguoi.ecommerce.response.OrderStatusRequest;
import com.nhom4nguoi.ecommerce.util.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderCompletedAdminAdapter extends BaseAdapter {
    private Context context;
    private List<Order> orders;
    private int layout;

    public OrderCompletedAdminAdapter(Context context, List<Order> orders, int layout) {
        this.context = context;
        this.orders = orders;
        this.layout = layout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public OrderCompletedAdminAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return orders==null? 0 :orders.size();
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
        OrderCompletedAdminAdapter.ViewHolder viewHolder;

        if (view==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout,null);

            viewHolder = new OrderCompletedAdminAdapter.ViewHolder();
            viewHolder.textOrderDate = (TextView) view.findViewById(R.id.txt_orderDateAdmin);
            viewHolder.textSum = (TextView) view.findViewById(R.id.txt_numberSumOrderAdmin);
            viewHolder.itemOrder = (LinearLayout) view.findViewById(R.id.item_orderAdmin);
            viewHolder.prev = (ImageButton) view.findViewById(R.id.btn_prevAdmin);
            viewHolder.next = (ImageButton) view.findViewById(R.id.btn_nextAdmin);

            view.setTag(viewHolder);
        }else{
            viewHolder= (OrderCompletedAdminAdapter.ViewHolder) view.getTag();
        }
        Order order = orders.get(i);
        viewHolder.textOrderDate.setText(order.getOrderDate().toString());
        viewHolder.textSum.setText(order.getSum());
        viewHolder.itemOrder.setBackgroundColor(0xFF00FF00);
        viewHolder.prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderStatusRequest orderStatusRequest = new OrderStatusRequest(order.getId(), "In delivering");
                OrderApi orderApi;
                Retrofit retrofit = ApiClient.getClient();
                orderApi = retrofit.create(OrderApi.class);
                orderApi.updateOrder(SharedPrefManager.getInstance(context).getJWT(), orderStatusRequest).enqueue(new Callback<CurrentOrderResponseItem>() {
                    @Override
                    public void onResponse(Call<CurrentOrderResponseItem> call, Response<CurrentOrderResponseItem> response) {
                        Toast.makeText(context, "Update Successfully!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, AdminOrderCompleted.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<CurrentOrderResponseItem> call, Throwable t) {

                    }
                });
            }
        });
        return view;
    }

    private class ViewHolder{
        TextView textOrderDate, textSum;
        LinearLayout itemOrder;
        ImageButton prev, next;
    }
}
