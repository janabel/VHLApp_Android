package com.example.vhlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.backendless.Backendless;

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

        Backendless.initApp(this, "8BDC2255-6F5F-EBA0-FF6E-60686A950A00", "F9FAC3D3-696E-451B-BA27-E735D5D3ED3D");
        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                Defaults.APPLICATION_ID,
                Defaults.API_KEY );

    }

    public void newpage(View v) {
        Intent i = new Intent(this, newPage.class);
        startActivity(i);
    }

    public void logout(View v) {
        Intent i = new Intent(this, SignIn.class);
        startActivity(i);
    }
}