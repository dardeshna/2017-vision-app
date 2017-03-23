package com.frc8.team8vision;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

/**
 * This activity represents a setting screen that can be accessed to adjust
 * the HSV threshold. It maintains six sliders, or seekbars, which represent
 * the minimum and maximum HSV.
 *
 * @author Calvin Yan
 */
public class SettingsActivity extends AppCompatActivity {

    private HSVSeekBar[] seekBars = new HSVSeekBar[6];

    private static boolean trackingLeft;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        trackingLeft = preferences.getBoolean("Tracking Left", true);
        RadioButton toCheck = (RadioButton)findViewById(((trackingLeft) ? R.id.target_left : R.id.target_right));
        toCheck.setChecked(true);

        for (int i = 0; i < 6; i++) {
            seekBars[i] = new HSVSeekBar(Constants.kSliderIds[i], Constants.kSliderReadoutIds[i],
                                         Constants.kSliderDefaultValues[i], Constants.kSliderNames[i], this);
        }
    }

    public void onRadioButtonClicked(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        trackingLeft = view.getId() == R.id.target_left;
        editor.putBoolean("Tracking Left", trackingLeft);
        editor.apply();
    }

    public static boolean trackingLeftTarget() { return trackingLeft; }

}