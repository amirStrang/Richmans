package com.example.asus.richmans.adapter;

/**
 * Created by Mr.Anonymous on 3/3/2018.
 */

import com.example.asus.richmans.R;
import com.example.asus.richmans.app.AppController;
import com.example.asus.richmans.model.History;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class HistoryCustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<History> historyList;

    public HistoryCustomListAdapter(Activity activity, List<History> historyList) {
        this.activity = activity;
        this.historyList = historyList;
    }

    @Override
    public int getCount() {
        return historyList.size();
    }

    @Override
    public Object getItem(int location) {
        return historyList.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_history2, null);

        TextView from = (TextView) convertView.findViewById(R.id.fromdate);
        TextView to = (TextView) convertView.findViewById(R.id.todate);
        TextView day = (TextView) convertView.findViewById(R.id.totalday);

        History m = historyList.get(position);

        from.setText(m.getFrom() + "");

        to.setText(m.getTo() + "");

        day.setText(m.getDay() + "");

        return convertView;
    }

}