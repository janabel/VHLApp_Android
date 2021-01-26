package com.example.vhlapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;
import java.text.DateFormatSymbols;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Calendar;

public class CalendarFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        // handle add button
        FloatingActionButton addButton = view.findViewById(R.id.floatingActionButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                v.setEnabled(false);
            }
        });
        CalendarView calender = view.findViewById(R.id.calendar);
        TextView dateView = view.findViewById(R.id.dateView);

        //set textview
        String date = formatDate();
        dateView.setText(date);

        // Add Listener in calendar
        calender.setOnDateChangeListener(
                        new CalendarView.OnDateChangeListener() {
                            @Override
                            public void onSelectedDayChange(@NonNull CalendarView view,
                                    int year, int month, int dayOfMonth)
                            {
                                formatDate();
                                String monthString = new DateFormatSymbols().getMonths()[month];
                                String date = monthString + " " + dayOfMonth + ", " + year;
                                dateView.setText(date);
                            }
                        });

        return view;
    }

    public String formatDate(){
        Calendar now = Calendar.getInstance();
        String month = new DateFormatSymbols().getMonths()[now.get(Calendar.MONTH)];
        return month + " " + now.get(Calendar.DATE) + ", " + now.get(Calendar.YEAR);
    }



}