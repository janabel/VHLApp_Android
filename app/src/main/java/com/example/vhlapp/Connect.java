package com.example.vhlapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;

import java.util.List;
import java.util.Map;

public class Connect extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        ImageView fb = findViewById(R.id.fbIcon);
        ImageView email = findViewById(R.id.email_icon);
        ImageView phone = findViewById(R.id.phone_icon);
        ImageView twt = findViewById(R.id.twitter_icon);
        ImageView blog = findViewById(R.id.blog_icon);
        ImageView ig = findViewById(R.id.insta_icon);

        fb.setClickable(true);
        email.setClickable(true);
        phone.setClickable(true);
        twt.setClickable(true);
        blog.setClickable(true);
        ig.setClickable(true);

        Backendless.initApp(this, "8BDC2255-6F5F-EBA0-FF6E-60686A950A00", "F9FAC3D3-696E-451B-BA27-E735D5D3ED3D");
        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                Defaults.APPLICATION_ID,
                Defaults.API_KEY );

    }

    public void phone(View v) {
        String whereClause = "name = 'Phone'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);

        Backendless.Data.of("WebLink").find(queryBuilder, new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> webLink) {
                // every loaded object from the "WebLinks" table is now an individual java.util.Map
                Map URLmap = webLink.get(0);
                String URL = (String) URLmap.get("url");

                Uri number = Uri.parse("tel:" + URL);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                startActivity(callIntent);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                String error = fault.getCode();
                Log.d("error: ", error);
                Toast.makeText(getBaseContext(), "Error: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void email(View v) {

        String whereClause = "name = 'Email'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);

        Backendless.Data.of("WebLink").find(queryBuilder, new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> webLink) {
                // every loaded object from the "WebLinks" table is now an individual java.util.Map
                Map URLmap = webLink.get(0);
                String URL = (String) URLmap.get("url");

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                // The intent does not have a URI, so declare the "text/plain" MIME type
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {URL}); // recipients
                startActivity(emailIntent);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                String error = fault.getCode();
                Log.d("error: ", error);
                Toast.makeText(getBaseContext(), "Error: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void instagram(View v) {
        String whereClause = "name = 'Instagram'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);

        Backendless.Data.of("WebLink").find(queryBuilder, new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> webLink) {
                // every loaded object from the "WebLinks" table is now an individual java.util.Map
                Map URLmap = webLink.get(0);
                String URL = (String) URLmap.get("url");

                Uri insta = Uri.parse(URL);
                Intent i = new Intent(Intent.ACTION_VIEW, insta);
                try {
                    startActivity(i);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getBaseContext(), "Error: App not installed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                String error = fault.getCode();
                Log.d("error: ", error);
                Toast.makeText(getBaseContext(), "Error: " + error, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void twitter(View v) {
        String whereClause = "name = 'Twitter'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);

        Backendless.Data.of("WebLink").find(queryBuilder, new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> webLink) {
                // every loaded object from the "WebLinks" table is now an individual java.util.Map
                Map URLmap = webLink.get(0);
                String URL = (String) URLmap.get("url");

                Uri insta = Uri.parse(URL);
                Intent i = new Intent(Intent.ACTION_VIEW, insta);
                try {
                    startActivity(i);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getBaseContext(), "Error: App not installed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                String error = fault.getCode();
                Log.d("error: ", error);
                Toast.makeText(getBaseContext(), "Error: " + error, Toast.LENGTH_LONG).show();
            }
        });

    }


    public void facebook(View v) {
        Log.d("was clicked", "yes");
        String whereClause = "name = 'Facebook'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);

        Backendless.Data.of("WebLink").find(queryBuilder, new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> webLink) {
                // every loaded object from the "WebLinks" table is now an individual java.util.Map
                Map URLmap = webLink.get(0);
                String URL = (String) URLmap.get("url");

                Intent i = new Intent(getBaseContext(), WebHomepage.class);
                i.putExtra("URL", URL);
                i.putExtra("pageName", "Facebook");
                startActivity(i);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                String error = fault.getCode();
                Log.d("sad", error);
                Toast.makeText(getBaseContext(), "sad" + error, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void blog(View v) {
        String whereClause = "name = 'Blog'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause(whereClause);

        Backendless.Data.of("WebLink").find(queryBuilder, new AsyncCallback<List<Map>>() {
            @Override
            public void handleResponse(List<Map> webLink) {
                // every loaded object from the "WebLinks" table is now an individual java.util.Map
                Map URLmap = webLink.get(0);
                String URL = (String) URLmap.get("url");

                Intent i = new Intent(getBaseContext(), WebHomepage.class);
                i.putExtra("URL", URL);
                i.putExtra("pageName", "Blog");
                startActivity(i);
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                String error = fault.getCode();
                Log.d("error: ", error);
                Toast.makeText(getBaseContext(), "Error: " + error, Toast.LENGTH_LONG).show();
            }
        });

    }

}
