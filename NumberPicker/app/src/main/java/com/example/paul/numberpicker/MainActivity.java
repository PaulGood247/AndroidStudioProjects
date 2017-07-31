package com.example.paul.numberpicker;


import android.app.Activity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends Activity {
    //DateFormat fmtDateAndTime=DateFormat.getDateTimeInstance();
    //TextView dateAndTimeLabel;
    Calendar dateAndTime=Calendar.getInstance();


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);

        Button btn=(Button)findViewById(R.id.timeBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(MainActivity.this,
                        t,
                        dateAndTime.get(Calendar.HOUR_OF_DAY),
                        dateAndTime.get(Calendar.MINUTE),
                        true).show();
            }
        });
    }

    TimePickerDialog.OnTimeSetListener t=new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay,
                              int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            updateLabel();
        }
    };

    private void updateLabel() {
        //dateAndTimeLabel.setText(fmtDateAndTime.format(dateAndTime.getTime()));
    }
}