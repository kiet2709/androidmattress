package com.nhom4nguoi.ecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.model.Banner;

import java.util.List;

public class BannerAdapter extends PagerAdapter {

    private Context context;
    private List<Banner> bannerList;

    public BannerAdapter(Context context, List<Banner> bannerList) {
        this.context = context;
        this.bannerList = bannerList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_banner, container, false);
        ImageView imageView = view.findViewById(R.id.banner);
        Banner banner = bannerList.get(position);
        if (banner != null){
            Glide.with(context).load(banner.getResourceId()).into(imageView);
        }
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if (bannerList != null){
            return bannerList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==  object;
    }
}
