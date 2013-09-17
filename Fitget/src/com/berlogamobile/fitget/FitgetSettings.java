package com.berlogamobile.fitget;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.berlogamobile.fitget.R;

public class FitgetSettings extends PreferenceActivity {
    @SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);

    }

}
