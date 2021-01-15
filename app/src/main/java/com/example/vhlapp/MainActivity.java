package com.example.vhlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.backendless.Backendless;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Welcome");
        Backendless.initApp(this, "8BDC2255-6F5F-EBA0-FF6E-60686A950A00", "F9FAC3D3-696E-451B-BA27-E735D5D3ED3D");
        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                Defaults.APPLICATION_ID,
                Defaults.API_KEY );

//        HashMap testObject = new HashMap<>();
//        testObject.put( "foo", "bar" );
//        Backendless.Data.of( "TestTable" ).save(testObject,
//                new AsyncCallback() {
////                    @Override
//                    public void handleResponse(Map response) {
//                        TextView label = new TextView(MainActivity.this);
//                        label.setText("Object is saved in Backendless. Please check in the console.");
//                        setContentView(label);
//                    }
//
//                    @Override
//                    public void handleResponse(Object response) {
//                    }
//
//                    @Override
//                    public void handleFault(BackendlessFault fault) {
//                        Log.e( "MYAPP", "Server reported an error " + fault.getMessage() );
//                    }
//                });
//
//        TextView label = new TextView(this);
//        label.setText("Hello world!");
//
//        setContentView(label);


    }

    public void disabled(View v){
        /* v.setEnabled(false);
        Button b = (Button) v;
        b.setText("disabled");
        Log.d("success","Button Disabled"); */
        findViewById(R.id.button2).setEnabled(false);
        ((Button) findViewById(R.id.button2)).setText("happy bday");
    }

    public void signUp(View v) {
        Intent i = new Intent(this, SignUp.class);
        startActivity(i);
    }

    public void getIn(View v) {
//        TextView t = findViewById(R.id.sourcetxt);
//        String input = t.getText().toString();
//        ((TextView) findViewById(R.id.outputtxt)).setText(input);
//        Toast.makeText(this, "alert", Toast.LENGTH_LONG).show();
//        Log.d("info", input);
        TextView t = findViewById(R.id.email);
        String input = t.getText().toString();
        Intent i = new Intent(this, Homepage.class);
        i.putExtra("name", input);
        startActivity(i);

        //need validation method, compare w/ existing server's user list




    }
}