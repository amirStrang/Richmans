package com.example.asus.richmans;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TutorialViewHolder extends RecyclerView.ViewHolder {

    //text
    public ImageView imgTutorial;
    public TextView txtName, txtExplain;

    public RelativeLayout layout;
    int type;

    public TutorialViewHolder(View itemView) {
        super(itemView);

        imgTutorial = (ImageView) itemView.findViewById(R.id.img_tutorial);
        txtName = (TextView) itemView.findViewById(R.id.txt_tutorial_name);
        txtExplain = (TextView) itemView.findViewById(R.id.txt_tutorial_explain);

        layout = (RelativeLayout) itemView.findViewById(R.id.layout_tutorial);
    }
}
