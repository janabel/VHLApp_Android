package com.example.vhlapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.util.Log;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private ArrayList<Event> eventList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Home");

        if (savedInstanceState != null) {
        int savedFragmentId = savedInstanceState.getInt("fragment");
        Fragment savedFragment = getSupportFragmentManager().findFragmentById(savedFragmentId);
        getSupportFragmentManager().beginTransaction().replace(savedFragmentId,
                savedFragment).commit(); }

        //top menu button
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //options in nav_view
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navListener);

        //bottom menu bar
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(bottomNavListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }

    }


    public void onResume() {
        super.onResume();
        Log.d("yay", "ok 10");
        setEventList();
        Intent i = getIntent();
        if (i.hasExtra("event")) {
            Log.d("yay", "ok 11");
            eventList.add((Event) i.getSerializableExtra("event"));
            Log.d("yay", "ok 12");
            saveCalendarEvents();
            Log.d("yay", eventList.toString());
        }

    }
    public void setEventList(){
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonEvents = sharedPref.getString("key", null);
        if (jsonEvents == null){
            eventList = new ArrayList<Event>();
        }else {
            Type type = new TypeToken<List<Event>>() {}.getType();
            eventList = gson.fromJson(jsonEvents, type);
        }
    }

    public void saveCalendarEvents(){
        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Gson gson = new Gson();
        String jsonEvents = gson.toJson(eventList);

        editor.putString("key", jsonEvents);
        editor.apply();
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
                            break;
                        case R.id.nav_notifs:
                            Intent toNotifs = new Intent(MainActivity.this, Notifications.class);
                            startActivity(toNotifs);
                            break;
                        case R.id.nav_settings:
                            Intent toSettings = new Intent(MainActivity.this, Settings.class);
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


    FragmentManager fragMgr = getSupportFragmentManager();
    FragmentTransaction fragTrans = fragMgr.beginTransaction();

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            setTitle("Home");
                            break;
                        case R.id.nav_calendar:

                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            Fragment fragment = CalendarFragment.newInstance(eventList);
                            ft.replace(R.id.fragment_container, fragment);
                            setTitle("Calendar");
                            ft.commit();
                            return true;
                            //selectedFragment = new CalendarFragment();
                            //setTitle("Calendar");
                            //break;
                        case R.id.nav_handbook:
                            selectedFragment = new HandbookFragment();
                            setTitle("Handbook");
                            break;
                        case R.id.nav_emergency:
                            selectedFragment = new EmergencyFragment();
                            setTitle("Emergency");
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment, "MY_FRAGMENT").commit();

                    return true;
                }
            };


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        try {
            Fragment currentFragment = (Fragment) getSupportFragmentManager().findFragmentByTag("MY_FRAGMENT");
            int id = currentFragment.getId();
            outState.putInt("fragment", id);
        } catch(Exception e){
            Log.i("save failed?", "true");
        }
    }


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