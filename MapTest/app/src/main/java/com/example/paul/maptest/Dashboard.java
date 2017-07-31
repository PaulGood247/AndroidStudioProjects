package com.example.paul.maptest;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by Paul on 01/12/2016.
 */



public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);

        Button btn = (Button) findViewById(R.id.button);

        final AutoCompleteTextView originAutocompleteView = (AutoCompleteTextView) findViewById(R.id.originAutocomplete);
        originAutocompleteView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.autocomplete_list_item));

        originAutocompleteView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get data associated with the specified position
                // in the list (AdapterView)
                String description = (String) parent.getItemAtPosition(position);
                Toast.makeText(Dashboard.this, description, Toast.LENGTH_SHORT).show();
            }
        });

        final AutoCompleteTextView destAutocompleteView = (AutoCompleteTextView) findViewById(R.id.destAutocomplete);
        destAutocompleteView.setAdapter(new PlacesAutoCompleteAdapter(this, R.layout.autocomplete_list_item));

        destAutocompleteView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get data associated with the specified position
                // in the list (AdapterView)
                String description = (String) parent.getItemAtPosition(position);
                Toast.makeText(Dashboard.this, description, Toast.LENGTH_SHORT).show();
            }
        });

        btn.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {

                        Log.v("Origin: ", String.valueOf(getLocationFromAddress(Dashboard.this, originAutocompleteView.getText().toString())));
                        Log.v("Dest: ", String.valueOf(getLocationFromAddress(Dashboard.this, destAutocompleteView.getText().toString())));

                        Intent i = new Intent(Dashboard.this, MapsActivity.class);
                        //Bundle args = new Bundle();
                        //args.putParcelable("origin", getLocationFromAddress(Dashboard.this, originAutocompleteView.getText().toString()));
                        //args.putParcelable("dest", getLocationFromAddress(Dashboard.this, destAutocompleteView.getText().toString()));

                        i.putExtra("origin", String.valueOf(getLocationFromAddress(Dashboard.this, originAutocompleteView.getText().toString())));
                        i.putExtra("dest", String.valueOf(getLocationFromAddress(Dashboard.this, destAutocompleteView.getText().toString())));
                        startActivity(i);
                    }
                });

    }
    public LatLng getLocationFromAddress(Context context, String strAddress)
    {
        Geocoder coder= new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try
        {
            address = coder.getFromLocationName(strAddress, 5);
            if(address==null)
            {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return p1;

    }
}
