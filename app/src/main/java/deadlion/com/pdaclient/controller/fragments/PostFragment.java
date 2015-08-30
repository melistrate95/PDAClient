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
import deadlion.com.pdaclient.controller.loader.FullPostLoader;
import deadlion.com.pdaclient.controller.loader.PostLoader;
import deadlion.com.pdaclient.model.Post;

/**
 * Created by Михаил on 25.08.2015.
 */
public class PostFragment extends Fragment {

    public static WebView webView;
    String url;

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
        webView.getSettings().setDefaultTextEncodingName("windows-1251");
        webView.setWebViewClient(new MyClient());
        Bundle args = getArguments();
        url = args.getString("url");
        /******************************************/
        FullPostLoader fullPostLoader = new FullPostLoader(getActivity(), webView, url);
        fullPostLoader.loadPost();
        /******************************************/
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
