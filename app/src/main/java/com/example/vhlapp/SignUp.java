package com.example.vhlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import org.w3c.dom.Text;

import java.util.Map;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Backendless.initApp(this, "8BDC2255-6F5F-EBA0-FF6E-60686A950A00", "F9FAC3D3-696E-451B-BA27-E735D5D3ED3D");
        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                Defaults.APPLICATION_ID,
                Defaults.API_KEY );
    }

    public void signIn(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void getIn(View v) {
//        Log.d("info", input);
        TextView t = findViewById(R.id.email);
        String input = t.getText().toString();
        Intent i = new Intent(this, Homepage.class);
        i.putExtra("name", input);
        startActivity(i);

        //need to add data into server user list
//        HashMap testObject = new HashMap<>();
//        testObject.put( "foo", "bar" );
        String email = ((TextView)findViewById(R.id.email)).getText().toString()
        Backendless.Data.of( "Users" ).save(email,
                new AsyncCallback() {
                    @Override
                    public void handleResponse(Object response) {
                        TextView t = findViewById(R.id.email);
                        String input = t.getText().toString();
                        Intent i = new Intent(this, Homepage.class);
                        i.putExtra("name", input);
                        startActivity(i);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Log.e( "MYAPP", "Server reported an error " + fault.getMessage() );
                        Toast.makeText(SignUp, "Server Error: Please try again later", Toast.LENGTH_LONG);
                    }
                });
    }
}