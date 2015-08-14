package deadlion.com.pdaclient.controller.fragments;

import android.content.Intent;
import android.os.Bundle;

import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.prefs.MaterialListPreference;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.activity.SettingPostActivity;
import deadlion.com.pdaclient.controller.provider.setting.SettingProvider;

/**
 * Created by Mike on 02.08.2015.
 */
public class SettingFragment extends PreferenceFragment {

    MaterialListPreference themePreference;
    CheckBoxPreference postFontPreference;
    Preference aboutPreference;
    Preference postAdapterPreference;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        SettingProvider settingProvider = new SettingProvider(getActivity());

        themePreference = (MaterialListPreference) findPreference("themePreference");
        settingProvider.getTheme(themePreference);

        postFontPreference = (CheckBoxPreference) findPreference("postFontPreference");
        settingProvider.getFont(postFontPreference);

        postAdapterPreference = findPreference("postAdapterPreference");
        postAdapterPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(getActivity(), SettingPostActivity.class);
                getActivity().startActivity(intent);
                return false;
            }
        });
        aboutPreference = findPreference("aboutPreference");
        aboutPreference.setOnPreferenceClickListener(new android.preference.Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(android.preference.Preference preference) {
                createAboutDialog().show();
                return false;
            }
        });
    }

    private AlertDialog createAboutDialog() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.about_dialog, (ViewGroup) getActivity().findViewById(R.id.toast_about));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(layout);
        builder.setTitle("О программе");
        builder.setPositiveButton("ОК", null);
        return builder.create();
    }
}
