package com.example.asus.richmans;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.VERTICAL;

public class HistoryTab1 extends Fragment {

    RecyclerView recCurrentPeriodHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history_tab1, container, false);

        recCurrentPeriodHistory = rootView.findViewById(R.id.rec_current_period_history);
        List<BuyHistory> histories = new ArrayList<>();

        //sample init
        for (int i = 0; i < 5; i++) {
            BuyHistory buyHistory = new BuyHistory();
            buyHistory.productName = "موبایل";
            buyHistory.price = "1000000";
            buyHistory.image = R.drawable.sample4;
            buyHistory.date = "3/3/2018";
            buyHistory.time = "14:25";
            histories.add(buyHistory);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recCurrentPeriodHistory.setLayoutManager(linearLayoutManager);
        recCurrentPeriodHistory.setHasFixedSize(true);
        recCurrentPeriodHistory.setAdapter(new BuyHistoryRecyclerAdapter(container.getContext(), histories));
        DividerItemDecoration itemDecor = new DividerItemDecoration(container.getContext(), VERTICAL);
        recCurrentPeriodHistory.addItemDecoration(itemDecor);
        return rootView;
    }
}
