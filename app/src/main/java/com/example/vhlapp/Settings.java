package com.example.vhlapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.google.android.material.navigation.NavigationView;

public class Settings extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");

        //top menu button
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //options in nav_view
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navListener);


        //get views
        SeekBar slider = findViewById(R.id.seekBar);
        TextView tv = findViewById(R.id.textView_fontsize);
        Switch flip = findViewById(R.id.appInfoSwitch);

        //setting app info switch listener
        flip.setChecked(getDefaultsBoolean("app info", getBaseContext()));

        if (getDefaultsBoolean("app info", getBaseContext())) {
            //alert made if show app info is checked
            Alert.makeAlert("Settings", Settings.this);
        }

        flip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setDefaultsBoolean("app info", isChecked, getBaseContext());
            }
        });

        //showing saved font size preference
        int prog = getDefaultsInt("fontSize", getBaseContext());
        slider.setProgress(prog);
        String text = "Font Size: " + prog;
        tv.setText(text);
        tv.setTextSize(prog);

        //setting slider listener
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //updates bar value in preferences to retrieve elsewhere
                setProgress(progress);
                setDefaultsInt("fontSize", progress, getBaseContext());

                //text updates with bar value
                TextView tv = findViewById(R.id.textView_fontsize);
                String text = "Font Size: " + progress;
                tv.setText(text);
                tv.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

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
                            Intent toMain = new Intent(Settings.this, MainActivity.class);
                            startActivity(toMain);
                            break;
                        case R.id.nav_notifs:
                            Intent toNotifs = new Intent(Settings.this, Notifications.class);
                            startActivity(toNotifs);
                            break;
                        case R.id.nav_settings:
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


