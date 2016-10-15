package com.airstem.airflow.ayush.airflow;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.airstem.airflow.ayush.airflow.helpers.StorageHelper;
import com.airstem.airflow.ayush.airflow.utils.CollectionUtils;
import com.airstem.airflow.ayush.airflow.utils.DatabaseUtils;

/**
 * Created by ayush on 08-10-16.
 */
public class AboutActivity extends AppCompatActivity {


    Spinner countriesSpinner;
    StorageHelper storageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.about_page_toolbar);
        setSupportActionBar(toolbar);

        storageHelper = new StorageHelper(getApplicationContext());
        countriesSpinner = (Spinner) findViewById(R.id.about_activity_countriesSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CollectionUtils.countries);

        countriesSpinner.setAdapter(adapter);

        countriesSpinner.setSelection(CollectionUtils.getIndexFromCountryName(storageHelper.getData()));

        countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                storageHelper.saveData(CollectionUtils.getCountryFromIndex(position));
                countriesSpinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }


    public void emailButtonOnClicked(View view){
        Intent sIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "ayush_shukla@live.com", null));
        sIntent.putExtra(Intent.EXTRA_SUBJECT, "Airflow user");
        sIntent.putExtra(Intent.EXTRA_TEXT, "Thank you.");

        try{
            startActivity(Intent.createChooser(sIntent, "Contact developer.."));
        }catch (Exception e){
            Snackbar.make(view,"No mail client installed.", Snackbar.LENGTH_LONG).show();
        }
    }

    public void airstemOnClick(View view){
        Intent bIntent = new Intent(Intent.ACTION_VIEW);
        bIntent.setData(Uri.parse("http://airstemapp.azurewebsites.net"));
        startActivity(bIntent);
    }

    public void twitterOnClick(View view){
        Intent bIntent = new Intent(Intent.ACTION_VIEW);
        bIntent.setData(Uri.parse("https://twitter.com/Aaayush_AS"));
        startActivity(bIntent);
    }

    public void paypalOnClick(View view){
        Intent bIntent = new Intent(Intent.ACTION_VIEW);
        bIntent.setData(Uri.parse("https://www.paypal.me/AyushAS"));
        startActivity(bIntent);
    }
}
