package com.example.vhlapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class LaunchLogo extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_logo);

        //need the following initApp whenever using server data
        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                Defaults.APPLICATION_ID,
                Defaults.API_KEY );

        //check if user is already logged in with a valid login
        AsyncCallback<Boolean> isValidLoginCallback = new AsyncCallback<Boolean>()
        {
            @Override
            public void handleResponse( Boolean response )
            {
                Log.i( "MYAPP", "[ASYNC] Is login valid? - " + response );
                //if logged in, skip to homepage
                if (response) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(LaunchLogo.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }, 2000);

                } else {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(LaunchLogo.this, SignIn.class);
                            startActivity(intent);
                        }
                    }, 2000);
                }
            }

            @Override
            public void handleFault( BackendlessFault fault )
            {
                View v = findViewById(R.id.VHLlogo);
                Log.i( "MYAPP", "Error - " + fault );
                String error = fault.getCode();
                Toast.makeText(v.getContext(), error, Toast.LENGTH_LONG).show();
            }

        };

        Backendless.UserService.isValidLogin( isValidLoginCallback );

    }
}