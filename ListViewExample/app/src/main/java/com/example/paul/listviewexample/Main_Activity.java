package com.example.paul.listviewexample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import static com.example.paul.listviewexample.R.layout.activity_main;

public class Main_Activity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);

        String[] countries = {"Ireland", "England",
                "Spain", "France", "America", "Australia",
                "China", "India", "Russia", "Greece"};


        ListAdapter theAdapter = new MyAdapter(this ,countries);

        setListAdapter(theAdapter);
    }

    protected void onListItemClick(ListView listView, View view, int position, long l) {

        String countryPicked =
                String.valueOf(listView.getItemAtPosition(position));

        Intent countryClick = new Intent(Main_Activity.this, CountryDetails.class);
        countryClick.putExtra("Country", countryPicked );
        startActivity(countryClick);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}