package com.nhom4nguoi.ecommerce.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.activity.LoginActivity;
import com.nhom4nguoi.ecommerce.activity.OrderActivity;
import com.nhom4nguoi.ecommerce.activity.ProfileActivity;
import com.nhom4nguoi.ecommerce.model.Category;
import com.nhom4nguoi.ecommerce.util.SharedPrefManager;

import java.util.List;

public class PersonalAdapter extends BaseAdapter {
    private Context context;
    private List<String> menus;
    private int layout;

    public PersonalAdapter(Context context, List<String> menus, int layout) {
        this.context = context;
        this.menus = menus;
        this.layout = layout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<String> getMenus() {
        return menus;
    }

    public void setMenus(List<String> menus) {
        this.menus = menus;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public PersonalAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return menus==null? 0 :menus.size();
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
        PersonalAdapter.ViewHolder viewHolder;

        if (view==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout,null);

            viewHolder = new PersonalAdapter.ViewHolder();
            viewHolder.textName = (TextView) view.findViewById(R.id.txt_personalMenu);
            viewHolder.up = (Button) view.findViewById(R.id.btnPersonalUp);
            viewHolder.down = (Button) view.findViewById(R.id.btnPersonalDown);
            view.setTag(viewHolder);
        }else{
            viewHolder= (PersonalAdapter.ViewHolder) view.getTag();
        }
        String menu = menus.get(i);
        viewHolder.textName.setText(menu);
        viewHolder.textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolder.textName.getText().equals("Profile")){
                    Intent intent = new Intent(context, ProfileActivity.class);
                    context.startActivity(intent);
                } else if (viewHolder.textName.getText().equals("Order")) {
                    Intent intent = new Intent(context, OrderActivity.class);
                    context.startActivity(intent);
                } else if (viewHolder.textName.getText().equals("Sign out")) {
                    SharedPrefManager.getInstance(context).storeJWT("");
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }
            }
        });
        return view;
    }

    private class ViewHolder{
        TextView textName;
        Button up, down;
    }
}
