package deadlion.com.pdaclient.controller.provider.main_list;


import android.content.Context;
import android.widget.ListView;

import java.util.ArrayList;

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

    public void buildList() {
        ArrayList<Post> posts = getPostList(PostCategory.getPostCategory(SpinnerCategory.NEWS_CATEGORY));
        settingProvider.setPostView(listView, posts);
    }

    public void buildList(int spinnerCategory) {
        ArrayList<Post> posts = getPostList(PostCategory.getPostCategory(spinnerCategory));
        settingProvider.setPostView(listView, posts);
    }

    private ArrayList<Post> getPostList(PostCategory postCategory) {
        ArrayList<Post> posts = new ArrayList<>();
        if (postCategory == PostCategory.NEWS_CATEGORY) {
            posts.add(new Post("Пост о двух, всем нам знакомых, героях Столичной пустоши",
                    "Сейчас я вам раскажу невероятную историю о приключении Генриха Дештвуда и его верного слуги гуля Аргайла." +
                            "Их путешествия начинаются в далекие годы, когда война еще не началась, хотя кому я вру (Война, война никогда не меняется)" +
                            ", да и Аргайл был тогда еще не гулем. Вообщем 2067 год...", postCategory, "xx", "xx", "01.04.2158", 134, new Author("Тридокнайт", "101254")));
            posts.add(new Post("xx", "xx", postCategory, "xx", "xx", "xx", 2, new Author("xx", "xx")));
            posts.add(new Post("xx", "xx", postCategory, "xx", "xx", "xx", 2, new Author("xx", "xx")));
            posts.add(new Post("xx", "xx", postCategory, "xx", "xx", "xx", 2, new Author("xx", "xx")));
            posts.add(new Post("xx", "xx", postCategory, "xx", "xx", "xx", 2, new Author("xx", "xx")));
            posts.add(new Post("xx", "xx", postCategory, "xx", "xx", "xx", 2, new Author("xx", "xx")));
        }
        else {
            posts.add(new Post("xx", "xx", postCategory, "xx", "xx", "xx", 2, new Author("xx", "xx")));
            posts.add(new Post("xx", "xx", postCategory, "xx", "xx", "xx", 2, new Author("xx", "xx")));
            posts.add(new Post("xx", "xx", postCategory, "xx", "xx", "xx", 2, new Author("xx", "xx")));
            posts.add(new Post("xx", "xx", postCategory, "xx", "xx", "xx", 2, new Author("xx", "xx")));
            posts.add(new Post("xx", "xx", postCategory, "xx", "xx", "xx", 2, new Author("xx", "xx")));
        }
        return posts;
    }
}
