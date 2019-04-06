package com.marakhovskyi.artem.colours;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private static final String RED_KEY = "RED";
    private static final String GREEN_KEY = "GREEN";
    private static final String BLUE_KEY = "BLUE";

    private LinearLayout colourPanel;
    private SeekBar redSeekBar;
    private SeekBar blueSeekBar;
    private SeekBar greenSeekBar;

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateColourPanel();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);
        colourPanel = (LinearLayout) findViewById(R.id.colourPanel);

        greenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        blueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        updateColourPanel();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (outState != null) {
            outState.putInt(BLUE_KEY, blueSeekBar.getProgress());
            outState.putInt(GREEN_KEY, greenSeekBar.getProgress());
            outState.putInt(RED_KEY, redSeekBar.getProgress());
        }
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        blueSeekBar.setProgress(savedInstanceState.getInt(BLUE_KEY));
        greenSeekBar.setProgress(savedInstanceState.getInt(GREEN_KEY));
        redSeekBar.setProgress(savedInstanceState.getInt(RED_KEY));
    }

    private void updateColourPanel() {
        colourPanel.setBackgroundColor(
                ((0xFF << 24) & 0xFF000000)
                        + ((redSeekBar.getProgress() << 16) & 0x00FF0000)
                        + ((greenSeekBar.getProgress() << 8) & 0x0000FF00)
                        + (blueSeekBar.getProgress() & 0x000000FF));

    }
}
