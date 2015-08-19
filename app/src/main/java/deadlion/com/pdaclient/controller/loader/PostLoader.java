package deadlion.com.pdaclient.controller.loader;

import android.os.AsyncTask;
import android.os.Parcelable;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import deadlion.com.pdaclient.controller.activity.MainActivity;
import deadlion.com.pdaclient.controller.fragments.ListAllFragment;
import deadlion.com.pdaclient.controller.provider.setting.SettingProvider;
import deadlion.com.pdaclient.database.DbHelper;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.model.complex_type.Author;
import deadlion.com.pdaclient.model.enum_model.PostCategory;
import deadlion.com.pdaclient.model.enum_model.SpinnerCategory;
import deadlion.com.pdaclient.view.adapter.ItemPostAdapter;

/**
 * Created by Михаил on 18.08.2015.
 */
public class PostLoader {

    ListView listView;
    SettingProvider settingProvider;
    int spinnerCategory = MainActivity.lastSpinnerCategory;
    DbHelper dbHelper;

    public PostLoader(ListView listView, SettingProvider settingProvider) {
        this.listView = listView;
        this.settingProvider = settingProvider;
        dbHelper = new DbHelper(settingProvider.getContext());
    }

    public class LoadPostsThread extends AsyncTask<String, Void, String> {

        ArrayList<Post> posts = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            settingProvider.setPostView(listView, posts);
            ListAllFragment.mSwipeRefreshLayout.post(new Runnable() {
                @Override
                public void run() {
                    ListAllFragment.mSwipeRefreshLayout.setRefreshing(true);
                }
            });
        }

        @Override
        protected String doInBackground(String... arg) {
            Document doc;
            try {
                doc = Jsoup.connect(getHTTPPath()).get();
                Elements title = doc.select(getTitleAttr());
                Elements photo = doc.select(getPhotoAttr());
                Elements data = doc.select(getDataAttr());
                Elements url = doc.select(getUrlAttr());
                Elements dateOfPublication = null, author = null;
                if (spinnerCategory != SpinnerCategory.REVIEW_CATEGORY) {
                    dateOfPublication = doc.select(getDateOfPublicationAttr());
                    author = doc.select(getAuthorAttr());
                }
                Elements countComments = doc.select(getCountCommentsAttr());
                for (int i = 0; i < photo.size(); i ++) {
                    String titleStr = title.get(i).attr("title");
                    String urlStr = url.get(i).attr("href");
                    String photoStr = photo.get(i).attr("src");
                    String dataStr = "";
                    Elements sub = data.get(i).select(getSubDataAttr());
                    for (Element subs : sub) {
                        dataStr += subs.text() + " ";
                    }
                    String dateOfPublicationStr = "";
                    String authorName = "";
                    if (spinnerCategory != SpinnerCategory.REVIEW_CATEGORY) {
                        dateOfPublicationStr = dateOfPublication.get(i).text();
                        authorName = author.get(i).text();
                    }
                    Integer countCommentsInt = Integer.parseInt(countComments.get(i).text());
                    Post post = new Post(titleStr, dataStr, PostCategory.NEWS_CATEGORY, urlStr, photoStr, dateOfPublicationStr, countCommentsInt , new Author(authorName, "104324"));
                    if (posts.size() > i) {
                        posts.set(i, post);
                    }
                    else {
                        posts.add(post);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            settingProvider.setPostView(listView, posts);
            ListAllFragment.mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    public void loadPosts() {
        new LoadPostsThread().execute();
    }

    protected String getHTTPPath() {
        switch (spinnerCategory) {
            case SpinnerCategory.NEWS_CATEGORY:
                return "http://4pda.ru/news/";
            case SpinnerCategory.ARTICLE_CATEGORY:
                return "http://4pda.ru/articles/";
            case SpinnerCategory.REVIEW_CATEGORY:
                return "http://4pda.ru/reviews/";
            case SpinnerCategory.PROGRAM_CATEGORY:
                return "http://4pda.ru/software/";
            case SpinnerCategory.GAME_CATEGORY:
                return "http://4pda.ru/games/";
            default:
                return "http://4pda.ru/news/";
        }
    }

    protected String getTitleAttr() {
        if (spinnerCategory == SpinnerCategory.REVIEW_CATEGORY) {
            return "div > div > ul > li > div > h1 > a";
        }
        return "article > div > h1 > a";
    }

    protected String getPhotoAttr() {
        if (spinnerCategory == SpinnerCategory.REVIEW_CATEGORY) {
            return "div > div > ul > li > div > a > img";
        }
        return "article > div > a > img";
    }

    protected String getDataAttr() {
        if (spinnerCategory == SpinnerCategory.REVIEW_CATEGORY) {
            return "div > div > ul > li > div[class = content]";
        }
        return "article > div > div[itemprop]";
    }

    protected String getUrlAttr() {
        if (spinnerCategory == SpinnerCategory.REVIEW_CATEGORY) {
            return "div > div > ul > li > div > h1 > a";
        }
        return "article > div > h1 > a";
    }

    protected String getSubDataAttr() {
        if (spinnerCategory == SpinnerCategory.REVIEW_CATEGORY) {
            return "ul > li";
        }
        return "p";
    }

    protected String getDateOfPublicationAttr() {
        if (spinnerCategory == SpinnerCategory.REVIEW_CATEGORY) {
            return "";
        }
        return "em.date";
    }

    protected String getAuthorAttr() {
        if (spinnerCategory == SpinnerCategory.REVIEW_CATEGORY) {
            return "";
        }
        return "span.autor > a";
    }

    protected String getCountCommentsAttr() {
        return "a.v-count";
    }
}
