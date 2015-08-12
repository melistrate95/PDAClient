package deadlion.com.pdaclient.controller.provider;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.activity.SettingActivity;
import deadlion.com.pdaclient.controller.activity.SettingPostActivity;
import deadlion.com.pdaclient.controller.fragments.SettingPostFragment;
import deadlion.com.pdaclient.model.enum_model.NavDrawerIdentifier;

/**
 * Created by Михаил on 07.08.2015.
 */
public class ToolbarMainProvider extends ToolbarProvider{

    private View spinnerView;
    private boolean hasSpinner = false;

    public ToolbarMainProvider(final Context context, Toolbar toolbar) {
        super(context, toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        initializeSpinner();
        menu = toolbar.getMenu();
        menu.getItem(3).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(context, SettingPostActivity.class);
                context.startActivity(intent);
                return false;
            }
        });
    }

    private void initializeSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.newsArray, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerView = View.inflate(context.getApplicationContext(), R.layout.toolbar_spinner, null);
        Spinner categorySpinner = (Spinner)spinnerView.findViewById(R.id.spinner);
        categorySpinner.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void addSpinner() {
        if (!hasSpinner) {
            toolbar.addView(spinnerView);
        }
        hasSpinner = true;
    }

    private void removeSpinner() {
        if (hasSpinner) {
            toolbar.removeView(spinnerView);
        }
        hasSpinner = false;
    }

    private void buildPostsToolbar() {
        menu.setGroupVisible(R.id.all_item, false);
        menu.getItem(0).setVisible(true);
        menu.getItem(2).setVisible(true);
        menu.getItem(3).setVisible(true);
        toolbar.setTitle("");
        addSpinner();
    }

    private void buildForumToolbar() {
        menu.setGroupVisible(R.id.all_item, false);
        menu.getItem(0).setVisible(true);
        menu.getItem(2).setVisible(true);
        toolbar.setTitle(context.getResources().getString(R.string.item_forum));
        removeSpinner();
    }

    private void buildDevDBToolbar() {
        menu.setGroupVisible(R.id.all_item, false);
        menu.getItem(0).setVisible(true);
        menu.getItem(2).setVisible(true);
        toolbar.setTitle(context.getResources().getString(R.string.item_devdb));
        removeSpinner();
    }

    private void buildFavouriteToolbar() {
        menu.setGroupVisible(R.id.all_item, false);
        menu.getItem(0).setVisible(true);
        menu.getItem(3).setVisible(true);
        toolbar.setTitle(context.getResources().getString(R.string.item_favorite));
        removeSpinner();
    }

    public void buildToolbar(int identifier) {
        switch (identifier) {
            case NavDrawerIdentifier.IDENTIFIER_POST:
                buildPostsToolbar();
                break;
            case NavDrawerIdentifier.IDENTIFIER_FORUM:
                buildForumToolbar();
                break;
            case NavDrawerIdentifier.IDENTIFIER_FAVORITE_POST:
                buildFavouriteToolbar();
                break;
            case NavDrawerIdentifier.IDENTIFIER_DEVDB:
                buildDevDBToolbar();
                break;
            default:
                break;
        }
    }
}
