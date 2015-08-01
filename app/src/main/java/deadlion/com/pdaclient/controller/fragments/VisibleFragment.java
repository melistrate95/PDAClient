package deadlion.com.pdaclient.controller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.model.User;
import deadlion.com.pdaclient.model.enum_model.PostCategory;

public class VisibleFragment extends Fragment {

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listView;
    PostAdapter postAdapter;
    ArrayList<Post> posts = new ArrayList<Post>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        posts.add(new Post(PostCategory.FAVORITE_CATEGORY, "xx", "xx", new User("xx", "ololo"), "xx", 10, "xx", "xx"));
        posts.add(new Post(PostCategory.FAVORITE_CATEGORY, "xx", "xx", new User("xx", "ololo"), "xx", 10, "xx", "xx"));
        posts.add(new Post(PostCategory.FAVORITE_CATEGORY, "xx", "xx", new User("xx", "ololo"), "xx", 10, "xx", "xx"));
        posts.add(new Post(PostCategory.FAVORITE_CATEGORY, "xx", "xx", new User("xx", "ololo"), "xx", 10, "xx", "xx"));
        posts.add(new Post(PostCategory.FAVORITE_CATEGORY, "xx", "xx", new User("xx", "ololo"), "xx", 10, "xx", "xx"));
        posts.add(new Post(PostCategory.FAVORITE_CATEGORY, "xx", "xx", new User("xx", "ololo"), "xx", 10, "xx", "xx"));
        postAdapter = new PostAdapter(posts);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        listView = (ListView) view.findViewById(R.id.posts);
        listView.setDivider(getResources().getDrawable(android.R.color.transparent));
        listView.setAdapter(postAdapter);
        return view;
    }


    private class PostAdapter extends ArrayAdapter<Post> {

        public PostAdapter(ArrayList<Post> posts) {
            super(getActivity(), 0, posts);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.item_post, null);
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
            dateOfPublicationView.setText(post.getDataOfPublication());

            TextView authorView = (TextView) convertView.findViewById(R.id.name_author);
            authorView.setText(post.getAuthor().getUserLogin());

            TextView countCommentView = (TextView) convertView.findViewById(R.id.count_comment);
            countCommentView.setText(post.getCountOfComments() + "");


            return convertView;
        }
    }
}
