package deadlion.com.pdaclient.controller.provider.main_list;

import android.content.Context;
import android.widget.ListView;

/**
 * Created by Михаил on 14.08.2015.
 */
public abstract class ListProvider {

    protected Context context;
    protected ListView listView;

    public ListProvider(Context context, ListView listView) {
        this.context = context;
        this.listView = listView;
    }
}
