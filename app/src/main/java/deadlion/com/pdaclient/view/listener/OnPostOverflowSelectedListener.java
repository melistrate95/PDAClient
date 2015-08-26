package deadlion.com.pdaclient.view.listener;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.fragments.ListAllFragment;
import deadlion.com.pdaclient.controller.provider.main_list.FavoritePostListProvider;
import deadlion.com.pdaclient.database.DbHelper;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.model.enum_model.PostCategory;

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
                    case R.id.add_to_favorite:
                        addToFavorite(post);
                        return true;
                    case R.id.remove_from_favorite:
                        removeFromFavorite(post);
                        return true;
                    case R.id.copy_in_buffer:
                        copyInBuffer(post);
                        return true;
                    case R.id.share:
                        shareNewsData(post);
                        return true;
                    default:
                        return super.onMenuItemSelected(menu, item);
                }
            }
        };
        if (post.getCategory().equals(PostCategory.FAVORITE_CATEGORY)) {
            popupMenu.inflate(R.menu.popup_favorite_post_item);
        }
        else {
            popupMenu.inflate(R.menu.popup_post_item);
        }
        popupMenu.show();
    }

    private void shareNewsData(Post sharePost) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, sharePost.getUrl());
        intent.putExtra(Intent.EXTRA_SUBJECT, sharePost.getTitle());
        intent = Intent.createChooser(intent, context.getString(R.string.share));
        context.startActivity(intent);
    }

    private void copyInBuffer(Post copyPost) {
        String bufferUrl = "http://4pda.ru/" + copyPost.getUrl();
        ((ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE)).setText(bufferUrl);
        Toast toast = Toast.makeText(context, context.getResources().getString(R.string.copy_url), Toast.LENGTH_SHORT);
        toast.show();
    }

    private void addToFavorite(Post addPost) {
        Post favoritePost = addPost;
        favoritePost.setCategory(PostCategory.FAVORITE_CATEGORY);
        DbHelper dbHelper = new DbHelper(context);
        dbHelper.getPostTable().insert(favoritePost);
        Toast.makeText(context, context.getResources().getString(R.string.add_to_favorite), Toast.LENGTH_SHORT).show();
    }

    private void removeFromFavorite(Post removePost) {
        DbHelper dbHelper = new DbHelper(context);
        dbHelper.getPostTable().delete(removePost);
        FavoritePostListProvider favoritePostListProvider = new FavoritePostListProvider(context, ListAllFragment.getListView());
        favoritePostListProvider.buildList();
        ListAllFragment.getmSwipeRefreshLayout().setEnabled(false);
    }
}
