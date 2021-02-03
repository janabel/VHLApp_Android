package com.example.vhlapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

public class CalendarFragment extends Fragment {

    private static final String DESCRIBABLE_KEY = "event list key";
    private ArrayList<Event> mEventList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        Alert.makeAlert("Create Appointments", getContext());

        mEventList = (ArrayList<Event>) getArguments().getSerializable(DESCRIBABLE_KEY);

        // handle add button
        FloatingActionButton addButton = view.findViewById(R.id.floatingActionButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddActivity.class);
                startActivity(i);
            }
        });
        CalendarView calender = view.findViewById(R.id.calendar);
        TextView dateView = view.findViewById(R.id.dateView);

        //set textview
        Calendar now = Calendar.getInstance();
        String date = formatDate(now);
        dateView.setText(date);

        // Add Listener in calendar
        calender.setOnDateChangeListener(
                        new CalendarView.OnDateChangeListener() {
                            @Override
                            public void onSelectedDayChange(@NonNull CalendarView view,
                                    int year, int month, int dayOfMonth)
                            {
                                String monthString = new DateFormatSymbols().getMonths()[month];
                                String date = monthString + " " + dayOfMonth + ", " + year;
                                dateView.setText(date);
                            }
                        });

        //set up events- change this!!! it's all hardcode
        if(mEventList != null) {
            if (mEventList.size() >= 1) {
                Button b = view.findViewById(R.id.button8);
                String button_string = mEventList.get(0).getmTitle() + " : " + formatDate(mEventList.get(0).getmDate());
                b.setText(button_string);
                b.setVisibility(View.VISIBLE);
            }
            if (mEventList.size() >= 2) {
                Button b = view.findViewById(R.id.button9);
                String button_string = mEventList.get(1).getmTitle() + " : " + formatDate(mEventList.get(1).getmDate());
                b.setText(button_string);
                b.setVisibility(View.VISIBLE);
            }
            if (mEventList.size() >= 3) {
                Button b = view.findViewById(R.id.button10);
                String button_string = mEventList.get(2).getmTitle() + " : " + formatDate(mEventList.get(2).getmDate());
                b.setText(button_string);
                b.setVisibility(View.VISIBLE);
            }
            if (mEventList.size() >= 4) {
                Button b = view.findViewById(R.id.button11);
                String button_string = mEventList.get(3).getmTitle() + " : " + formatDate(mEventList.get(3).getmDate());
                b.setText(button_string);
                b.setVisibility(View.VISIBLE);
            }
        }

        return view;
    }

    public String formatDate(Calendar calendar){
        String month = new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)];
        return month + " " + calendar.get(Calendar.DATE) + ", " + calendar.get(Calendar.YEAR);
    }

    public static CalendarFragment newInstance(ArrayList<Event> eventList) {
        CalendarFragment fragment = new CalendarFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DESCRIBABLE_KEY, eventList);
        fragment.setArguments(bundle);
        Log.d("yay!!!!", eventList.toString());
        return fragment;
    }



}