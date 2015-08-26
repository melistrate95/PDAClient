package deadlion.com.pdaclient.view.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;

import deadlion.com.pdaclient.controller.activity.MainActivity;
import deadlion.com.pdaclient.controller.activity.PostActivity;
import deadlion.com.pdaclient.model.enum_model.NavDrawerIdentifier;

/**
 * Created by Михаил on 19.08.2015.
 */
public class OnListItemClickListener implements AdapterView.OnItemClickListener {

    Context context;

    public OnListItemClickListener(Context context) {
        this.context = context;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (MainActivity.lastNavDrawerIdentifier) {
            case NavDrawerIdentifier.IDENTIFIER_POST:
                Intent intent = new Intent(context, PostActivity.class);
                intent.putExtra("position", position);
                context.startActivity(intent);
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
