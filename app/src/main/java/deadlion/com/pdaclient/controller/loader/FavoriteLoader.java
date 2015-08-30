package deadlion.com.pdaclient.controller.loader;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.activity.MainActivity;
import deadlion.com.pdaclient.database.DbHelper;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.model.enum_model.PostCategory;
import deadlion.com.pdaclient.model.enum_model.SpinnerCategory;

/**
 * Created by Михаил on 30.08.2015.
 */
public class FavoriteLoader {

    Post post;
    Context context;

    private static ArrayList<Post> posts = new ArrayList<>();
    private DbHelper dbHelper;

    public FavoriteLoader(Context context, Post post) {
        this.context = context;
        this.post = post;
        dbHelper = new DbHelper(context);
        posts = dbHelper.getPostTable().get(PostCategory.FAVORITE_CATEGORY);
    }

    public class LoadFullTextThread extends AsyncTask<String, Void, String> {

        private void removeElements(Elements elements) {
            if (elements != null) {
                elements.remove();
            }
        }

        private void removeElement(Element element) {
            if (element != null) {
                element.remove();
            }
        }

        String text = "";

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... arg) {
            Document doc;
            try {
                doc = Jsoup.connect(post.getUrl()).get();
                removeElement(doc.getElementById("top-adbox"));
                removeElement(doc.getElementById("header"));
                removeElements(doc.getElementsByClass("slider-news"));
                removeElements(doc.getElementsByClass("materials-box"));
                removeElements(doc.getElementsByClass("more-meta"));
                removeElements(doc.getElementsByClass("box"));
                removeElements(doc.getElementsByAttribute("data-callfn"));
                removeElement(doc.getElementById("commentform"));
                removeElement(doc.getElementById("footer"));

                text = doc.html();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            post.setText(text);
            dbHelper.getPostTable().insert(post);
            Toast.makeText(context, context.getResources().getString(R.string.add_post), Toast.LENGTH_SHORT).show();
        }
    }

    public void loadText() {
        new LoadFullTextThread().execute();
    }

    public static ArrayList<Post> getArrayPosts(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        posts = dbHelper.getPostTable().get(PostCategory.FAVORITE_CATEGORY);
        return posts;
    }
}
