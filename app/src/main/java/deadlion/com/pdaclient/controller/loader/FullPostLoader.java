package deadlion.com.pdaclient.controller.loader;

import android.os.AsyncTask;
import android.webkit.WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
                doc = Jsoup.connect(url).get();
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
            webView.loadDataWithBaseURL(null, text, "text/html", "UTF-8", null);
        }
    }

    public void loadPost() {
        new LoadPostThread().execute();
    }
}
