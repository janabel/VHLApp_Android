package com.example.vhlapp;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class Settings extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle("Settings");

        try {
            int prog = getDefaults("fontSize", getBaseContext());
            SeekBar slider = findViewById(R.id.seekBar);
            slider.setProgress(prog);
            TextView tv = findViewById(R.id.textView_fontsize);
            String text = "Font Size: " + prog;
            tv.setText(text);

        } catch (Exception e) {
            e.printStackTrace();
        }

        SeekBar slider = findViewById(R.id.seekBar);

        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //updates bar value in preferences to retrieve elsewhere
                setProgress(progress);
                setDefaults("fontSize", progress, getBaseContext());

                //text updates with bar value
                TextView tv = findViewById(R.id.textView_fontsize);
                String text = "Font Size: " + progress;
                tv.setText(text);
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


