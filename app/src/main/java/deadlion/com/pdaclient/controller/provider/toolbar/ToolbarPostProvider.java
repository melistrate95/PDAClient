package deadlion.com.pdaclient.controller.provider.toolbar;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.Toolbar;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.provider.toolbar.ToolbarProvider;

/**
 * Created by Михаил on 07.08.2015.
 */
public class ToolbarPostProvider extends ToolbarProvider {

    public ToolbarPostProvider(Activity context, Toolbar toolbar) {
        super(context, toolbar);
        toolbar.inflateMenu(R.menu.menu_post);
        menu = toolbar.getMenu();
    }

    private void buildPostToolbar(String title) {
        menu.setGroupVisible(R.id.all_item, false);
        menu.getItem(0).setVisible(true);
        toolbar.setTitle(title);
    }

    public void buildToolbar(String title) {
        buildPostToolbar(title);
    }
}
