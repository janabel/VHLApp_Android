package com.example.vhlapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.io.Serializable;

public class EventActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private String eventType;
    private Calendar date;
    private Calendar time;
    private String notification_time;
    private String title;
    private String specialist;
    private String description;


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


    public void handleText(){
        EditText title_view = findViewById(R.id.title);
        title = title_view.getText().toString();

        EditText specialist_view = findViewById(R.id.specialist);
        specialist = specialist_view.getText().toString();

        EditText description_view = findViewById(R.id.editTextTextMultiLine);
        description = description_view.getText().toString();

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
            notification_time = "No notification before";
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
                        Calendar dateCalendar = Calendar.getInstance();
                        dateCalendar.set(Calendar.YEAR, year);
                        dateCalendar.set(Calendar.MONTH, monthOfYear);
                        dateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String date_string = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        ((Button) v).setText(date_string);
                        date = dateCalendar;
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
                        String time_string = hourOfDay + ":" + minute + " " + am_pm;
                        ((Button) v).setText(time_string);
                        time = timeCalendar;
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
        Log.d("yay", "ok");
        handleText();
        Event event = new Event(title, specialist, eventType, date, time, notification_time, description, true);
        Toast.makeText(this, "Event Saved", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, MainActivity.class);
        Log.d("yay", "ok 1");
        i.putExtra("event", (Serializable) event);
        Log.d("yay", "ok 2");
        startActivity(i);
    }


}