package deadlion.com.pdaclient.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.view.listener.OnPostOverflowSelectedListener;

/**
 * Created by Михаил on 05.08.2015.
 */
public class FullItemPostAdapter extends ItemPostAdapter {

    public FullItemPostAdapter(Context context, ArrayList<Post> posts) {
        super(context, posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.full_item_post, null);
            viewHolder = new ViewHolder();
            viewHolder.saveFullData(convertView);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Post post = getItem(position);

        viewHolder.buildFullPost(context, post);

        return convertView;
    }
}
