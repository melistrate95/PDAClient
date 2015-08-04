package deadlion.com.pdaclient.view.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import deadlion.com.pdaclient.model.Post;

/**
 * Created by Михаил on 05.08.2015.
 */
public abstract class ItemPostAdapter extends ArrayAdapter<Post> {

    Context context;
    ArrayList<Post> posts;

    public ItemPostAdapter(Context context, ArrayList<Post> posts) {
        super(context, 0, posts);
        this.posts = posts;
        this.context = context;
    }
}
