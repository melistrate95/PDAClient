package deadlion.com.pdaclient.controller.provider;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.activity.MainActivity;
import deadlion.com.pdaclient.controller.activity.SettingActivity;
import deadlion.com.pdaclient.controller.fragments.ListAllFragment;
import deadlion.com.pdaclient.model.enum_model.NavDrawerIdentifier;

/**
 * Created by Михаил on 11.08.2015.
 */
public class NavigationDrawerProvider {

    private ToolbarMainProvider toolbarProvider;
    private Activity activity;
    public Drawer navigationDrawer;


    public NavigationDrawerProvider(ToolbarMainProvider toolbarProvider, Activity activity) {
        this.toolbarProvider = toolbarProvider;
        this.activity = activity;
    }

    public void initializeNavigationDrawer() {

        final AccountHeader accountHeader = createAccountHeader();

        navigationDrawer = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbarProvider.getToolbar())
                .withAccountHeader(accountHeader)
                .withSelectedItem(-1)
                .addDrawerItems(
                        initializeDrawerItems()
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                        int identifier = iDrawerItem.getIdentifier();
                        FragmentManager manager = activity.getFragmentManager();
                        toolbarProvider.buildToolbar(identifier);
                        switch (identifier) {
                            case NavDrawerIdentifier.IDENTIFIER_POST:
                                MainActivity.lastNavDrawerIdentifier = identifier;
                                manager.beginTransaction().replace(R.id.container, new ListAllFragment()).commit();
                                break;
                            case NavDrawerIdentifier.IDENTIFIER_FAVORITE_POST:
                                MainActivity.lastNavDrawerIdentifier = identifier;
                                manager.beginTransaction().replace(R.id.container, new ListAllFragment()).commit();
                                break;
                            case NavDrawerIdentifier.IDENTIFIER_FORUM:
                                MainActivity.lastNavDrawerIdentifier = identifier;
                                manager.beginTransaction().replace(R.id.container, new ListAllFragment()).commit();
                                break;
                            case NavDrawerIdentifier.IDENTIFIER_FAVORITE_TOPIC:
                                MainActivity.lastNavDrawerIdentifier = identifier;
                                break;
                            case NavDrawerIdentifier.IDENTIFIER_DEVDB:
                                MainActivity.lastNavDrawerIdentifier = identifier;
                                manager.beginTransaction().replace(R.id.container, new ListAllFragment()).commit();
                                break;
                            case NavDrawerIdentifier.IDENTIFIER_SETTING:
                                Intent intent = new Intent(activity, SettingActivity.class);
                                activity.startActivity(intent);
                                break;
                        }
                        return false;
                    }
                })
                .build();
    }

    private IDrawerItem[] initializeDrawerItems() {
        return new IDrawerItem[]{
                new SectionDrawerItem()
                        .withName(R.string.item_section_post),
                new SecondaryDrawerItem()
                        .withName(R.string.item_news)
                        .withIcon(R.drawable.ic_news)
                        .withIdentifier(NavDrawerIdentifier.IDENTIFIER_POST),
                new SecondaryDrawerItem()
                        .withName(R.string.item_favorite)
                        .withIcon(R.drawable.ic_favorite)
                        .withIdentifier(NavDrawerIdentifier.IDENTIFIER_FAVORITE_POST),
                new SectionDrawerItem()
                        .withName(R.string.item_section_forum),
                new SecondaryDrawerItem()
                        .withName(R.string.item_forum)
                        .withIcon(R.drawable.ic_forum)
                        .withIdentifier(NavDrawerIdentifier.IDENTIFIER_FORUM),
                new SecondaryDrawerItem()
                        .withName(R.string.item_favorite)
                        .withIcon(R.drawable.ic_favorite)
                        .withIdentifier(NavDrawerIdentifier.IDENTIFIER_FAVORITE_TOPIC),
                new SecondaryDrawerItem()
                        .withName(R.string.item_devdb)
                        .withIcon(R.drawable.ic_devdb)
                        .withIdentifier(NavDrawerIdentifier.IDENTIFIER_DEVDB),
                new SectionDrawerItem()
                        .withName(R.string.item_section_other),
                new SecondaryDrawerItem()
                        .withName(R.string.item_setting)
                        .withIcon(R.drawable.ic_setting)
                        .withIdentifier(NavDrawerIdentifier.IDENTIFIER_SETTING)
        };
    }

    private AccountHeader createAccountHeader() {
        AccountHeaderBuilder accountHeaderBuilder = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.header);
        return accountHeaderBuilder.build();
    }

    public void setDrawerItemSelection(int identifier) {
        navigationDrawer.setSelectionByIdentifier(identifier);
    }
}
