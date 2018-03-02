package com.example.asus.richmans;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

public class TutorialRecyclerAdapter extends RecyclerView.Adapter<TutorialViewHolder> {

    private List<Tutorial> tutorials;
    Context context;

    public TutorialRecyclerAdapter(Context context, List<Tutorial> tutorials) {
        this.tutorials = tutorials;
        this.context = context;
    }

    @Override
    public TutorialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tutorials, parent, false);
        return new TutorialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TutorialViewHolder holder, int position) {
        final Tutorial tutorial = tutorials.get(position);

        holder.txtName.setText(tutorial.name);
        holder.txtExplain.setText(tutorial.explain);
        Glide.with(context).load(tutorial.image).into(holder.imgTutorial);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, TextTutorialActivity.class);
                i.putExtra("name", tutorial.name);
                i.putExtra("explain", tutorial.explain);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tutorials.size();
    }
}
