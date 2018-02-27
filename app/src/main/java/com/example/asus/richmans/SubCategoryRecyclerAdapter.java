package com.example.asus.richmans;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;


public class SubCategoryRecyclerAdapter extends RecyclerView.Adapter<SubCategoryViewHolder> {

    private List<SubCategory> subCats;
    private Context context;

    public SubCategoryRecyclerAdapter(Context context, List<SubCategory> subCats) {
        this.subCats = subCats;
        this.context = context;
    }

    @Override
    public SubCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subcat_recycler, parent, false);
        return new SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SubCategoryViewHolder holder, int position) {
        SubCategory subCat = subCats.get(position);

        holder.name.setText(subCat.name);
        Glide.with(context).load(subCat.image).into(holder.image);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to Product list
                String subCatName = holder.name.getText().toString();
                Intent i = new Intent(context, ProductsListActivity.class);
                i.putExtra("URI", subCatName);
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return subCats.size();
    }

}
