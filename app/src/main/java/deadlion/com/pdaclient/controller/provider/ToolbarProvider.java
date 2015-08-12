package deadlion.com.pdaclient.controller.provider;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

/**
 * Created by Михаил on 06.08.2015.
 */
public abstract class ToolbarProvider {

    protected Context context;
    protected Toolbar toolbar;
    protected Menu menu;

    public ToolbarProvider(Context context, Toolbar toolbar) {
        this.toolbar = toolbar;
        this.context = context;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}
