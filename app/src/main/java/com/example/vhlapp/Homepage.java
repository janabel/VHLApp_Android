package com.example.vhlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        ArrayList<String> arrayList = new ArrayList<>();
        ListView listview = (ListView) findViewById(R.id.listview);

        arrayList.add("Homepage");
        arrayList.add("Clinical Care Center Feedback Form");
        arrayList.add("MyVHL");
        arrayList.add("VHL Manifestations");
        arrayList.add("News and Events");
        arrayList.add("VHL Facts");
        arrayList.add("Find a VHL Clinical Care Center");
        arrayList.add("Active Surveillance Guidelines");
        arrayList.add("VHL Handbook");
        arrayList.add("Connect");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, arrayList);
        listview.setAdapter(arrayAdapter);


        //need the following initApp whenever using server data
        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                Defaults.APPLICATION_ID,
                Defaults.API_KEY );

//        BackendlessUser user = Backendless.UserService.CurrentUser();
//        Boolean b = user.equals(null);
//        Log.i("user null check", "is user null? - " + b);
//        String name = (String) user.getProperty("name");
//        setTitle("Welcome, " + name);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String whereClause = "position = " + position;
                Log.d("whereclause: ", whereClause);
                DataQueryBuilder queryBuilder = DataQueryBuilder.create();
                queryBuilder.setWhereClause( whereClause );

                Backendless.Data.of( "WebLinks" ).find( queryBuilder,
                        new AsyncCallback<List<Map>>(){
                            @Override
                            public void handleResponse( List<Map> webLink )
                            {
                                // every loaded object from the "WebLinks" table is now an individual java.util.Map
                                Log.d("success?", "yes");
                                Map URLmap = webLink.get(0);
                                String URL = (String) URLmap.get("URL");

                                Intent i = new Intent(Homepage.this, WebHomepage.class);
                                i.putExtra("URL", URL);
                                startActivity(i);

                            }
                            @Override
                            public void handleFault( BackendlessFault fault )
                            {
                                // an error has occurred, the error code can be retrieved with fault.getCode()
                                String error = fault.getCode();
                                Log.d("error: ", error);
                                Toast.makeText(Homepage.this, "Error: " + error, Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });

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