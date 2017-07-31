package com.example.paul.lab4b;
import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class CountryList extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);

        //String[] countries = new String[]{"Ireland","England","Spain"};

        //setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries));
        //onListItemClick(countries, 1, "@android:id/list");

        countrylist();
        clickCountryList();
    }

    private void countrylist() {
        Resources res = getResources();
        String[] countries = res.getStringArray(R.array.countries_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.countrylayout, countries);

        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(adapter);

    }

    private void clickCountryList(){
        ListView list = (ListView) findViewById(android.R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView countryView = (TextView) view;
                Toast.makeText(CountryList.this, "Clicked on "+ countryView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*private void onListItemClick(String[] countries, int position, String s) {
        Toast.makeText(CountryList.this, countries[position], Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_country_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
