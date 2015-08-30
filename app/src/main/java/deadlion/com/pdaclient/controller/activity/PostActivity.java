package deadlion.com.pdaclient.controller.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.fragments.PostFragment;
import deadlion.com.pdaclient.controller.loader.FavoriteLoader;
import deadlion.com.pdaclient.controller.loader.PostLoader;
import deadlion.com.pdaclient.controller.provider.toolbar.ToolbarPostProvider;
import deadlion.com.pdaclient.controller.provider.toolbar.ToolbarProvider;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.model.enum_model.NavDrawerIdentifier;
import deadlion.com.pdaclient.view.adapter.PostPagerAdapter;

public class PostActivity extends ToolbarActivity {

    private static ToolbarPostProvider toolbarProvider;
    private static ViewPager viewPager;

    private ArrayList<Post> posts = new ArrayList<>();

    private static Post post;

    public static Post getPost() {
        return post;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_view_pager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbarProvider = new ToolbarPostProvider(this, toolbar);
        FragmentManager manager = getSupportFragmentManager();
        switch (MainActivity.lastNavDrawerIdentifier) {
            case NavDrawerIdentifier.IDENTIFIER_POST:
                posts = PostLoader.getArrayPosts();
                break;
            case NavDrawerIdentifier.IDENTIFIER_FAVORITE_POST:
                posts = FavoriteLoader.getArrayPosts(this);
                break;
        }
        PostPagerAdapter postPagerAdapter = new PostPagerAdapter(manager, posts);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(postPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                post = posts.get(position);
                PostActivity.getToolbarProvider().buildToolbar(post.getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(getIntent().getIntExtra("position", 0));
        post = posts.get(getIntent().getIntExtra("position", 0));
        PostActivity.getToolbarProvider().buildToolbar(post.getTitle());
    }

    public static ToolbarPostProvider getToolbarProvider() {
        return toolbarProvider;
    }

    public static ViewPager getViewPager() {
        return viewPager;
    }
}
