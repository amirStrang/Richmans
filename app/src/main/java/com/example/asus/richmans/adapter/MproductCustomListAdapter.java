package com.example.asus.richmans.adapter;

/**
 * Created by Mr.Anonymous on 2/24/2018.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.asus.richmans.R;
import com.example.asus.richmans.app.AppController;
import com.example.asus.richmans.model.Mproduct;

import java.util.List;

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
        price.setText(Separate3digits(m.getPrice()));
        cat.setText(m.getCat());

        return convertView;
    }


    private String Separate3digits(String value) {
        char[] temp = value.toCharArray();
        String result = "";
        int counter = 0;
        for (int i = value.length() - 1; i >= 0; i--) {
            result += temp[i];
            counter++;
            if (counter % 3 == 0 && i != 0) {
                result += ",";
            }
        }
        temp = result.toCharArray();
        result = "";
        for (int i = temp.length - 1; i >= 0; i--) {
            result += temp[i];
        }
        return result;
    }

}