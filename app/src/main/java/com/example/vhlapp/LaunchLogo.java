package com.example.vhlapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class LaunchLogo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_logo);

        //check if user is already logged in with a valid login
        AsyncCallback<Boolean> isValidLoginCallback = new AsyncCallback<Boolean>()
        {
            @Override
            public void handleResponse( Boolean response )
            {
                Log.i( "MYAPP", "[ASYNC] Is login valid? - " + response );
                //if logged in, skip to homepage
                if (response) {
                    View v = findViewById(R.id.VHLlogo);
                    Intent i = new Intent(v.getContext(), Homepage.class);
                    startActivity(i);
                }
            }

            @Override
            public void handleFault( BackendlessFault fault )
            {
                Log.i( "MYAPP", "Error - " + fault );
            }

        };

        Backendless.UserService.isValidLogin( isValidLoginCallback );

    }
}