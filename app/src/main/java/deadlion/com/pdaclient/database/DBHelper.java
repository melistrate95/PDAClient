package deadlion.com.pdaclient.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import deadlion.com.pdaclient.model.Post;
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
        db.execSQL(DBData.getShortTableQuery() + DBData.getFullTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addShortData(Post post) {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBData.CATEGORY, post.getPostCategory().toString());
        cv.put(DBData.POST_TITLE, post.getTitle());
        cv.put(DBData.PHOTO_URL, post.getPhotoUrl());
        cv.put(DBData.DATA_OF_PUBLICATION, post.getDataOfPublication());
        cv.put(DBData.COUNT_OF_COMMENTS, post.getCountOfComments());
        cv.put(DBData.SHORT_POST_TEXT, post.getShortPostText());
        cv.put(DBData.POST_URL, post.getPostUrl());
        db.insert(DBData.SHORT_POST_TABLE, null, cv);
        db.close();
    }

    public void addFullData(Post post) {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBData.CATEGORY, post.getPostCategory().toString());
        cv.put(DBData.POST_TITLE, post.getTitle());
        cv.put(DBData.PHOTO_URL, post.getPhotoUrl());
        cv.put(DBData.DATA_OF_PUBLICATION, post.getDataOfPublication());
        cv.put(DBData.COUNT_OF_COMMENTS, post.getCountOfComments());
        cv.put(DBData.SHORT_POST_TEXT, post.getShortPostText());
        cv.put(DBData.POST_URL, post.getPostUrl());
        cv.put(DBData.FULL_POST_TEXT, post.getFullPostText());
        cv.put(DBData.POST_COMMENTS, post.getPostComments());
        db.insert(DBData.FULL_POST_TABLE, null, cv);
        db.close();
    }

    public void fillShortDB(ArrayList<Post> postArrayList) {
        db = getWritableDatabase();
        for (int i = 0; i < postArrayList.size(); i ++) {
            ContentValues cv = new ContentValues();
            cv.put(DBData.CATEGORY, postArrayList.get(i).getPostCategory().toString());
            cv.put(DBData.POST_TITLE, postArrayList.get(i).getTitle());
            cv.put(DBData.PHOTO_URL, postArrayList.get(i).getPhotoUrl());
            cv.put(DBData.DATA_OF_PUBLICATION, postArrayList.get(i).getDataOfPublication());
            cv.put(DBData.COUNT_OF_COMMENTS, postArrayList.get(i).getCountOfComments());
            cv.put(DBData.SHORT_POST_TEXT, postArrayList.get(i).getShortPostText());
            cv.put(DBData.POST_URL, postArrayList.get(i).getPostUrl());
            db.insert(DBData.SHORT_POST_TABLE, null, cv);
        }
        db.close();
    }

    public void fillFullDB(ArrayList<Post> postArrayList) {
        db = getWritableDatabase();
        for (int i = 0; i < postArrayList.size(); i ++) {
            ContentValues cv = new ContentValues();
            cv.put(DBData.CATEGORY, postArrayList.get(i).getPostCategory().toString());
            cv.put(DBData.POST_TITLE, postArrayList.get(i).getTitle());
            cv.put(DBData.PHOTO_URL, postArrayList.get(i).getPhotoUrl());
            cv.put(DBData.DATA_OF_PUBLICATION, postArrayList.get(i).getDataOfPublication());
            cv.put(DBData.COUNT_OF_COMMENTS, postArrayList.get(i).getCountOfComments());
            cv.put(DBData.SHORT_POST_TEXT, postArrayList.get(i).getShortPostText());
            cv.put(DBData.POST_URL, postArrayList.get(i).getPostUrl());
            cv.put(DBData.FULL_POST_TEXT, postArrayList.get(i).getFullPostText());
            cv.put(DBData.POST_COMMENTS, postArrayList.get(i).getPostComments());
            db.insert(DBData.FULL_POST_TABLE, null, cv);
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
                    Post post = new Post(
                            cursor.getString(cursor.getColumnIndex(DBData.POST_TITLE)),
                            cursor.getString(cursor.getColumnIndex(DBData.PHOTO_URL)),
                            cursor.getString(cursor.getColumnIndex(DBData.DATA_OF_PUBLICATION)),
                            cursor.getString(cursor.getColumnIndex(DBData.COUNT_OF_COMMENTS)),
                            cursor.getString(cursor.getColumnIndex(DBData.SHORT_POST_TEXT)),
                            cursor.getString(cursor.getColumnIndex(DBData.POST_URL)),
                            PostCategory.getCategoryByString(cursor.getString(cursor.getColumnIndex(DBData.CATEGORY)))
                    );
                    arrayList.add(post);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arrayList;
    }

    public Post getFullFavoritePost(int index) {
        db = getWritableDatabase();
        Cursor cursor = db.query(DBData.FULL_POST_TABLE, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int i = 0;
            do {
                if (cursor.getString(cursor.getColumnIndex(DBData.CATEGORY)).equals(PostCategory.FAVORITE_CATEGORY.toString())) {
                    if (i == index) {
                        Post post = new Post(
                                cursor.getString(cursor.getColumnIndex(DBData.POST_TITLE)),
                                cursor.getString(cursor.getColumnIndex(DBData.PHOTO_URL)),
                                cursor.getString(cursor.getColumnIndex(DBData.DATA_OF_PUBLICATION)),
                                cursor.getString(cursor.getColumnIndex(DBData.COUNT_OF_COMMENTS)),
                                cursor.getString(cursor.getColumnIndex(DBData.SHORT_POST_TEXT)),
                                cursor.getString(cursor.getColumnIndex(DBData.POST_URL)),
                                PostCategory.FAVORITE_CATEGORY
                        );
                        post.setFullPostText(cursor.getString(cursor.getColumnIndex(DBData.FULL_POST_TEXT)));
                        post.setCountOfComments(cursor.getString(cursor.getColumnIndex(DBData.COUNT_OF_COMMENTS)));
                        return post;
                    }
                    i ++;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return null;
    }

    public void removeShortPosts(PostCategory category) {
        db = getWritableDatabase();
        db.delete(DBData.SHORT_POST_TABLE, DBData.CATEGORY + " = " + category.toString(), null);
        db.close();
    }

    public void removeFullPosts(PostCategory category) {
        db = getWritableDatabase();
        db.delete(DBData.FULL_POST_TABLE, DBData.CATEGORY + " = " + category.toString(), null);
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
