package fr.mabreizh.droid;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class SettingsActivity extends PreferenceActivity {

	public static final String START = "start";
	public static final String END = "end";
	public static final String START_HOUR = "hstart";
	public static final String END_HOUR = "hend";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
	}

}
