package deadlion.com.pdaclient.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, "PostDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

//    public ArrayList<Post> fillShortArray(PostCategory category) {
//        ArrayList<Post> arrayList = new ArrayList<Post>();
//        db = getWritableDatabase();
//        Cursor cursor = db.query(DBData.SHORT_POST_TABLE, null, null, null, null, null, null);
//        if (cursor.moveToFirst()) {
//            do {
//                if (cursor.getString(cursor.getColumnIndex(DBData.CATEGORY)).equals(category.toString())) {
//                    User author = getUserById(cursor.getString(cursor.getColumnIndex(DBData.AUTHOR_ID)));
//                    Post post = new Post(
//                            PostCategory.getCategoryByString(cursor.getString(cursor.getColumnIndex(DBData.CATEGORY))),
//                            cursor.getString(cursor.getColumnIndex(DBData.POST_TITLE)),
//                            cursor.getString(cursor.getColumnIndex(DBData.PHOTO_URL)),
//                            author,
//                            cursor.getString(cursor.getColumnIndex(DBData.DATE_OF_PUBLICATION)),
//                            cursor.getInt(cursor.getColumnIndex(DBData.COUNT_OF_COMMENTS)),
//                            cursor.getString(cursor.getColumnIndex(DBData.SHORT_POST_TEXT)),
//                            cursor.getString(cursor.getColumnIndex(DBData.POST_URL))
//                    );
//                    arrayList.add(post);
//                }
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return arrayList;
//    }

//    public Post getShortFavoritePost(String urlPost) {
//        db = getWritableDatabase();
//        Post post = null;
//        Cursor cursor = db.query(DBData.SHORT_POST_TABLE, null, null, null, null, null, null);
//        if (cursor.moveToFirst()) {
//            do {
//                if (cursor.getString(cursor.getColumnIndex(DBData.CATEGORY)).equals(PostCategory.FAVORITE_CATEGORY.toString())) {
//                    User author = getUserById(cursor.getString(cursor.getColumnIndex(DBData.AUTHOR_ID)));
//                    post = new Post(
//                            PostCategory.getCategoryByString(cursor.getString(cursor.getColumnIndex(DBData.CATEGORY))),
//                            cursor.getString(cursor.getColumnIndex(DBData.POST_TITLE)),
//                            cursor.getString(cursor.getColumnIndex(DBData.PHOTO_URL)),
//                            author,
//                            cursor.getString(cursor.getColumnIndex(DBData.DATE_OF_PUBLICATION)),
//                            cursor.getInt(cursor.getColumnIndex(DBData.COUNT_OF_COMMENTS)),
//                            cursor.getString(cursor.getColumnIndex(DBData.SHORT_POST_TEXT)),
//                            cursor.getString(cursor.getColumnIndex(DBData.POST_URL))
//                    );
//                    return post;
//                }
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return post;
//    }

//    public Post getFullFavoritePost(String urlPost) {
//        db = getWritableDatabase();
//        Post post = null;
//        Cursor cursor = db.query(DBData.FULL_POST_TABLE, null, null, null, null, null, null);
//        if (cursor.moveToFirst()) {
//            do {
//                if (cursor.getString(cursor.getColumnIndex(DBData.CATEGORY)).equals(PostCategory.FAVORITE_CATEGORY.toString())) {
//                    post = getShortFavoritePost(urlPost);
//                    post.setFullPostText(cursor.getString(cursor.getColumnIndex(DBData.FULL_POST_TEXT)));
//                    post.setPostComments(cursor.getString(cursor.getColumnIndex(DBData.POST_COMMENTS)));
//                    return post;
//                }
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return post;
//    }

}

