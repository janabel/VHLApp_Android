package com.example.vhlapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    }

    public void toSignIn(View v) {
        Intent i = new Intent(this, SignIn.class);
        startActivity(i);
    }


    public void signUp(View v) {
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