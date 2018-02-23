package com.example.asus.richmans;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class BuyHistoryViewHolder extends RecyclerView.ViewHolder {
    public ImageView imgProduct;
    public TextView txtName, txtPrice,txtDate,txtTime;

    public BuyHistoryViewHolder(View itemView) {
        super(itemView);

        imgProduct = (ImageView) itemView.findViewById(R.id.img_product);
        txtName = (TextView) itemView.findViewById(R.id.txt_product_name);
        txtPrice = (TextView) itemView.findViewById(R.id.txt_price);
        txtDate =(TextView) itemView.findViewById(R.id.txt_date);
        txtTime =(TextView) itemView.findViewById(R.id.txt_time);
    }
}
