package deadlion.com.pdaclient.view.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import deadlion.com.pdaclient.controller.activity.PostActivity;
import deadlion.com.pdaclient.controller.fragments.PostFragment;
import deadlion.com.pdaclient.controller.loader.PostLoader;
import deadlion.com.pdaclient.model.Post;

/**
 * Created by Михаил on 25.08.2015.
 */
public class PostPagerAdapter extends FragmentStatePagerAdapter {

    ArrayList<Post> posts;

    public PostPagerAdapter(FragmentManager fm, ArrayList<Post> posts) {
        super(fm);
        this.posts = posts;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putString("url", posts.get(position).getUrl());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return posts.size();
    }

}
