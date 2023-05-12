package com.nhom4nguoi.ecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.model.Category;
import com.nhom4nguoi.ecommerce.model.Cloth;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private List<Category> categories;
    private int layout;

    public CategoryAdapter(Context context, List<Category> categories, int layout) {
        this.context = context;
        this.categories = categories;
        this.layout = layout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public CategoryAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return categories==null? 0 :categories.size();
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
        CategoryAdapter.ViewHolder viewHolder;

        if (view==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout,null);

            viewHolder = new CategoryAdapter.ViewHolder();
            viewHolder.textName = (TextView) view.findViewById(R.id.txt_category);
            viewHolder.imagePic = (ImageView) view.findViewById(R.id.img_category);
            view.setTag(viewHolder);
        }else{
            viewHolder= (CategoryAdapter.ViewHolder) view.getTag();
        }
        Category category = categories.get(i);
        viewHolder.textName.setText(category.getName());
        viewHolder.imagePic.setImageResource(category.getResourceId());

        return view;
    }

    private class ViewHolder{
        TextView textName;
        ImageView imagePic;
    }
}
