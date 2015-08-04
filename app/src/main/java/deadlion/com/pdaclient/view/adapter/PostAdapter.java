package deadlion.com.pdaclient.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.model.Post;

/**
 * Created by Mike on 01.08.2015.
 */
public class PostAdapter extends ArrayAdapter<Post> {

    Context context;
    ArrayList<Post> posts;

    public PostAdapter(Context context, ArrayList<Post> posts) {
        super(context, 0, posts);
        this.posts = posts;
        this.context = context;
    }

    @Override
    public Post getItem(int position) {
        return posts.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_post, null);
        }

        Post post = getItem(position);

        TextView titleTextView = (TextView) convertView.findViewById(R.id.news_title);
        titleTextView.setText(post.getTitle());

        ImageView photoImageView = (ImageView)convertView.findViewById(R.id.news_photo);
        Picasso.with(getContext())
                .load(post.getPhotoUrl())
                .into(photoImageView);

        TextView dataTextView = (TextView) convertView.findViewById(R.id.news_data);
        dataTextView.setText(post.getShortPostText());

        TextView dateOfPublicationView = (TextView) convertView.findViewById(R.id.date_of_publication);
        dateOfPublicationView.setText(post.getDateOfPublication());

        // TODO: Нету поля author, есть поле authorId, нужно что-то пофиксить
        //TextView authorView = (TextView) convertView.findViewById(R.id.name_author);
        //authorView.setText(post.getAuthor().getUserLogin());

        TextView countCommentView = (TextView) convertView.findViewById(R.id.count_comment);
        countCommentView.setText(post.getCountOfComments() + "");

        return convertView;
    }
}