package com.example.paul.greenpooling10;

/**
 * Created by Paul on 08/11/2016.
 */

import android.os.Bundle;
import android.view.Menu;

public class Profile extends NavDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_home);
        super.onCreate(savedInstanceState, R.layout.drawer_layout);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //return super.onCreateOptionsMenu(menu);
        return false;
    }


}
