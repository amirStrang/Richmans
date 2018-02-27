package com.example.asus.richmans;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PlayHistoryRecyclerAdapter extends RecyclerView.Adapter<PlayHistoryViewHolder> {

    private List<PlayHistory> histories;
    private Context context;

    public PlayHistoryRecyclerAdapter(Context context, List<PlayHistory> histories) {
        this.context = context;
        this.histories = histories;
    }

    @Override
    public PlayHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_total_history, parent, false);
        return new PlayHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayHistoryViewHolder holder, int position) {
        PlayHistory playHistory = histories.get(position);

        holder.txtBeginPeriodDate.setText(playHistory.beginPeriodDate);
        holder.txtEndPeriodDate.setText(playHistory.endPeriodDate);
//        holder.txtMaxCreditValue.setText(playHistory.maxCredit);
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }
}
