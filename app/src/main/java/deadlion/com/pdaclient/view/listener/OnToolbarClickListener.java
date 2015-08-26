package deadlion.com.pdaclient.view.listener;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.fragments.PostFragment;
import deadlion.com.pdaclient.controller.loader.PostLoader;
import deadlion.com.pdaclient.model.Post;

/**
 * Created by Михаил on 07.08.2015.
 */
public class OnToolbarClickListener implements Toolbar.OnMenuItemClickListener {

    Context context;

    public OnToolbarClickListener(Context context) {
        this.context = context;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Post post = PostFragment.getPost();
                shareNewsData(post);
                return true;

        }
        return false;
    }

    private void shareNewsData(Post sharePost) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, sharePost.getUrl());
        intent.putExtra(Intent.EXTRA_SUBJECT, sharePost.getTitle());
        intent = Intent.createChooser(intent, context.getString(R.string.share));
        context.startActivity(intent);
    }
}
