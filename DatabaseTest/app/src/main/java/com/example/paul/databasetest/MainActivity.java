package com.example.paul.databasetest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    String task ="TestTask";
    String desc ="Bla Bla Bla Bla Bla Bla Bla Bla Bla";
    String result;
    Cursor myCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SampleDBManager db = new SampleDBManager(this);

        try {
            db.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        db.insertSomething(task, desc);

        try {
            myCursor=db.getSomeThing(1);
            if(myCursor.moveToFirst())
            {
                result=myCursor.getString(1);
            }
            else{
                result= "Nothing in database!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db.close();

        TextView text = (TextView) findViewById(R.id.resultText);
        text.setText(result );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
