package com.cognizant.settingsapp;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SimpleSettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_simple);
    }
}