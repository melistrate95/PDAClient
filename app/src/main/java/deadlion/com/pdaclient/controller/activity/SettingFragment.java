package deadlion.com.pdaclient.controller.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import deadlion.com.pdaclient.R;

/**
 * Created by Mike on 02.08.2015.
 */
public class SettingFragment extends PreferenceFragment{

    Preference exitPreference;
    Preference themePreference;
    Preference postFontPreference;
    Preference aboutPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        exitPreference = (Preference) findPreference("exitPreference");
        themePreference = (Preference) findPreference("themePreference");
        postFontPreference = (Preference) findPreference("postFontPreference");
        aboutPreference = (Preference) findPreference("aboutPreference");
    }
}
