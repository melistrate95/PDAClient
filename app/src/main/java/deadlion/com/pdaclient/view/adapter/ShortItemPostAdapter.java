package deadlion.com.pdaclient.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.model.Post;

/**
 * Created by Михаил on 05.08.2015.
 */
public class ShortItemPostAdapter extends ItemPostAdapter {

    public ShortItemPostAdapter(Context context, ArrayList<Post> posts) {
        super(context, posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.short_item_post, null);

            viewHolder = new ViewHolder();
            viewHolder.saveShortData(convertView);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Post post = getItem(position);

        viewHolder.buildShortPost(context, post);

        return convertView;
    }
}
