package deadlion.com.pdaclient.controller.provider.toolbar;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
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
import deadlion.com.pdaclient.controller.provider.toolbar.ToolbarMainProvider;
import deadlion.com.pdaclient.model.enum_model.NavDrawerIdentifier;

/**
 * Created by Михаил on 11.08.2015.
 */
public class NavigationDrawerProvider {

    private ToolbarMainProvider toolbarProvider;
    private Activity context;
    public Drawer navigationDrawer;


    public NavigationDrawerProvider(ToolbarMainProvider toolbarProvider, Activity context) {
        this.toolbarProvider = toolbarProvider;
        this.context = context;
    }

    public void initializeNavigationDrawer() {

        final AccountHeader accountHeader = createAccountHeader();

        navigationDrawer = new DrawerBuilder()
                .withActivity(context)
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
                        toolbarProvider.buildToolbar(identifier);
                        switch (identifier) {
                            case NavDrawerIdentifier.IDENTIFIER_SETTING:
                                Intent intent = new Intent(context, SettingActivity.class);
                                context.startActivity(intent);
                                break;
                            default:
                                ListAllFragment fragment = (ListAllFragment) context.getFragmentManager().findFragmentByTag("ListAllFragment");
                                fragment.chooseListProvider(identifier, MainActivity.lastSpinnerCategory);
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
                .withActivity(context)
                .withHeaderBackground(R.drawable.header);
        return accountHeaderBuilder.build();
    }

    public void setDrawerItemSelection(int identifier) {
        navigationDrawer.setSelectionByIdentifier(identifier);
    }
}
