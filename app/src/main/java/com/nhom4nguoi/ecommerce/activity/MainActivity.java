package com.nhom4nguoi.ecommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.GridView;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.adapter.BannerAdapter;
import com.nhom4nguoi.ecommerce.adapter.ClothAdapter;
import com.nhom4nguoi.ecommerce.adapter.ClothAdapter2;
import com.nhom4nguoi.ecommerce.model.Banner;
import com.nhom4nguoi.ecommerce.model.Cloth;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcvCloth, rcvForyou;
    GridView gridView;
    ClothAdapter clothAdapter, clothAdapterForyou;
    ClothAdapter2 clothAdapter2;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private BannerAdapter bannerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // anh xa cho banner
        viewPager = findViewById(R.id.viewpaper);
        circleIndicator = findViewById(R.id.circle_indicator);

        bannerAdapter = new BannerAdapter(this, getBannerList());
        viewPager.setAdapter(bannerAdapter);
        circleIndicator.setViewPager(viewPager);
        bannerAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        //anh xa cho rcv best seller
        rcvCloth = findViewById(R.id.rcv_cloth);
        clothAdapter = new ClothAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcvCloth.setLayoutManager(linearLayoutManager);
        clothAdapter.setData(getClothes());
        rcvCloth.setAdapter(clothAdapter);
        //anh xa cho rcv for you
        rcvForyou = findViewById(R.id.rcv_cloth_foryou);
        clothAdapterForyou = new ClothAdapter(this);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rcvForyou.setLayoutManager(linearLayoutManager1);
        clothAdapterForyou.setData(getClothes());
        rcvForyou.setAdapter(clothAdapterForyou);
        //anh xa cho product gridview
        gridView = (GridView) findViewById(R.id.gridview_product);
        ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
        layoutParams.height = (getClothes().size())*320;
        Log.d("Kiet", layoutParams.height+"");
        layoutParams.width = 1067;
        gridView.setLayoutParams(layoutParams);
        clothAdapter2 = new ClothAdapter2(
                MainActivity.this,
                getClothes(),
                R.layout.item_product2
        );
        gridView.setAdapter(clothAdapter2);
    }

    private List<Cloth> getClothes() {
        List<Cloth> clothes = new ArrayList<>();
        clothes.add(new Cloth(R.drawable.product1, "Product 1", 15));
        clothes.add(new Cloth(R.drawable.product2, "Product 2", 25));
        clothes.add(new Cloth(R.drawable.product3, "Product 3", 10));
        clothes.add(new Cloth(R.drawable.product4, "Product 4", 35));

        clothes.add(new Cloth(R.drawable.product1, "Product 1", 15));
        clothes.add(new Cloth(R.drawable.product2, "Product 2", 25));
        clothes.add(new Cloth(R.drawable.product3, "Product 3", 10));
        clothes.add(new Cloth(R.drawable.product4, "Product 4", 35));
        return clothes;
    }

    private List<Banner> getBannerList() {
        List<Banner> bannerList = new ArrayList<>();
        bannerList.add(new Banner(R.drawable.banner1));
        bannerList.add(new Banner(R.drawable.banner2));
        bannerList.add(new Banner(R.drawable.banner3));
        return bannerList;
    }
}