package com.airstem.airflow.ayush.airflow.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.helpers.MoodHelper;
import com.airstem.airflow.ayush.airflow.model.Mood;

/**
 * Created by ayush on 06-10-16.
 */

public class CategoryAdapter extends BaseAdapter {

    Mood[] moods;
    MoodHelper moodHelper;
    Context mContext;

    public CategoryAdapter(Context context){
        mContext = context;
        moodHelper = new MoodHelper();
        moods = moodHelper.getMoods();
        //getScreenWidth();
    }

    @Override
    public int getCount() {
        return moods.length;
    }

    @Override
    public Mood getItem(int position) {
        return moods[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        if(rowView == null){
            //LayoutInflater lInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = LayoutInflater.from(mContext).inflate(R.layout.i_feel_fragment_content, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) rowView.findViewById(R.id.i_feel_fragment_content_moodTextView);
            viewHolder.imageView = (ImageView) rowView.findViewById(R.id.i_feel_fragment_content_moodImageView);
            rowView.setTag(viewHolder);
        }

        Mood mood= getItem(position);

        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        viewHolder.textView.setText(mood.Name);
        viewHolder.imageView.setImageResource(mood.Image);

        return rowView;
    }


    static class ViewHolder{
        TextView textView;
        ImageView imageView;
    }

}
