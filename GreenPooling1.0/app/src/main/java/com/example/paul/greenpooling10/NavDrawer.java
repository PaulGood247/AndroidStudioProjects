package com.example.paul.greenpooling10;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NavDrawer extends AppCompatActivity
{
    public DrawerLayout drawerLayout;
    public ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;

    protected void onCreate(Bundle savedInstanceState, int resLayoutID)
    {
        super.onCreate(savedInstanceState);
        setContentView(resLayoutID);

        String[] drawerListEntries = new String[]{"Bla bla", "Profile", "Home"};

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            toolbar.bringToFront();
        }

        //drawerToggle = new ActionBarDrawerToggle( this, drawerLayout, R.mipmap.ic_drawer, );
        Log.i("Biiii", ""+toolbar);

        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.app_name, R.string.menu);

        drawerLayout.addDrawerListener(drawerToggle);

        drawerList = (ListView) findViewById(R.id.drawer_list);
        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, drawerListEntries));

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
                //map.drawerClickEvent(pos);
                String selectedValue = (String) drawerList.getAdapter().getItem(pos);
                Toast.makeText(getBaseContext(), selectedValue, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return drawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}