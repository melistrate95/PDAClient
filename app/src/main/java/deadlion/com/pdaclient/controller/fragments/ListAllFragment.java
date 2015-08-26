package deadlion.com.pdaclient.controller.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.activity.MainActivity;
import deadlion.com.pdaclient.controller.provider.main_list.DevDBListProvider;
import deadlion.com.pdaclient.controller.provider.main_list.FavoritePostListProvider;
import deadlion.com.pdaclient.controller.provider.main_list.ForumListProvider;
import deadlion.com.pdaclient.controller.provider.main_list.PostListProvider;
import deadlion.com.pdaclient.controller.provider.setting.SettingProvider;
import deadlion.com.pdaclient.database.DbHelper;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.model.complex_type.Author;
import deadlion.com.pdaclient.model.enum_model.NavDrawerIdentifier;
import deadlion.com.pdaclient.model.enum_model.PostCategory;
import deadlion.com.pdaclient.model.enum_model.SpinnerCategory;
import deadlion.com.pdaclient.view.listener.OnListItemClickListener;

public class ListAllFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    Parcelable state;
    private static SwipeRefreshLayout mSwipeRefreshLayout;
    private static ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        listView = (ListView) view.findViewById(R.id.posts);
        listView.setDivider(getResources().getDrawable(android.R.color.transparent));
        listView.setOnItemClickListener(new OnListItemClickListener(getActivity()));
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.WHITE, Color.BLUE, Color.WHITE);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        state = listView.onSaveInstanceState();
    }

    @Override
    public void onRefresh() {
        chooseListProvider(MainActivity.lastNavDrawerIdentifier, MainActivity.lastSpinnerCategory);
    }

    public static ListView getListView() {
        return listView;
    }

    public static SwipeRefreshLayout getmSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    public void chooseListProvider(int navIdentifier, int spinnerCategory) {
        MainActivity.lastNavDrawerIdentifier = navIdentifier;
        MainActivity.lastSpinnerCategory = spinnerCategory;
        switch (navIdentifier) {
            case NavDrawerIdentifier.IDENTIFIER_POST:
                PostListProvider postListProvider = new PostListProvider(getActivity(), listView);
                postListProvider.buildList(spinnerCategory);
                mSwipeRefreshLayout.setEnabled(true);
                break;
            case NavDrawerIdentifier.IDENTIFIER_FAVORITE_POST:
                FavoritePostListProvider favoritePostListProvider = new FavoritePostListProvider(getActivity(), listView);
                favoritePostListProvider.buildList();
                mSwipeRefreshLayout.setEnabled(false);
                break;
            case NavDrawerIdentifier.IDENTIFIER_FORUM:
                ForumListProvider forumListProvider = new ForumListProvider(getActivity(), listView);
                forumListProvider.buildList();
                mSwipeRefreshLayout.setEnabled(true);
                break;
            case NavDrawerIdentifier.IDENTIFIER_DEVDB:
                DevDBListProvider devDBListProvider = new DevDBListProvider(getActivity(), listView);
                devDBListProvider.buildList();
                mSwipeRefreshLayout.setEnabled(true);
                break;
        }
        if (state != null) {
            listView.onRestoreInstanceState(state);
        }
    }

    public void chooseOfflineListProvider(int navIdentifier, int spinnerCategory) {
        MainActivity.lastNavDrawerIdentifier = navIdentifier;
        MainActivity.lastSpinnerCategory = spinnerCategory;
        switch (navIdentifier) {
            case NavDrawerIdentifier.IDENTIFIER_POST:
                PostListProvider postListProvider = new PostListProvider(getActivity(), listView);
                postListProvider.buildOfflineList(spinnerCategory);
                mSwipeRefreshLayout.setEnabled(true);
                break;
            case NavDrawerIdentifier.IDENTIFIER_FAVORITE_POST:
                FavoritePostListProvider favoritePostListProvider = new FavoritePostListProvider(getActivity(), listView);
                favoritePostListProvider.buildList();
                mSwipeRefreshLayout.setEnabled(false);
                break;
            case NavDrawerIdentifier.IDENTIFIER_FORUM:
                ForumListProvider forumListProvider = new ForumListProvider(getActivity(), listView);
                forumListProvider.buildList();
                mSwipeRefreshLayout.setEnabled(true);
                break;
            case NavDrawerIdentifier.IDENTIFIER_DEVDB:
                DevDBListProvider devDBListProvider = new DevDBListProvider(getActivity(), listView);
                devDBListProvider.buildList();
                mSwipeRefreshLayout.setEnabled(true);
                break;
        }
        if (state != null) {
            listView.onRestoreInstanceState(state);
        }
    }
}