package com.example.asus.richmans;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ProductViewHolder extends RecyclerView.ViewHolder {
    public ImageView image;
    public TextView name, price;
    public RelativeLayout relativeLayout;

    public ProductViewHolder(View itemView) {
        super(itemView);

        image = (ImageView) itemView.findViewById(R.id.img_product);
        name = (TextView) itemView.findViewById(R.id.txt_name);
        price = (TextView) itemView.findViewById(R.id.txt_price);
        relativeLayout = (RelativeLayout) itemView.findViewById(R.id.product_horizontal_item);
    }
}
