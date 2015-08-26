package deadlion.com.pdaclient.controller.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.activity.PostActivity;
import deadlion.com.pdaclient.controller.loader.PostLoader;
import deadlion.com.pdaclient.model.Post;

/**
 * Created by Михаил on 25.08.2015.
 */
public class PostFragment extends Fragment {

    public static WebView webView;
    String url;
    private static Post post;

    public static Post getPost() {
        return post;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.web_fragment, container, false);
        webView  = (WebView) v.findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSaveFormData(true);
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        webView.setWebViewClient(new MyClient());
        Bundle args = getArguments();
        url = args.getString("url");
        webView.loadUrl(url);
        post = PostLoader.getArrayPosts().get(PostActivity.getViewPager().getCurrentItem());
        PostActivity.getToolbarProvider().buildToolbar(post.getTitle());
        return v;
    }

    private class MyClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            view.getContext().startActivity(intent);
            return true;
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {

        }

        public void onPageFinished(WebView view, String url) {

        }
    }
}
