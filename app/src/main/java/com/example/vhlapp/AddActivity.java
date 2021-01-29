package com.example.vhlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }
    public void newEventHandler(View v){
        Intent i = new Intent(this, EventActivity.class);
        startActivity(i);
    }
    public void newPersonalizedNotificationHandler(View v){
        Intent i = new Intent(this, PersonalizedNotification.class);
        startActivity(i);
    }

}