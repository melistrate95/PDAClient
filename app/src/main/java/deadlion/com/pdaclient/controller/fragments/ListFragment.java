package deadlion.com.pdaclient.controller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.model.complex_type.Author;
import deadlion.com.pdaclient.model.enum_model.PostCategory;
import deadlion.com.pdaclient.view.adapter.PostAdapter;

public class ListFragment extends Fragment {

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listView;
    PostAdapter postAdapter;
    ArrayList<Post> posts = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        posts.add(new Post("xx", "xx", PostCategory.ARTICLE_CATEGORY, "xx", "xx", "xx", 2, new Author("xx", "xx")));
        posts.add(new Post("xx", "xx", PostCategory.ARTICLE_CATEGORY, "xx", "xx", "xx", 2, new Author("xx", "xx")));
        posts.add(new Post("xx", "xx", PostCategory.ARTICLE_CATEGORY, "xx", "xx", "xx", 2, new Author("xx", "xx")));
        posts.add(new Post("xx", "xx", PostCategory.ARTICLE_CATEGORY, "xx", "xx", "xx", 2, new Author("xx", "xx")));
        posts.add(new Post("xx", "xx", PostCategory.ARTICLE_CATEGORY, "xx", "xx", "xx", 2, new Author("xx", "xx")));
        posts.add(new Post("xx", "xx", PostCategory.ARTICLE_CATEGORY, "xx", "xx", "xx", 2, new Author("xx", "xx")));


        postAdapter = new PostAdapter(getActivity(), posts);
        postAdapter.setNotifyOnChange(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        listView = (ListView) view.findViewById(R.id.posts);
        listView.setDivider(getResources().getDrawable(android.R.color.transparent));
        listView.setAdapter(postAdapter);
        return view;
    }
}
