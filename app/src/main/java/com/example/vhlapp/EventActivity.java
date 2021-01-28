package com.example.vhlapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class EventActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String eventType;
    String date;
    String time;
    String notification_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // event type dropdown menu
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.event_types_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // notification type dropdown menu
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.notification_types_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);



    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        eventType = (String) parent.getItemAtPosition(pos);

        Spinner spin = (Spinner)parent;
        if(spin.getId() == R.id.spinner)
        {
            eventType = (String) parent.getItemAtPosition(pos);
        }
        if(spin.getId() == R.id.spinner2)
        {
            notification_time = (String) parent.getItemAtPosition(pos);
        }

    }
    public void onNothingSelected(AdapterView<?> parent) {
        eventType = "General Appointment";

        Spinner spin = (Spinner)parent;
        if(spin.getId() == R.id.spinner)
        {
            eventType = "General Appointment";
        }
        if(spin.getId() == R.id.spinner2)
        {
            notification_time = "no notification before";
        }


    }
    public void dateButtonHandler(View v){
        //get current date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        ((Button) v).setText(date);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void timeButtonHandler(View v){
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        Calendar timeCalendar = Calendar.getInstance();
                        timeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        timeCalendar.set(Calendar.MINUTE, minute);
                        String am_pm = "PM";
                        if (timeCalendar.get(Calendar.AM_PM) == Calendar.AM)
                            am_pm = "AM";
                        time = hourOfDay + ":" + minute + " " + am_pm;
                        ((Button) v).setText(time);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void cancelButtonHandler(View v){
        Toast.makeText(this, "Event Canceled", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void saveButtonHandler(View v){
        Toast.makeText(this, "Event Saved", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


}