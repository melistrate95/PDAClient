package deadlion.com.pdaclient.controller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.activity.MainActivity;
import deadlion.com.pdaclient.controller.provider.main_list.FavoritePostListProvider;
import deadlion.com.pdaclient.controller.provider.main_list.PostListProvider;
import deadlion.com.pdaclient.model.enum_model.NavDrawerIdentifier;
import deadlion.com.pdaclient.model.enum_model.SpinnerCategory;

public class ListAllFragment extends Fragment {

    Parcelable state;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView listView;

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
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        state = listView.onSaveInstanceState();
    }

    public ListView getListView() {
        return listView;
    }

    public void chooseListProvider(int navIdentifier, int spinnerCategory) {
        MainActivity.lastNavDrawerIdentifier = navIdentifier;
        MainActivity.lastSpinnerCategory = spinnerCategory;
        switch (navIdentifier) {
            case NavDrawerIdentifier.IDENTIFIER_POST:
                PostListProvider postListProvider = new PostListProvider(getActivity(), listView);
                postListProvider.buildList(spinnerCategory);
                break;
            case NavDrawerIdentifier.IDENTIFIER_FAVORITE_POST:
                FavoritePostListProvider favoritePostListProvider = new FavoritePostListProvider(getActivity(), listView);
                favoritePostListProvider.buildList();
                break;
            case NavDrawerIdentifier.IDENTIFIER_FORUM:
                break;
            case NavDrawerIdentifier.IDENTIFIER_DEVDB:
                break;
        }
        if (state != null) {
            listView.onRestoreInstanceState(state);
        }
    }
}