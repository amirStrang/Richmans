package com.example.asus.richmans;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TutorialViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgTutorial;
    public TextView txtName, txtExplain;
    public RelativeLayout layout;

    public TutorialViewHolder(View itemView) {
        super(itemView);

        imgTutorial = itemView.findViewById(R.id.img_tutorial);
        txtName = itemView.findViewById(R.id.txt_tutorial_name);
        txtExplain = itemView.findViewById(R.id.txt_tutorial_explain);
        layout = (RelativeLayout) itemView.findViewById(R.id.layout_tutorial);
    }
}
