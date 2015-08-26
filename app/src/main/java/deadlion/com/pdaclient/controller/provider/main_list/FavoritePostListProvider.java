package deadlion.com.pdaclient.controller.provider.main_list;

import android.content.Context;
import android.widget.ListView;

import java.util.ArrayList;

import deadlion.com.pdaclient.controller.provider.setting.SettingProvider;
import deadlion.com.pdaclient.database.DbHelper;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.model.complex_type.Author;
import deadlion.com.pdaclient.model.enum_model.PostCategory;

/**
 * Created by Михаил on 14.08.2015.
 */
public class FavoritePostListProvider extends ListProvider{

    SettingProvider settingProvider;

    public FavoritePostListProvider(Context context, ListView listView) {
        super(context, listView);
        settingProvider = new SettingProvider(context);
    }

    public void buildList() {
        ArrayList<Post> posts = getFavoriteList();
        settingProvider.setPostView(listView, posts);
    }

    private ArrayList<Post> getFavoriteList() {
        DbHelper dbHelper = new DbHelper(context);
        ArrayList<Post> posts = dbHelper.getPostTable().get(PostCategory.FAVORITE_CATEGORY);
        return posts;
    }
}
