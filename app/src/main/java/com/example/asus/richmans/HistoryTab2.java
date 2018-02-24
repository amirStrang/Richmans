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

public class HistoryTab2 extends Fragment {

    RecyclerView recTotalHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history_tab2, container, false);

        recTotalHistory = rootView.findViewById(R.id.rec_total_history);
        List<PlayHistory> histories = new ArrayList<>();

        //sample init
        for (int i = 0; i < 5; i++) {
            PlayHistory playHistory = new PlayHistory();

            playHistory.beginPeriodDate = "1/1/1396";
            playHistory.endPeriodDate = "1/2/1396";
//            playHistory.maxCredit = "50000";

            histories.add(playHistory);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recTotalHistory.setLayoutManager(linearLayoutManager);
        recTotalHistory.setHasFixedSize(true);
        recTotalHistory.setAdapter(new PlayHistoryRecyclerAdapter(container.getContext(), histories));
//        DividerItemDecoration itemDecor = new DividerItemDecoration(container.getContext(), VERTICAL);
//        recTotalHistory.addItemDecoration(itemDecor);
        return rootView;
    }
}
