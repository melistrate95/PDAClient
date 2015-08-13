package deadlion.com.pdaclient.controller.provider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.prefs.MaterialListPreference;

import java.util.ArrayList;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.controller.activity.MainActivity;
import deadlion.com.pdaclient.controller.activity.SettingActivity;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.view.adapter.FullItemPostAdapter;
import deadlion.com.pdaclient.view.adapter.FullWithoutImageItemPostAdapter;
import deadlion.com.pdaclient.view.adapter.ItemPostAdapter;
import deadlion.com.pdaclient.view.adapter.ShortItemPostAdapter;
import deadlion.com.pdaclient.view.adapter.ShortWithoutImageItemPostAdapter;

/**
 * Created by Михаил on 12.08.2015.
 */
public class SettingProvider {

    private Context context;

    private SharedPreferences themeDataPreference;
    private SharedPreferences largeFontPreference;
    private SharedPreferences loadImagePreference;
    private SharedPreferences postViewPreference;

    public SettingProvider(Context context) {
        this.context = context;
    }

    public void getTheme(MaterialListPreference themePreference) {
        themeDataPreference = context.getSharedPreferences("THEME_PREFERENCES", Context.MODE_PRIVATE);
        int index_color = themeDataPreference.getInt("THEME_ID", 0);
        themePreference.setValueIndex(index_color);
        themePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                SharedPreferences.Editor edit = themeDataPreference.edit();
                edit.putInt("THEME_ID", Integer.parseInt((String) newValue));
                edit.apply();
                Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
                return true;
            }
        });
    }

    public void setTheme() {
        themeDataPreference = context.getSharedPreferences("THEME_PREFERENCES", Context.MODE_PRIVATE);
        int index_color = themeDataPreference.getInt("THEME_ID", 0);
        switch (index_color) {
            case 0:
                context.setTheme(R.style.LightTheme);
                break;
            case 1:
                context.setTheme(R.style.DarkTheme);
                break;
            default:
                break;
        }
    }

    public void getFont(final CheckBoxPreference postFontPreference) {
        largeFontPreference = context.getSharedPreferences("FONT_PREFERENCES", Context.MODE_PRIVATE);
        boolean isLarge = largeFontPreference.getBoolean("FONT_ID", false);
        postFontPreference.setChecked(isLarge);
        postFontPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                SharedPreferences.Editor edit = largeFontPreference.edit();
                edit.putBoolean("FONT_ID", postFontPreference.isChecked());
                edit.apply();
                return false;
            }
        });
    }

    public void setFont(TextView textView) {
        largeFontPreference = context.getSharedPreferences("FONT_PREFERENCES", Context.MODE_PRIVATE);
        boolean isLarge = largeFontPreference.getBoolean("FONT_ID", false);
        if (isLarge) {
            textView.setTextSize(20);
        }
        else {
            textView.setTextSize(15);
        }
    }

    public void getLoadImage(MaterialListPreference loadImageListPreference) {
        loadImagePreference = context.getSharedPreferences("LOAD_IMAGE_PREFERENCES", Context.MODE_PRIVATE);
        int isLoadImage = loadImagePreference.getInt("LOAD_IMAGE_ID", 0);
        loadImageListPreference.setValueIndex(isLoadImage);
        loadImageListPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                SharedPreferences.Editor edit = loadImagePreference.edit();
                edit.putInt("LOAD_IMAGE_ID", Integer.parseInt((String) newValue));
                edit.apply();
                return true;
            }
        });
    }

    public void getPostView(MaterialListPreference postViewListPreference) {
        postViewPreference = context.getSharedPreferences("POST_VIEW_PREFERENCES", Context.MODE_PRIVATE);
        int postView = postViewPreference.getInt("POST_VIEW_ID", 0);
        postViewListPreference.setValueIndex(postView);
        postViewListPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                SharedPreferences.Editor edit = postViewPreference.edit();
                edit.putInt("POST_VIEW_ID", Integer.parseInt((String) newValue));
                edit.apply();
                return true;
            }
        });
    }

    public void setPostView(ListView listView, ArrayList<Post> posts) {
        postViewPreference = context.getSharedPreferences("POST_VIEW_PREFERENCES", Context.MODE_PRIVATE);
        int postView = postViewPreference.getInt("POST_VIEW_ID", 0);
        loadImagePreference = context.getSharedPreferences("LOAD_IMAGE_PREFERENCES", Context.MODE_PRIVATE);
        int isLoadImage = loadImagePreference.getInt("LOAD_IMAGE_ID", 0);
        ItemPostAdapter postAdapter = null;
        switch (postView) {
            case 0:
                switch (isLoadImage){
                    case 0:
                        postAdapter = new FullItemPostAdapter(context, posts);
                        break;
                    case 1:
                        postAdapter = new FullItemPostAdapter(context, posts);
                        break;
                    case 2:
                        postAdapter = new FullWithoutImageItemPostAdapter(context, posts);
                        break;
                }
                break;
            case 1:
                switch (isLoadImage){
                    case 0:
                        postAdapter = new ShortItemPostAdapter(context, posts);
                        break;
                    case 1:
                        postAdapter = new ShortItemPostAdapter(context, posts);
                        break;
                    case 2:
                        postAdapter = new ShortWithoutImageItemPostAdapter(context, posts);
                        break;
                }
                break;
        }
        postAdapter.setNotifyOnChange(true);
        listView.setAdapter(postAdapter);
    }
}
