package deadlion.com.pdaclient.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.view.adapter.adapter_view.PostView;

/**
 * Created by Михаил on 05.08.2015.
 */
public abstract class ItemPostAdapter extends ArrayAdapter<Post> {

    Context context;
    ArrayList<Post> posts;
    PostView postView;

    public ItemPostAdapter(Context context, ArrayList<Post> posts) {
        super(context, 0, posts);
        this.posts = posts;
        this.context = context;
        postView = new PostView(context);
    }

    @Override
    public Post getItem(int position) {
        return posts.get(position);
    }
}
