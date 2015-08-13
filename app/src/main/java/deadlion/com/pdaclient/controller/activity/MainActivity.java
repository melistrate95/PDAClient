package deadlion.com.pdaclient.controller.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.fragments.ListAllFragment;
import deadlion.com.pdaclient.controller.provider.NavigationDrawerProvider;
import deadlion.com.pdaclient.controller.provider.ToolbarMainProvider;
import deadlion.com.pdaclient.model.enum_model.NavDrawerIdentifier;


public class MainActivity extends ToolbarActivity {

    public static int lastNavDrawerIdentifier = NavDrawerIdentifier.IDENTIFIER_POST;
    NavigationDrawerProvider navigationDrawerProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToolbarMainProvider toolbarProvider = new ToolbarMainProvider(this, toolbar);
        navigationDrawerProvider = new NavigationDrawerProvider(toolbarProvider, this);
        navigationDrawerProvider.initializeNavigationDrawer();
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
