package com.example.asus.richmans;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class SubCategoryViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView name;
    public RelativeLayout relativeLayout;

    public SubCategoryViewHolder(View itemView) {
        super(itemView);

        image = (ImageView) itemView.findViewById(R.id.img_subCat);
        name = (TextView) itemView.findViewById(R.id.txt_subCat);
        relativeLayout = (RelativeLayout) itemView.findViewById(R.id.subcat_rec_item);
    }

}
