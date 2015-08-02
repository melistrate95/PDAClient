package deadlion.com.pdaclient.view.listener;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.model.Post;

/**
 * Created by Mike on 02.08.2015.
 */
public class OnPostOverflowSelectedListener implements View.OnClickListener {

    private Post post;
    private Context context;

    public OnPostOverflowSelectedListener(Context context, Post post) {
        this.context = context;
        this.post = post;
    }

    @Override
    public void onClick(View v) {
        PopupMenu popupMenu = new PopupMenu(context, v) {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.test:
                        return true;
                    default:
                        return super.onMenuItemSelected(menu, item);
                }
            }
        };
        popupMenu.inflate(R.menu.popup_post_item);
        popupMenu.show();
    }
}
