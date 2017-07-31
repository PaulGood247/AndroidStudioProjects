package com.example.paul.listviewexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<String> {
    public MyAdapter(Context context, String[] values) {
        super(context, R.layout.country_page_layout, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater theInflater = LayoutInflater.from(getContext());

        View theView = theInflater.inflate(R.layout.country_page_layout, parent, false);

        String country = getItem(position);

        TextView theTextView = (TextView) theView.findViewById(R.id.countryTextView);

        theTextView.setText(country);

        ImageView theImageView;
        theImageView = (ImageView) theView.findViewById(R.id.countryFlagImage);



        if (country.equals("Ireland")) {
            theImageView.setImageResource(R.drawable.check_icon);
        }
        else{
            theImageView.setImageResource(R.mipmap.ic_launcher);
        }

        return theView;
    }
}
