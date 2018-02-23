package com.example.asus.richmans;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;


class BuyHistoryRecyclerAdapter extends RecyclerView.Adapter<BuyHistoryViewHolder> {

    private List<BuyHistory> histories;
    private Context context;


    public BuyHistoryRecyclerAdapter(Context context, List<BuyHistory> histories) {
        this.histories = histories;
        this.context = context;
    }

    @Override
    public BuyHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.current_hstory_item, parent, false);
        return new BuyHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BuyHistoryViewHolder holder, int position) {
        BuyHistory buyHistory = histories.get(position);

        holder.txtName.setText(buyHistory.productName);
        holder.txtPrice.setText(buyHistory.price);
        Glide.with(context).load(buyHistory.image).into(holder.imgProduct);
        holder.txtDate.setText(buyHistory.date);
        holder.txtTime.setText(buyHistory.time);
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }
}
