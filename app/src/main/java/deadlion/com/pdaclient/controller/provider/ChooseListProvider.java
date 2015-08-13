package deadlion.com.pdaclient.controller.provider;

import android.content.Context;

import deadlion.com.pdaclient.model.enum_model.NavDrawerIdentifier;

/**
 * Created by Михаил on 12.08.2015.
 */
public class ChooseListProvider {

    Context context;

    public ChooseListProvider(Context context) {
        this.context = context;
    }

    public void buildList(int identifier) {
        switch (identifier) {
            case NavDrawerIdentifier.IDENTIFIER_POST:
                break;
            case NavDrawerIdentifier.IDENTIFIER_FAVORITE_POST:
                break;
            case NavDrawerIdentifier.IDENTIFIER_FORUM:
                break;
            case NavDrawerIdentifier.IDENTIFIER_FAVORITE_TOPIC:
                break;
            case NavDrawerIdentifier.IDENTIFIER_DEVDB:
                break;
            default:
                break;
        }
    }
}
