package com.airstem.airflow.ayush.airflow.adapters.option;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.model.helper.Option;

import java.util.ArrayList;

/**
 * Created by mcd-50 on 25/9/17.
 */

public class OptionAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Option> mItems;

    public OptionAdapter(Context context, ArrayList<Option> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Option getItem(int position) {
        return mItems.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = layoutInflater.inflate(R.layout.option_item, null, true);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) rowView.findViewById(R.id.option_item_text);
            viewHolder.image = (ImageView) rowView.findViewById(R.id.option_item_image);
            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }

        Option item = mItems.get(position);
        viewHolder.text.setText(item.getText());
        viewHolder.image.setImageResource(item.getImage());
        return rowView;
    }

    static class ViewHolder {
        public TextView text;
        public ImageView image;
    }
}
