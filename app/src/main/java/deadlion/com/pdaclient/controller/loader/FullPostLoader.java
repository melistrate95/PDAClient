package deadlion.com.pdaclient.controller.loader;

import android.os.AsyncTask;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

import deadlion.com.pdaclient.model.Post;

/**
 * Created by Михаил on 26.08.2015.
 */
public class FullPostLoader {

    WebView webView;
    String url;

    public FullPostLoader(WebView webView, String url) {
        this.webView = webView;
        this.url = url;
    }

    public class LoadPostThread extends AsyncTask<String, Void, String> {

        String text = "";

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... arg) {
            Document doc;
            try {
                doc = Jsoup.connect(url).get();
                Element head = doc.select("head").first();
                Element el1 = doc.select("article[id=content]").first();
//                Element el1 = doc.select("div.container").first();
//                Element el2 = doc.select("div.comment-box").first();
                text = head.html() + el1.html();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            webView.loadDataWithBaseURL(null, text, "text/html", "ru-RU", null);
        }
    }

    public void loadPost() {
        new LoadPostThread().execute();
    }
}
