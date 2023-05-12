package com.nhom4nguoi.ecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.model.Order;

import java.util.List;

public class OrderAdapterInProgress extends BaseAdapter {
    private Context context;
    private List<Order> orders;
    private int layout;

    public OrderAdapterInProgress(Context context, List<Order> orders, int layout) {
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

    public OrderAdapterInProgress(Context context) {
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
        OrderAdapterInProgress.ViewHolder viewHolder;

        if (view==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout,null);

            viewHolder = new OrderAdapterInProgress.ViewHolder();
            viewHolder.textOrderDate = (TextView) view.findViewById(R.id.txt_orderDate);
            viewHolder.textSum = (TextView) view.findViewById(R.id.txt_numberSumOrder);
            viewHolder.itemOrder = (LinearLayout) view.findViewById(R.id.item_order);
            view.setTag(viewHolder);
        }else{
            viewHolder= (OrderAdapterInProgress.ViewHolder) view.getTag();
        }
        Order order = orders.get(i);
        viewHolder.textOrderDate.setText(order.getOrderDate().toString());
        viewHolder.textSum.setText(order.getSum());
        viewHolder.itemOrder.setBackgroundColor(0xFFFFFF00);

        return view;
    }

    private class ViewHolder{
        TextView textOrderDate, textSum;
        LinearLayout itemOrder;
    }
}
