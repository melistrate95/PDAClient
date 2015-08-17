package deadlion.com.pdaclient.controller.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.KeyEvent;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.fragments.ListAllFragment;
import deadlion.com.pdaclient.controller.provider.toolbar.NavigationDrawerProvider;
import deadlion.com.pdaclient.controller.provider.toolbar.ToolbarMainProvider;
import deadlion.com.pdaclient.model.enum_model.NavDrawerIdentifier;
import deadlion.com.pdaclient.model.enum_model.SpinnerCategory;


public class MainActivity extends ToolbarActivity {

    public static int lastNavDrawerIdentifier = NavDrawerIdentifier.IDENTIFIER_POST;
    public static int lastSpinnerCategory = SpinnerCategory.NEWS_CATEGORY;
    NavigationDrawerProvider navigationDrawerProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolbarMainProvider toolbarProvider = new ToolbarMainProvider(this, toolbar);
        navigationDrawerProvider = new NavigationDrawerProvider(toolbarProvider, this);
        navigationDrawerProvider.initializeNavigationDrawer();
        if (savedInstanceState == null) {
            FragmentManager manager = getFragmentManager();
            manager.beginTransaction().replace(R.id.container, new ListAllFragment(), "ListAllFragment").commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationDrawerProvider.setDrawerItemSelection(lastNavDrawerIdentifier);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (navigationDrawerProvider.navigationDrawer.isDrawerOpen()) {
            navigationDrawerProvider.navigationDrawer.closeDrawer();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
