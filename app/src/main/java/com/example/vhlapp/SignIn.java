package com.example.vhlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;


public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setTitle("Welcome");
        Backendless.initApp(this, "8BDC2255-6F5F-EBA0-FF6E-60686A950A00", "F9FAC3D3-696E-451B-BA27-E735D5D3ED3D");
        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                Defaults.APPLICATION_ID,
                Defaults.API_KEY );

//        //check if user is already logged in with a valid login
//        AsyncCallback<Boolean> isValidLoginCallback = new AsyncCallback<Boolean>()
//        {
//            @Override
//            public void handleResponse( Boolean response )
//            {
//                Log.i( "MYAPP", "[ASYNC] Is login valid? - " + response );
//                //if logged in, skip to homepage
//                if (response) {
//                    View v = findViewById(R.id.email);
//                    Intent i = new Intent(v.getContext(), Homepage.class);
//                    startActivity(i);
//                }
//            }
//
//            @Override
//            public void handleFault( BackendlessFault fault )
//            {
//                Log.i( "MYAPP", "Error - " + fault );
//            }
//
//        };
//
//        Backendless.UserService.isValidLogin( isValidLoginCallback );

    }


    public void signUp(View v) {
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }

    public void getIn(View v) {

        String email = ((TextView)findViewById(R.id.email)).getText().toString();
        String pass = ((TextView)findViewById(R.id.password)).getText().toString();

        Backendless.UserService.login( email, pass, new AsyncCallback<BackendlessUser>()
        {
            public void handleResponse( BackendlessUser user )
            {
                // user has been logged in
                Intent i = new Intent(v.getContext(), Homepage.class);
                startActivity(i);
                Log.i("loggedin?", "true");
            }

            public void handleFault( BackendlessFault fault )
            {
                // login failed, to get the error code call fault.getCode()
                String error = fault.getCode();
                Toast.makeText(v.getContext(), error, Toast.LENGTH_LONG).show();
                Log.i("loggedin?", "false");
            }
        }, true);


    }

}