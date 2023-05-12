package com.nhom4nguoi.ecommerce.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom4nguoi.ecommerce.R;
import com.nhom4nguoi.ecommerce.model.Cloth;

import java.util.List;

public class ClothAdapter2 extends BaseAdapter {
    private Context context;
    private List<Cloth> clothes;
    private int layout;

    public ClothAdapter2(Context context, List<Cloth> clothes, int layout) {
        this.context = context;
        this.clothes = clothes;
        this.layout = layout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Cloth> getClothes() {
        return clothes;
    }

    public void setClothes(List<Cloth> clothes) {
        this.clothes = clothes;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public ClothAdapter2(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return clothes==null? 0 :clothes.size();
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
        ViewHolder viewHolder;

        if (view==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout,null);

            viewHolder = new ViewHolder();
            viewHolder.textName = (TextView) view.findViewById(R.id.product_name2);
            viewHolder.textDesc = (TextView) view.findViewById(R.id.product_price2);
            viewHolder.imagePic = (ImageView) view.findViewById(R.id.img_product2);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        Cloth cloth = clothes.get(i);
        viewHolder.textName.setText(cloth.getTitle());
        viewHolder.textDesc.setText(cloth.getPrice().toString());
        viewHolder.imagePic.setImageResource(cloth.getResourceId());

        return view;
    }

    private class ViewHolder{
        TextView textName,textDesc;
        ImageView imagePic;
    }

}
