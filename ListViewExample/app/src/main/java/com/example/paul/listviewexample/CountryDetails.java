package com.example.paul.listviewexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class CountryDetails extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_page_layout);

        Intent intent = getIntent();
        String country = intent.getStringExtra("Country");

        findViewById(R.id.countryTextView);

        TextView countryView = (TextView) findViewById(R.id.countryTextView);
        countryView.setText("The country chosen is " + country);

        ImageView countryImage = (ImageView) findViewById(R.id.countryFlagImage);

        if (country.equals("Ireland")) {
            countryImage.setImageResource(R.drawable.irishflag);
        }
        else if (country.equals("England")) {
            countryImage.setImageResource(R.drawable.englishflag);
        }
    }

}



