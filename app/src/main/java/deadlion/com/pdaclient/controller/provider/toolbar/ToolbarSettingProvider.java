package deadlion.com.pdaclient.controller.provider.toolbar;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.Toolbar;

import deadlion.com.pdaclient.R;

/**
 * Created by Михаил on 07.08.2015.
 */
public class ToolbarSettingProvider extends ToolbarProvider {

    public ToolbarSettingProvider(Activity context, Toolbar toolbar) {
        super(context, toolbar);
    }

    public void buildSettingToolbar() {
        toolbar.setTitle(context.getResources().getString(R.string.item_setting));
    }

    public void buildSettingAdapterToolbar() {
        toolbar.setTitle(context.getResources().getString(R.string.item_post_view));
    }
}
