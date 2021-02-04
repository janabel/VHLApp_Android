package com.example.vhlapp;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class Settings extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");

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

}


