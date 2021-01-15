package com.example.vhlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Text;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        setTitle("Welcome " + name);
        String buttonmsg = name + "'s Inventory";
        ((Button)findViewById(R.id.button)).setText(buttonmsg);

    }

    public void newpage(View v) {
        Intent i = new Intent(this, newPage.class);
        startActivity(i);
    }

    public void logout(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}