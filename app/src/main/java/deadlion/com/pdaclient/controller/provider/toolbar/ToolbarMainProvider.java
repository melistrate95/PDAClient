package deadlion.com.pdaclient.controller.provider.toolbar;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.activity.MainActivity;
import deadlion.com.pdaclient.controller.activity.SettingPostActivity;
import deadlion.com.pdaclient.controller.fragments.ListAllFragment;
import deadlion.com.pdaclient.controller.provider.main_list.PostListProvider;
import deadlion.com.pdaclient.controller.provider.toolbar.ToolbarProvider;
import deadlion.com.pdaclient.model.enum_model.NavDrawerIdentifier;
import deadlion.com.pdaclient.model.enum_model.PostCategory;
import deadlion.com.pdaclient.model.enum_model.SpinnerCategory;

/**
 * Created by Михаил on 07.08.2015.
 */
public class ToolbarMainProvider extends ToolbarProvider {

    private View spinnerView;
    private boolean hasSpinner = false;

    public ToolbarMainProvider(final Activity context, Toolbar toolbar) {
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

        spinnerView = View.inflate(context.getApplication(), R.layout.toolbar_spinner, null);
        Spinner categorySpinner = (Spinner)spinnerView.findViewById(R.id.spinner);
        categorySpinner.setAdapter(adapter);
        categorySpinner.setSelection(MainActivity.lastSpinnerCategory);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ListAllFragment fragment = (ListAllFragment) context.getFragmentManager().findFragmentByTag("ListAllFragment");
                PostListProvider postListProvider = new PostListProvider(context, fragment.getListView());
                postListProvider.buildList(i);
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
