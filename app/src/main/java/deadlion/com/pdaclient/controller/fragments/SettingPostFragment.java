package deadlion.com.pdaclient.controller.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.afollestad.materialdialogs.prefs.MaterialListPreference;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.provider.setting.SettingProvider;

/**
 * Created by Михаил on 11.08.2015.
 */
public class SettingPostFragment extends PreferenceFragment {

    MaterialListPreference loadImagePreference;
    MaterialListPreference postViewPreference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_post_view);
        SettingProvider settingProvider = new SettingProvider(getActivity());

        loadImagePreference = (MaterialListPreference) findPreference("loadImagePreference");
        settingProvider.getLoadImage(loadImagePreference);

        postViewPreference = (MaterialListPreference) findPreference("postViewPreference");
        settingProvider.getPostView(postViewPreference);
    }
}
