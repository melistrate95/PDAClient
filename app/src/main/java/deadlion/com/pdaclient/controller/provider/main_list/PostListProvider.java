package deadlion.com.pdaclient.controller.provider.main_list;


import android.content.Context;
import android.widget.ListView;

import java.util.ArrayList;

import deadlion.com.pdaclient.controller.activity.MainActivity;
import deadlion.com.pdaclient.controller.loader.PostLoader;
import deadlion.com.pdaclient.controller.provider.setting.SettingProvider;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.model.complex_type.Author;
import deadlion.com.pdaclient.model.enum_model.PostCategory;
import deadlion.com.pdaclient.model.enum_model.SpinnerCategory;

/**
 * Created by Михаил on 14.08.2015.
 */
public class PostListProvider extends ListProvider {

    SettingProvider settingProvider;

    public PostListProvider(Context context, ListView listView) {
        super(context, listView);
        settingProvider = new SettingProvider(context);
    }

    public void buildList(int spinnerCategory) {
        MainActivity.lastSpinnerCategory = spinnerCategory;
        PostLoader postLoader = new PostLoader(context, listView);
        postLoader.loadPosts();
    }

    public void buildOfflineList(int spinnerCategory) {
        MainActivity.lastSpinnerCategory = spinnerCategory;
        PostLoader postLoader = new PostLoader(context, listView);
        postLoader.showPosts();
    }
}
