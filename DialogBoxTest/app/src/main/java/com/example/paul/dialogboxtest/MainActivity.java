package com.example.paul.dialogboxtest;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.paul.dialogboxtest.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button removeButton= (Button) findViewById(R.id.button1);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment myFragment = new MyDialogFragment();

                myFragment.show(getFragmentManager(), "theDialog");
            }
        });
    }


    // This method creates the menu on the app
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Called when a options menu item is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // We check what menu item was clicked and show a Toast
        if (id == R.id.action_settings) {

            // A DialogFragment is a Fragment you can place over top
            // the current Activity. A Fragment is like an interface
            // block that you can place into an Activity.
            // The FrgamentManager allows you to interact with the
            // Fragment
            DialogFragment myFragment = new MyDialogFragment();

            myFragment.show(getFragmentManager(), "theDialog");

            return true;

            // If exit was clicked close the app
        } else if (id == R.id.exit_the_app) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}