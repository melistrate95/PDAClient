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
import deadlion.com.pdaclient.controller.provider.SettingProvider;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.view.listener.OnPostOverflowSelectedListener;

/**
 * Created by Михаил on 05.08.2015.
 */
public abstract class ItemPostAdapter extends ArrayAdapter<Post> {

    static Context context;
    ArrayList<Post> posts;
    ViewHolder viewHolder;

    public ItemPostAdapter(Context context, ArrayList<Post> posts) {
        super(context, 0, posts);
        this.posts = posts;
        this.context = context;
    }

    @Override
    public Post getItem(int position) {
        return posts.get(position);
    }

    static class ViewHolder {
        private View overflowButton;
        private TextView titleTextView;
        private ImageView photoImageView;
        private TextView dataTextView;
        private TextView dateOfPublicationView;
        private TextView authorView;
        private TextView countCommentView;

        private void saveShortText(View convertView) {
            SettingProvider settingProvider = new SettingProvider(context);
            dataTextView = (TextView) convertView.findViewById(R.id.news_data);
            settingProvider.setFont(dataTextView);
        }

        public void saveFullData(View convertView) {
            overflowButton = convertView.findViewById(R.id.post_overflow);
            titleTextView = (TextView) convertView.findViewById(R.id.news_title);
            photoImageView = (ImageView)convertView.findViewById(R.id.news_photo);
            saveShortText(convertView);
            dateOfPublicationView = (TextView) convertView.findViewById(R.id.date_of_publication);
            authorView = (TextView) convertView.findViewById(R.id.name_author);
            countCommentView = (TextView) convertView.findViewById(R.id.count_comment);
            convertView.setTag(this);
        }

        public void saveFullWithoutImageData(View convertView) {
            overflowButton = convertView.findViewById(R.id.post_overflow);
            titleTextView = (TextView) convertView.findViewById(R.id.news_title);
            saveShortText(convertView);
            dateOfPublicationView = (TextView) convertView.findViewById(R.id.date_of_publication);
            authorView = (TextView) convertView.findViewById(R.id.name_author);
            countCommentView = (TextView) convertView.findViewById(R.id.count_comment);
            convertView.setTag(this);
        }

        public void saveShortData(View convertView) {
            overflowButton = convertView.findViewById(R.id.post_overflow);
            titleTextView = (TextView) convertView.findViewById(R.id.news_title);
            photoImageView = (ImageView)convertView.findViewById(R.id.news_photo);
            dateOfPublicationView = (TextView) convertView.findViewById(R.id.date_of_publication);
            authorView = (TextView) convertView.findViewById(R.id.name_author);
            countCommentView = (TextView) convertView.findViewById(R.id.count_comment);
            convertView.setTag(this);
        }

        public void saveShortWithoutImageData(View convertView) {
            overflowButton = convertView.findViewById(R.id.post_overflow);
            titleTextView = (TextView) convertView.findViewById(R.id.news_title);
            dateOfPublicationView = (TextView) convertView.findViewById(R.id.date_of_publication);
            authorView = (TextView) convertView.findViewById(R.id.name_author);
            countCommentView = (TextView) convertView.findViewById(R.id.count_comment);
            convertView.setTag(this);
        }

        public void buildFullPost(Context context, Post post) {
            overflowButton.setOnClickListener(new OnPostOverflowSelectedListener(context, post));
            titleTextView.setText(post.getTitle());
            Picasso.with(context)
                    .load(post.getPhotoUrl())
                    .into(photoImageView);
            dataTextView.setText(post.getDescription());
            dateOfPublicationView.setText(post.getDateOfPublication());
            authorView.setText(post.getAuthor().getName());
            countCommentView.setText(post.getCountOfComments() + "");
        }

        public void buildFullWithoutImagePost(Context context, Post post) {
            overflowButton.setOnClickListener(new OnPostOverflowSelectedListener(context, post));
            titleTextView.setText(post.getTitle());
            dataTextView.setText(post.getDescription());
            dateOfPublicationView.setText(post.getDateOfPublication());
            authorView.setText(post.getAuthor().getName());
            countCommentView.setText(post.getCountOfComments() + "");
        }

        public void buildShortPost(Context context, Post post) {
            overflowButton.setOnClickListener(new OnPostOverflowSelectedListener(context, post));
            titleTextView.setText(post.getTitle());
            Picasso.with(context)
                    .load(post.getPhotoUrl())
                    .into(photoImageView);
            dateOfPublicationView.setText(post.getDateOfPublication());
            authorView.setText(post.getAuthor().getName());
            countCommentView.setText(post.getCountOfComments() + "");
        }

        public void buildShortWithoutImagePost(Context context, Post post) {
            overflowButton.setOnClickListener(new OnPostOverflowSelectedListener(context, post));
            titleTextView.setText(post.getTitle());
            dateOfPublicationView.setText(post.getDateOfPublication());
            authorView.setText(post.getAuthor().getName());
            countCommentView.setText(post.getCountOfComments() + "");
        }
    }
}
