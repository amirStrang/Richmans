package com.example.asus.richmans;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CustomSpinnerAdapter extends BaseAdapter {

    Context context;
    String[] titles;
    LayoutInflater inflater;
    // Initialise custom font, for example:
    Typeface font;

    public CustomSpinnerAdapter(Context context, String[] titles) {
        this.context = context;
        this.titles = titles;
        inflater = LayoutInflater.from(context);
        font = Typeface.createFromAsset(context.getAssets(), "fonts/BKoodakBold.ttf");
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_spinner, parent, false);
            holder = new ViewHolder();
            holder.txtSpinnerItem = (TextView) convertView.findViewById(R.id.txt_spinner_item);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.fill(position);
        return convertView;
    }

    public class ViewHolder {
        TextView txtSpinnerItem;

        public ViewHolder() {

        }

        public void fill(int position) {
            txtSpinnerItem.setText(titles[position]);
            txtSpinnerItem.setTypeface(font);
        }
    }
}
