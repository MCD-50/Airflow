package com.airstem.airflow.ayush.airflow.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airstem.airflow.ayush.airflow.MainActivity;
import com.airstem.airflow.ayush.airflow.R;
import com.airstem.airflow.ayush.airflow.helpers.MoodHelper;
import com.airstem.airflow.ayush.airflow.model.Advert;
import com.airstem.airflow.ayush.airflow.model.Base;
import com.airstem.airflow.ayush.airflow.model.Mood;
import com.airstem.airflow.ayush.airflow.model.Pda;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ayush on 06-10-16.
 */

public class CategoryAdapter extends BaseAdapter {

    List<Base> moods;
    MoodHelper moodHelper;
    Activity mActivity;

    LinearLayout linearLayout;
    ImageView imageView;

    TextView textView;
    Button topButton, shuffleButton;

    public CategoryAdapter(Activity activity){
        mActivity = activity;
        moodHelper = new MoodHelper();

        //we are initializing empty one so that is not null;

        moods = new ArrayList<>();

        //lets get data on by one
        initBaseMood();




        //moodHelper.getMoods();
        //getScreenWidth();
    }

    private void initBaseMood(){
        Mood[] moodList = moodHelper.getMoods();
        //moods.add(0, new Pda());
        for(Mood moodObject : moodList){
            moods.add(moodObject);
        }
    }

    @Override
    public int getCount() {
        return moods.size();
    }

    @Override
    public Base getItem(int position) {
        return moods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Base base = getItem(position);
        View rowView = convertView;
        if(rowView == null){
            rowView = LayoutInflater.from(mActivity).inflate(R.layout.i_feel_fragment_content, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) rowView.findViewById(R.id.i_feel_fragment_content_moodTextView);
            viewHolder.imageView = (ImageView) rowView.findViewById(R.id.i_feel_fragment_content_moodImageView);
            rowView.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        if(base.getClass().equals(Mood.class)){
            //we will parse the class first
            Mood moodClass = (Mood) base;
            viewHolder.textView.setText(moodClass.Name);
            Picasso.with(mActivity).load(moodClass.Image).placeholder(R.drawable.default_art).into(viewHolder.imageView);
        }
        return rowView;
    }
/*
    boolean subs = false;
    private void getItOnce(View v) {

        if(!subs){
            linearLayout = (LinearLayout) v.findViewById(R.id.i_feel_fragment_content_linearLayout);
            imageView = (ImageView) v.findViewById(R.id.i_feel_fragment_content_spImageView);
            textView = (TextView) v.findViewById(R.id.i_feel_fragment_content_msgTextView);
            topButton = (Button) v.findViewById(R.id.i_feel_fragment_content_topButton);
            shuffleButton = (Button) v.findViewById(R.id.i_feel_fragment_content_shuffleButton);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPda();
                }
            });

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPda();
                }
            });

            topButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) mActivity).executeIFeelFragmentAirflow50ListViewOnItemSelected();
                }
            });

            shuffleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) mActivity).executeIFeelFragmentShuffleListViewOnItemSelected();
                }
            });

            subs = true;
        }
    }

    private void showPda(){

    }*/


    static class ViewHolder{
        TextView textView;
        ImageView imageView;
    }

}
