package com.example.asus.richmans.adapter;

/**
 * Created by Mr.Anonymous on 2/24/2018.
 */

import com.example.asus.richmans.R;
import com.example.asus.richmans.app.AppController;
import com.example.asus.richmans.model.Mproduct;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class MproductCustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Mproduct> product;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public MproductCustomListAdapter(Activity activity, List<Mproduct> product) {
        this.activity = activity;
        this.product = product;
    }

    @Override
    public int getCount() {
        return product.size();
    }

    @Override
    public Object getItem(int location) {
        return product.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_myshop_product_horizontal, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView cat = (TextView) convertView.findViewById(R.id.category);

        Mproduct m = product.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // name
        name.setText(m.getName() + "");

        // price
        price.setText(m.getPrice());
        cat.setText(m.getCat());

        return convertView;
    }

}