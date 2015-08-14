package deadlion.com.pdaclient.controller.provider.main_list;

import android.content.Context;
import android.widget.ListView;

import java.util.ArrayList;

import deadlion.com.pdaclient.controller.provider.setting.SettingProvider;
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
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post("Пост о двух, всем нам знакомых, героях Столичной пустоши",
                "Сейчас я вам раскажу невероятную историю о приключении Генриха Дештвуда и его верного слуги гуля Аргайла." +
                        "Их путешествия начинаются в далекие годы, когда война еще не началась, хотя кому я вру (Война, война никогда не меняется)" +
                        ", да и Аргайл был тогда еще не гулем. Вообщем 2067 год...", PostCategory.FAVORITE_CATEGORY, "xx", "xx", "01.04.2158", 134, new Author("Тридокнайт", "101254")));
        return posts;
    }
}
