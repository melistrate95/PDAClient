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
import deadlion.com.pdaclient.controller.provider.SettingProvider;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.model.complex_type.Author;
import deadlion.com.pdaclient.model.enum_model.NavDrawerIdentifier;
import deadlion.com.pdaclient.model.enum_model.PostCategory;
import deadlion.com.pdaclient.view.adapter.*;

public class ListAllFragment extends Fragment {

    SwipeRefreshLayout mSwipeRefreshLayout;
    ListView listView;
    ArrayList<Post> posts = new ArrayList<>();
    SettingProvider settingProvider;

    public static ListAllFragment newInstance() {
        ListAllFragment fragment = new ListAllFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        settingProvider = new SettingProvider(getActivity());
        posts.add(new Post("Пост о двух, всем нам знакомых, героях Столичной пустоши",
                "Сейчас я вам раскажу невероятную историю о приключении Генриха Дештвуда и его верного слуги гуля Аргайла." +
                        "Их путешествия начинаются в далекие годы, когда война еще не началась, хотя кому я вру (Война, война никогда не меняется)" +
                        ", да и Аргайл был тогда еще не гулем. Вообщем 2067 год...", PostCategory.ARTICLE_CATEGORY, "xx", "xx", "01.04.2158", 134, new Author("Тридокнайт", "101254")));
        posts.add(new Post("xx", "xx", PostCategory.ARTICLE_CATEGORY, "xx", "xx", "xx", 2, new Author("xx", "xx")));
        posts.add(new Post("xx", "xx", PostCategory.ARTICLE_CATEGORY, "xx", "xx", "xx", 2, new Author("xx", "xx")));
        posts.add(new Post("xx", "xx", PostCategory.ARTICLE_CATEGORY, "xx", "xx", "xx", 2, new Author("xx", "xx")));
        posts.add(new Post("xx", "xx", PostCategory.ARTICLE_CATEGORY, "xx", "xx", "xx", 2, new Author("xx", "xx")));
        posts.add(new Post("xx", "xx", PostCategory.ARTICLE_CATEGORY, "xx", "xx", "xx", 2, new Author("xx", "xx")));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = (ListView) view.findViewById(R.id.posts);
        listView.setDivider(getResources().getDrawable(android.R.color.transparent));
        settingProvider.setPostView(listView, posts);
        return view;
    }
}