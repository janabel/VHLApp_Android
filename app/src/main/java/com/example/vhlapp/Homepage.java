package com.example.vhlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //need the following initApp whenever using server data
        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                Defaults.APPLICATION_ID,
                Defaults.API_KEY );
        //

        BackendlessUser user = Backendless.UserService.CurrentUser();
//        Boolean b = user.equals(null);
//        Log.i("user null check", "is user null? - " + b);
//        String name = (String) user.getProperty("name");
//        setTitle("Welcome, " + name);

    }

    public void newpage(View v) {
        Intent i = new Intent(this, newPage.class);
        startActivity(i);
    }

    public void logout(View v) {

        Backendless.UserService.logout( new AsyncCallback<Void>()
        {
            public void handleResponse( Void response )
            {
                // user has been logged out
                Intent i = new Intent(Homepage.this, SignIn.class);
                startActivity(i);
            }

            public void handleFault( BackendlessFault fault )
            {
                // something went wrong and logout failed, to get the error code call fault.getCode()
                String error = fault.getCode();
                Toast.makeText(Homepage.this, "Error logging out: " + error, Toast.LENGTH_LONG).show();
            }
        });

    }
}