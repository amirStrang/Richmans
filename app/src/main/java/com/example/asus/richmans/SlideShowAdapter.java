package com.example.asus.richmans;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.example.asus.richmans.app.AppController;

import java.util.ArrayList;


public class SlideShowAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<String> images = new ArrayList<>();

    public SlideShowAdapter(Context context, String Uri1, String Uri2, String Uri3) {
        this.context = context;

        images.add(Uri1);
        images.add(Uri2);
        images.add(Uri3);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.slideshow_layout, container, false);

        NetworkImageView imageView = (NetworkImageView) view.findViewById(R.id.image);

//        Glide.with(context).load(images.get(position)).into(imageView);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        imageView.setImageUrl(images.get(position), imageLoader);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(context, "index : " + position, Toast.LENGTH_SHORT).show();
            }
        });

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout) object);
    }
}
