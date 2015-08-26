package deadlion.com.pdaclient.controller.activity;

import android.app.FragmentManager;
import android.os.Bundle;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.fragments.SettingPostFragment;
import deadlion.com.pdaclient.controller.provider.toolbar.ToolbarSettingProvider;

/**
 * Created by Михаил on 12.08.2015.
 */
public class SettingPostActivity extends ToolbarActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolbarSettingProvider toolbarProvider = new ToolbarSettingProvider(this, toolbar);
        toolbarProvider.buildSettingAdapterToolbar();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, new SettingPostFragment()).commit();
    }
}

