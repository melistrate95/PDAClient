package deadlion.com.pdaclient.view.listener;

import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import deadlion.com.pdaclient.controller.activity.MainActivity;
import deadlion.com.pdaclient.model.enum_model.NavDrawerIdentifier;

/**
 * Created by Михаил on 19.08.2015.
 */
public class OnListItemClickListener implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (MainActivity.lastNavDrawerIdentifier) {
            case NavDrawerIdentifier.IDENTIFIER_POST:
                break;
            case NavDrawerIdentifier.IDENTIFIER_FAVORITE_POST:
                break;
            case NavDrawerIdentifier.IDENTIFIER_FORUM:
                break;
            case NavDrawerIdentifier.IDENTIFIER_DEVDB:
                break;
        }
    }
}
