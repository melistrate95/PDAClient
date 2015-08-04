package deadlion.com.pdaclient.view.adapter;

import android.content.Context;
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
public class ShortItemPostAdapter extends ItemPostAdapter {

    public ShortItemPostAdapter(Context context, ArrayList<Post> posts) {
        super(context, posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.short_item_post, null);
        }

        Post post = getItem(position);

        View overflow = convertView.findViewById(R.id.post_overflow);
        overflow.setOnClickListener(new OnPostOverflowSelectedListener(context, post));

        TextView titleTextView = (TextView) convertView.findViewById(R.id.news_title);
        titleTextView.setText(post.getTitle());

        ImageView photoImageView = (ImageView)convertView.findViewById(R.id.news_photo);
        Picasso.with(getContext())
                .load(post.getPhotoUrl())
                .into(photoImageView);

        TextView dateOfPublicationView = (TextView) convertView.findViewById(R.id.date_of_publication);
        dateOfPublicationView.setText(post.getDataOfPublication());

        TextView authorView = (TextView) convertView.findViewById(R.id.name_author);
        authorView.setText(post.getAuthor().getUserLogin());

        TextView countCommentView = (TextView) convertView.findViewById(R.id.count_comment);
        countCommentView.setText(post.getCountOfComments() + "");

        return convertView;
    }
}
