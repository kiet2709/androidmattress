package com.nhom4nguoi.ecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.model.CartItem;

import java.util.List;

public class AdminProductAdapter extends BaseAdapter {
    private Context context;
    private List<CartItem> cartItems;
    private int layout;

    public AdminProductAdapter(Context context, List<CartItem> cartItems, int layout) {
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

    public AdminProductAdapter(Context context) {
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
        AdminProductAdapter.ViewHolder viewHolder;

        if (view==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout,null);

            viewHolder = new AdminProductAdapter.ViewHolder();
            viewHolder.textName = (TextView) view.findViewById(R.id.txt_cartItemName);
            viewHolder.imagePic = (ImageView) view.findViewById(R.id.img_cart);
            viewHolder.textPrice = (TextView) view.findViewById(R.id.txt_cartItemPrice);
            viewHolder.quantity = (TextView) view.findViewById(R.id.txt_cartItemQuantity);
            view.setTag(viewHolder);
        }else{
            viewHolder= (AdminProductAdapter.ViewHolder) view.getTag();
        }
        CartItem cartItem = cartItems.get(i);
        viewHolder.textName.setText(cartItem.getName());
        viewHolder.imagePic.setImageResource(cartItem.getResourceId());
        viewHolder.textPrice.setText(cartItem.getPrice());
        viewHolder.quantity.setText(cartItem.getQuantity());
        return view;
    }

    private class ViewHolder{
        TextView textName;
        ImageView imagePic;
        TextView textPrice;
        TextView quantity;

    }

}
