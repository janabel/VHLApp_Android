package com.example.vhlapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.google.android.material.navigation.NavigationView;

public class Notifications extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        setTitle("Notifications");

        //top menu button
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //options in nav_view
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navListener);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private NavigationView.OnNavigationItemSelectedListener navListener =
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_main:
                            Intent toMain = new Intent(Notifications.this, MainActivity.class);
                            startActivity(toMain);
                            break;
                        case R.id.nav_notifs:
                            break;
                        case R.id.nav_settings:
                            Intent toSettings = new Intent(Notifications.this, Settings.class);
                            startActivity(toSettings);
                            break;
                        case R.id.nav_logout:
                            logout();
                            break;
                    }
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
            };


    public void logout() {
        Backendless.UserService.logout(new AsyncCallback<Void>()
        {
            public void handleResponse( Void response )
            {
                // user has been logged out
                Intent i = new Intent(getApplicationContext(), SignIn.class);
                startActivity(i);
            }

            public void handleFault( BackendlessFault fault )
            {
                // something went wrong and logout failed, to get the error code call fault.getCode()
                String error = fault.getCode();
                Toast.makeText(getApplicationContext(), "Error logging out: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }

}