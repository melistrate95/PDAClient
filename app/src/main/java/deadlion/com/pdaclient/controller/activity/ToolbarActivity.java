package deadlion.com.pdaclient.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.provider.setting.SettingProvider;
import deadlion.com.pdaclient.view.listener.OnToolbarClickListener;

/**
 * Created by Mike on 01.08.2015.
 */
public abstract class ToolbarActivity extends AppCompatActivity {

    Toolbar toolbar;

    protected abstract int getLayoutResId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SettingProvider settingProvider = new SettingProvider(this);
        settingProvider.setTheme();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initToolbar();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new OnToolbarClickListener(this));
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
