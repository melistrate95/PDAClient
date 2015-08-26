package deadlion.com.pdaclient.controller.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.fragments.PostFragment;
import deadlion.com.pdaclient.controller.loader.PostLoader;
import deadlion.com.pdaclient.controller.provider.toolbar.ToolbarPostProvider;
import deadlion.com.pdaclient.controller.provider.toolbar.ToolbarProvider;
import deadlion.com.pdaclient.view.adapter.PostPagerAdapter;

public class PostActivity extends ToolbarActivity {

    private static ToolbarPostProvider toolbarProvider;
    private static ViewPager viewPager;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_view_pager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbarProvider = new ToolbarPostProvider(this, toolbar);
        FragmentManager manager = getSupportFragmentManager();
        PostPagerAdapter postPagerAdapter = new PostPagerAdapter(manager, PostLoader.getArrayPosts());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(postPagerAdapter);
        viewPager.setCurrentItem(getIntent().getIntExtra("position", 0));
    }

    public static ToolbarPostProvider getToolbarProvider() {
        return toolbarProvider;
    }

    public static ViewPager getViewPager() {
        return viewPager;
    }
}
