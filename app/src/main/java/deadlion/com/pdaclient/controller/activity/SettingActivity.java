package deadlion.com.pdaclient.controller.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.fragments.SettingFragment;
import deadlion.com.pdaclient.controller.fragments.SettingPostFragment;
import deadlion.com.pdaclient.controller.provider.ToolbarSettingProvider;

/**
 * Created by Mike on 02.08.2015.
 */
public class SettingActivity extends ToolbarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolbarSettingProvider toolbarProvider = new ToolbarSettingProvider(this, toolbar);
        toolbarProvider.buildSettingToolbar();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, new SettingFragment()).commit();
    }
}
