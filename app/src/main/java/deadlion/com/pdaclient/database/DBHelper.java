package deadlion.com.pdaclient.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.model.User;
import deadlion.com.pdaclient.model.enum_model.PostCategory;

/**
 * Created by Mike on 27.07.2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, "PostDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBData.getShortTableQuery());
        db.execSQL(DBData.getFullTableQuery());
        db.execSQL(DBData.getAuthorsTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addAuthor(User user) {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBData.AUTHOR_NAME, user.getUserLogin());
        cv.put(DBData.AUTHOR_ID, user.getMemberId());
        db.insert(DBData.AUTHORS_TABLE, null, cv);
        db.close();
    }

    public void addShortPost(Post post) {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBData.CATEGORY, post.getPostCategory().toString());
        cv.put(DBData.POST_TITLE, post.getTitle());
        cv.put(DBData.PHOTO_URL, post.getPhotoUrl());
        cv.put(DBData.AUTHOR_ID, post.getAuthor().getMemberId());
        cv.put(DBData.DATE_OF_PUBLICATION, post.getDataOfPublication());
        cv.put(DBData.COUNT_OF_COMMENTS, post.getCountOfComments());
        cv.put(DBData.SHORT_POST_TEXT, post.getShortPostText());
        cv.put(DBData.POST_URL, post.getPostUrl());
        db.insert(DBData.SHORT_POST_TABLE, null, cv);
        db.close();
    }

    public void addFullPost(Post post) {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBData.POST_URL, post.getPostUrl());
        cv.put(DBData.FULL_POST_TEXT, post.getFullPostText());
        cv.put(DBData.POST_COMMENTS, post.getPostComments());
        db.insert(DBData.FULL_POST_TABLE, null, cv);
        db.close();
    }

    public void fillShortDB(ArrayList<Post> postArrayList) {
        for (Post post : postArrayList) {
            addShortPost(post);
        }
        db.close();
    }

    public void fillFullDB(ArrayList<Post> postArrayList) {
        for (Post post : postArrayList) {
            addFullPost(post);
        }
        db.close();
    }

    public ArrayList<Post> fillShortArray(PostCategory category) {
        ArrayList<Post> arrayList = new ArrayList<Post>();
        db = getWritableDatabase();
        Cursor cursor = db.query(DBData.SHORT_POST_TABLE, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex(DBData.CATEGORY)).equals(category.toString())) {
                    User author = getUserById(cursor.getString(cursor.getColumnIndex(DBData.AUTHOR_ID)));
                    Post post = new Post(
                            PostCategory.getCategoryByString(cursor.getString(cursor.getColumnIndex(DBData.CATEGORY))),
                            cursor.getString(cursor.getColumnIndex(DBData.POST_TITLE)),
                            cursor.getString(cursor.getColumnIndex(DBData.PHOTO_URL)),
                            author,
                            cursor.getString(cursor.getColumnIndex(DBData.DATE_OF_PUBLICATION)),
                            cursor.getInt(cursor.getColumnIndex(DBData.COUNT_OF_COMMENTS)),
                            cursor.getString(cursor.getColumnIndex(DBData.SHORT_POST_TEXT)),
                            cursor.getString(cursor.getColumnIndex(DBData.POST_URL))
                    );
                    arrayList.add(post);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arrayList;
    }

    public User getUserById(String id) {
        db = getWritableDatabase();
        User author = null;
        Cursor cursor = db.query(DBData.AUTHORS_TABLE, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex(DBData.AUTHOR_ID)).equals(id)) {
                    author = new User(cursor.getString(cursor.getColumnIndex(DBData.AUTHOR_NAME)), id);
                    return author;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return author;
    }

    public Post getShortFavoritePost(String urlPost) {
        db = getWritableDatabase();
        Post post = null;
        Cursor cursor = db.query(DBData.SHORT_POST_TABLE, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex(DBData.CATEGORY)).equals(PostCategory.FAVORITE_CATEGORY.toString())) {
                    User author = getUserById(cursor.getString(cursor.getColumnIndex(DBData.AUTHOR_ID)));
                    post = new Post(
                            PostCategory.getCategoryByString(cursor.getString(cursor.getColumnIndex(DBData.CATEGORY))),
                            cursor.getString(cursor.getColumnIndex(DBData.POST_TITLE)),
                            cursor.getString(cursor.getColumnIndex(DBData.PHOTO_URL)),
                            author,
                            cursor.getString(cursor.getColumnIndex(DBData.DATE_OF_PUBLICATION)),
                            cursor.getInt(cursor.getColumnIndex(DBData.COUNT_OF_COMMENTS)),
                            cursor.getString(cursor.getColumnIndex(DBData.SHORT_POST_TEXT)),
                            cursor.getString(cursor.getColumnIndex(DBData.POST_URL))
                    );
                    return post;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return post;
    }

    public Post getFullFavoritePost(String urlPost) {
        db = getWritableDatabase();
        Post post = null;
        Cursor cursor = db.query(DBData.FULL_POST_TABLE, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(cursor.getColumnIndex(DBData.CATEGORY)).equals(PostCategory.FAVORITE_CATEGORY.toString())) {
                    post = getShortFavoritePost(urlPost);
                    post.setFullPostText(cursor.getString(cursor.getColumnIndex(DBData.FULL_POST_TEXT)));
                    post.setPostComments(cursor.getString(cursor.getColumnIndex(DBData.POST_COMMENTS)));
                    return post;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return post;
    }

    public void removeShortPosts(PostCategory category) {
        db = getWritableDatabase();
        db.delete(DBData.SHORT_POST_TABLE, DBData.CATEGORY + " = " + category.toString(), null);
        db.close();
    }

    public void removeFullPosts(PostCategory category, String urlPost) {
        db = getWritableDatabase();
        db.delete(DBData.FULL_POST_TABLE,
                DBData.CATEGORY + " = " + category.toString() + " " + DBData.POST_URL + " = " + urlPost,
                null);
        db.close();
    }

    public void cleanShortData() {
        db = getWritableDatabase();
        db.delete(DBData.SHORT_POST_TABLE, null, null);
        db.close();
    }

    public void cleanFullData() {
        db = getWritableDatabase();
        db.delete(DBData.FULL_POST_TABLE, null, null);
        db.close();
    }
}
