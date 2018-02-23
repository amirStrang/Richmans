package com.example.asus.richmans;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class PlayHistoryViewHolder extends RecyclerView.ViewHolder {

    public TextView txtBeginPeriodDate, txtEndPeriodDate, txtMaxCreditValue;

    public PlayHistoryViewHolder(View itemView) {
        super(itemView);

        txtBeginPeriodDate = (TextView) itemView.findViewById(R.id.txt_begin_period_date);
        txtEndPeriodDate = (TextView) itemView.findViewById(R.id.txt_end_period_date);
        txtMaxCreditValue = (TextView) itemView.findViewById(R.id.txt_max_credit_value);
    }
}
