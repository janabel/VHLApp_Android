package com.example.vhlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//        Backendless.initApp(this, "8BDC2255-6F5F-EBA0-FF6E-60686A950A00", "F9FAC3D3-696E-451B-BA27-E735D5D3ED3D");
//        Backendless.setUrl( Defaults.SERVER_URL );
//        Backendless.initApp( getApplicationContext(),
//                Defaults.APPLICATION_ID,
//                Defaults.API_KEY );
    }

    public void signIn(View v) {
        Intent i = new Intent(this, SignIn.class);
        startActivity(i);
    }


    public void getIn(View v) {
//        Log.d("info", input);

        //need to add user data into server user list
        String email = ((TextView)findViewById(R.id.email)).getText().toString();
        String pass = ((TextView)findViewById(R.id.password)).getText().toString();
        String name = ((TextView)findViewById(R.id.name)).getText().toString();

        BackendlessUser user = new BackendlessUser();
        user.setProperty( "email", email );
        user.setPassword( pass );
        user.setProperty( "name", name );

//        if(!email.equals("") && !pass.equals("") && !pass.equals("")) {
            Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                public void handleResponse(BackendlessUser registeredUser) {
                    // user has been registered and now can login
                    TextView t = findViewById(R.id.email);
                    String input = t.getText().toString();
                    Intent i = new Intent(v.getContext(), MainActivity.class);
                    i.putExtra("name", input);
                    startActivity(i);
                }

                public void handleFault(BackendlessFault fault) {
                    // an error has occurred, the error code can be retrieved with fault.getCode()
                    String error = fault.getCode();
                    Toast.makeText(v.getContext(), error, Toast.LENGTH_LONG).show();
                }
            });
//        } else {
//            Toast.makeText(v.getContext(), "Cannot leave Email, Password, or Name blank", Toast.LENGTH_LONG).show();
//        }

    }
}