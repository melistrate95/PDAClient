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
import deadlion.com.pdaclient.model.User;
import deadlion.com.pdaclient.model.enum_model.PostCategory;
import deadlion.com.pdaclient.view.adapter.*;

public class ListAllFragment extends Fragment {

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listView;
    FullWithoutImageItemPostAdapter postAdapter;
    ArrayList<Post> posts = new ArrayList<Post>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        posts.add(new Post(PostCategory.FAVORITE_CATEGORY, "Пост о двух, всем нам знакомых, героях Столичной пустоши", "xx", new User("Galaxy News", "106424"), "15.8.2122", 10,
                "Сейчас я вам раскажу невероятную историю о приключении Генриха Дештвуда и его верного слуги гуля Аргайла." +
                        "Их путешествия начинаются в далекие годы, когда война еще не началась, хотя кому я вру (Война, война никогда не меняется)" +
                        ", да и Аргайл был тогда еще не гулем. Вообщем 2067 год...", "xx"));
        posts.add(new Post(PostCategory.FAVORITE_CATEGORY, "xx", "xx", new User("xx", "ololo"), "xx", 10, "xx", "xx"));
        posts.add(new Post(PostCategory.FAVORITE_CATEGORY, "xx", "xx", new User("xx", "ololo"), "xx", 10, "xx", "xx"));
        posts.add(new Post(PostCategory.FAVORITE_CATEGORY, "xx", "xx", new User("xx", "ololo"), "xx", 10, "xx", "xx"));
        posts.add(new Post(PostCategory.FAVORITE_CATEGORY, "xx", "xx", new User("xx", "ololo"), "xx", 10, "xx", "xx"));
        posts.add(new Post(PostCategory.FAVORITE_CATEGORY, "xx", "xx", new User("xx", "ololo"), "xx", 10, "xx", "xx"));
        postAdapter = new FullWithoutImageItemPostAdapter(getActivity(), posts);
        postAdapter.setNotifyOnChange(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = (ListView) view.findViewById(R.id.posts);
        listView.setDivider(getResources().getDrawable(android.R.color.transparent));
        listView.setAdapter(postAdapter);
        return view;
    }
}