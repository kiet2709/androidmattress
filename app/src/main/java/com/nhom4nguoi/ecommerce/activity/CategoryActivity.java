package com.nhom4nguoi.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.GridView;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.adapter.CategoryAdapter;
import com.nhom4nguoi.ecommerce.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    GridView gridView;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        gridView = (GridView) findViewById(R.id.gridview_category);
        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.height = (getCategories().size())*305;
        layoutParams.width = 1067;
        gridView.setLayoutParams(layoutParams);
        categoryAdapter = new CategoryAdapter(
                CategoryActivity.this,
                getCategories(),
                R.layout.item_category
        );
        gridView.setAdapter(categoryAdapter);
        Log.d("kiet", "onCreate: "+ getCategories().get(0).getResourceId());
    }

    private List<Category> getCategories(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("cate 1", R.drawable.product1));
        categories.add(new Category("cate 2", R.drawable.product2));
        categories.add(new Category("cate 3", R.drawable.product3));
        categories.add(new Category("cate 4", R.drawable.product4));
        categories.add(new Category("cate 5", R.drawable.product1));
        return categories;
    }
}