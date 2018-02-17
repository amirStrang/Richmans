package com.example.asus.richmans;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;


public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    private List<Product> products;
    private Context context;

    public ProductRecyclerAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_horizontal_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = products.get(position);

        holder.name.setText(product.name);
        holder.price.setText((product.price));
        Glide.with(context).load(product.images.get(0)).into(holder.image);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
